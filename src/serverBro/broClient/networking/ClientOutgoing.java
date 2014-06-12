package serverBro.broClient.networking;

import java.io.Serializable;

import serverBro.broShared.Config;
import serverBro.broShared.CryptoManager;
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

  public synchronized void sendNetworkEvent(Serializable event) {
    try {
      // Singleton.log("Client sending: " + event);
      if (Config.getInstance().encryptionEnabled()) {
        event = CryptoManager.getInstance().encryptNetworkEvent(event);
      }
      serverConnection.getOut().writeObject(event);
      serverConnection.getOut().reset();
    } catch (Exception e) {
      Logger.error("Socket write failed!", e);
    }
  }

  public void setServerConnection(ServerConnection serverConnection) {
    this.serverConnection = serverConnection;
  }
}
