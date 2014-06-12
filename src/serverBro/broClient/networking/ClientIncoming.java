package serverBro.broClient.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

import serverBro.broShared.Config;
import serverBro.broShared.CryptoManager;
import serverBro.broShared.Logger;
import serverBro.broShared.events.external.NetworkEvent;

/**
 * Handles incoming communication with the server.
 * 
 * @author Patrick
 * 
 */
public class ClientIncoming implements Runnable {
  private final String hostname;
  private final int port;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private ClientNetworkController clientController;
  private Socket clientSocket;

  public ClientIncoming(int port, String hostname, ClientNetworkController clientController) {
    this.port = port;
    this.hostname = hostname;
    this.clientController = clientController;
  }

  private Socket setUpConnection(int port, String hostname) {
    Logger.log("Connecting to " + hostname + " on port " + port + "...");
    Socket clientSocket = null;
    try {
      clientSocket = new Socket();
      int timeOutInMs = 1000;
      clientSocket.connect(new InetSocketAddress(hostname, port), timeOutInMs);
    } catch (IOException e) {
      Logger.error("Unable to connect...", e);

    }
    return clientSocket;
  }

  private void initConnection(Socket clientSocket) {
    try {
      ServerConnection serverConnection = new ServerConnection(new ObjectOutputStream(clientSocket.getOutputStream()), clientSocket);
      clientController.setServerConnection(serverConnection);
      in = new ObjectInputStream(clientSocket.getInputStream());

      Serializable serializable = null;
      NetworkEvent event = null;
      Logger.log("Initiating streams...");
      while ((serializable = (Serializable) in.readObject()) != null) {
        if (Config.getInstance().encryptionEnabled()) {
          serializable = CryptoManager.getInstance().decryptNetworkEvent(serializable);
        }
        event = (NetworkEvent) serializable;
        synchronized (event) {
          // Singleton.log("Client received: " + event.toString());
          clientController.evaluateIncoming((NetworkEvent) event);

        }
      }

    } catch (IOException | ClassNotFoundException e) {
      Logger.error("Lost connection", e);
    }
  }

  @Override
  public void run() {
    while (Config.getInstance().isConnected()) {
      clientSocket = setUpConnection(port, hostname);
      if (clientSocket != null) {
        Logger.log("Client disconnected!");
        initConnection(clientSocket);
      }
      try {
        if (Config.getInstance().isConnected()) {
          int sleepTime = 1000;
          Logger.log("Connection failed, retrying in " + sleepTime + " ms!");
          Thread.sleep(sleepTime);
        } else {
          break;
        }
      } catch (InterruptedException e) {
        Logger.error("Sleep interrupted", e);
      }
    }

    // always clean up your sockets!
    kill();
  }

  public void kill() {
    Logger.log("Killing client connection");
    try {
      if (out != null)
        out.close();
      if (in != null)
        in.close();
      if (clientSocket != null)
        clientSocket.close();
    } catch (IOException e) {
      Logger.error("Couldn't close socket...", e);
    } finally {
    }
  }

  public ObjectOutputStream getOut() {
    return out;
  }
}
