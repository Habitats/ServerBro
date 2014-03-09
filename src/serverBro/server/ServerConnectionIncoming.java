package serverBro.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import serverBro.Config;
import serverBro.Logger;
import serverBro.events.AuthEvent;
import serverBro.events.NetworkEvent;

/**
 * Handles the connection with a single client. Event client gets it's own connection on it's own
 * thread.
 * 
 */
public class ServerConnectionIncoming implements Runnable {

  private final Socket clientSocket;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  private ClientConnection clientConnection;
  private ServerController serverController;

  public ServerConnectionIncoming(Socket clientSocket, ServerController serverController) {
    this.clientSocket = clientSocket;
    this.serverController = serverController;
  }

  private void initConnection() throws IOException {
    out = new ObjectOutputStream(clientSocket.getOutputStream());
    in = new ObjectInputStream(clientSocket.getInputStream());
    serverController.getClientConnections().add(new ClientConnection(out, clientSocket));

    NetworkEvent event;

    try {
      while ((event = (NetworkEvent) in.readObject()) != null
          && Config.getInstance().isNetworkEnabled()) {
        synchronized (event) {
          Logger.log("Server received: " + event);
          for (ClientConnection clientConnection : serverController.getClientConnections()) {
            if (clientConnection.getClientSocket() == clientSocket && event.getSender() != null) {
              clientConnection.setIdentity(event.getSender());
              this.clientConnection = clientConnection;
              // break;
            }
          }
          // forward event to serverController that handles it
          serverController.evaluateIncoming(event);
        }
      }
    } catch (Exception e) {
      Logger.log("Client dropped! Cleaning up...");
      e.printStackTrace();
    }
    out.close();
    in.close();
    clientSocket.close();
    serverController.evaluateIncoming(new AuthEvent(AuthEvent.EventType.LOG_OUT, clientConnection
        .getIdentity()));
    serverController.getClientConnections().remove(clientConnection);
  }

  @Override
  public void run() {
    try {
      initConnection();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        out.close();
        in.close();
        clientSocket.close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }
}
