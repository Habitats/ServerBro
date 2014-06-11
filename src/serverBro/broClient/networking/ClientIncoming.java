package serverBro.broClient.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import serverBro.broShared.Config;
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
  private Socket clientSocket;
  private ClientNetworkController clientController;

  public ClientIncoming(int port, String hostname, ClientNetworkController clientController) {
    this.port = port;
    this.hostname = hostname;
    this.clientController = clientController;
  }

  private Socket setUpConnection(int port, String hostname) {
    Logger.log("Connecting to " + hostname + " on port " + port + "...");
    try {
      clientSocket = new Socket(hostname, port);
    } catch (IOException e) {
      Logger.log("Unable to connect...");

      Config.getInstance().setConnected(false);
    }
    return clientSocket;
  }

  private void initConnection(Socket socket) {
    try {
      ServerConnection serverConnection = new ServerConnection(new ObjectOutputStream(socket.getOutputStream()), socket);
      clientController.setServerConnection(serverConnection);
      in = new ObjectInputStream(socket.getInputStream());

      NetworkEvent event;
      Logger.log("Initiating streams...");
      while ((event = (NetworkEvent) in.readObject()) != null) {
        synchronized (event) {
          // Singleton.log("Client received: " + event.toString());
          clientController.evaluateIncoming(event);

          // not sure why this has to be done here
        }
      }

    } catch (IOException | ClassNotFoundException e) {
      Logger.log("Lost connection!");
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (Config.getInstance().isConnected()) {
      Socket socket = setUpConnection(port, hostname);
      if (socket != null) {
        Logger.log("Client disconnected!");
        initConnection(socket);
      }
      try {
        if (!Config.getInstance().isConnected()) {
          return;
        }
        int sleepTime = 1000;
        Logger.log("Connection failed, retrying in " + sleepTime + " ms!");
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
      }
    }

  }

  public void kill() {
    try {
      if (out != null)
        out.close();
      if (in != null)
        in.close();
      if (clientSocket != null)
        clientSocket.close();
    } catch (IOException e) {
      Logger.log("Couldn't close socket...");
    }
  }

  public ObjectOutputStream getOut() {
    return out;
  }
}
