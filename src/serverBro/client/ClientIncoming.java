package serverBro.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import serverBro.Logger;
import serverBro.events.NetworkEvent;

public class ClientIncoming implements Runnable {
  private final String hostname;
  private final int port;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private Socket clientSocket;
  private ClientController clientController;

  public ClientIncoming(int port, String hostname, ClientController clientController) {
    this.port = port;
    this.hostname = hostname;
    this.clientController = clientController;
  }

  private Socket setUpConnection(int port, String hostname) {
    Logger.log("Connecting to " + hostname + " on port " + port + "...");
    try {
      clientSocket = new Socket(hostname, port);
    } catch (IOException e) {
      Logger.log("Unable to connect. Exiting...");
      kill();
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
      kill();
    }
  }

  @Override
  public void run() {
    Socket socket = setUpConnection(port, hostname);
    initConnection(socket);

    kill();
  }

  private void kill() {
    try {
      if (out != null)
        out.close();
      if (in != null)
        in.close();
      if (clientSocket != null)
        clientSocket.close();
    } catch (IOException e) {
      Logger.log("Couldn't close socket...");
    } finally {
      Logger.log("Exiting...");
      System.exit(0);
    }
  }

  public ObjectOutputStream getOut() {
    return out;
  }
}
