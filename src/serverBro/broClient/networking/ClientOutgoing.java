package serverBro.broClient.networking;

import serverBro.broShared.Logger;
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
    } catch (Exception e) {
      Logger.error("Socket write failed!",e);
    }
  }

  public void setServerConnection(ServerConnection serverConnection) {
    this.serverConnection = serverConnection;
  }
}
