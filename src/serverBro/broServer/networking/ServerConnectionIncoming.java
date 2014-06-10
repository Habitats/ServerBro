package serverBro.broServer.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import serverBro.broShared.Config;
import serverBro.broShared.Logger;
import serverBro.broShared.events.external.NetworkEvent;

/**
 * Handles the outgoing communication with a single client. Each client has it's own connection on
 * it's own thread.
 * 
 * @author Patrick
 */
public class ServerConnectionIncoming implements Runnable {

  private final Socket clientSocket;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  private ClientConnection clientConnection;
  private ServerNetworkController serverController;

  public ServerConnectionIncoming(Socket clientSocket, ServerNetworkController serverController) {
    this.clientSocket = clientSocket;
    this.serverController = serverController;
  }

  private void initConnection() throws IOException {
    out = new ObjectOutputStream(clientSocket.getOutputStream());
    in = new ObjectInputStream(clientSocket.getInputStream());
    serverController.getClientConnections().add(new ClientConnection(out, clientSocket));

    NetworkEvent event;

    try {
      while ((event = (NetworkEvent) in.readObject()) != null) {
        synchronized (event) {
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
      // e.printStackTrace();
    }
    out.close();
    in.close();
    clientSocket.close();
    // serverController.evaluateIncoming(new AuthEvent());
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
        // e1.printStackTrace();
      }
    }
  }
}
