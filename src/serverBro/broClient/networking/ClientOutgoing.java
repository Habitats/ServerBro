package serverBro.broClient.networking;

import java.io.IOException;

import serverBro.broShared.events.external.NetworkEvent;

/**
 * Handles outgoing communication with the server.
 * 
 * @author Patrick
 * 
 */
public class ClientOutgoing {

  private ServerConnection serverConnection;

  public synchronized void sendNetworkEvent(NetworkEvent event) {
    try {
      // Singleton.log("Client sending: " + event);
      serverConnection.getOut().writeObject(event);
      serverConnection.getOut().reset();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setServerConnection(ServerConnection serverConnection) {
    this.serverConnection = serverConnection;
  }
}
