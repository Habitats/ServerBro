package serverBro.broServer.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

  private void initConnection() {
    try {
      out = new ObjectOutputStream(clientSocket.getOutputStream());
      in = new ObjectInputStream(clientSocket.getInputStream());
    } catch (IOException e) {
      Logger.error("Couldn't get socket streams", e);
    }
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
      Logger.error("Client dropped! Cleaning up...", e);
    }
    serverController.getClientConnections().remove(clientConnection);
  }

  @Override
  public void run() {
    initConnection();
    try {
      if (out != null) {
        out.close();
      }
      if (in != null) {
        in.close();
      }
      if (clientSocket != null) {
        clientSocket.close();
      }
    } catch (IOException e) {
      Logger.error("Couldn't close socket streams", e);
    }
  }
}
