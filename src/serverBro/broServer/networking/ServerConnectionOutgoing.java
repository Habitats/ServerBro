package serverBro.broServer.networking;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import serverBro.broShared.Identity;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.misc.Config;
import serverBro.broShared.misc.CryptoManager;
import serverBro.broShared.misc.Logger;

/**
 * Manage outgoing communication from the server
 * 
 * @author Patrick
 * 
 */
public class ServerConnectionOutgoing {

  private final List<ClientConnection> clientConnections;

  public ServerConnectionOutgoing(List<ClientConnection> clientConnections) {
    this.clientConnections = clientConnections;
  }

  public void sendEvent(NetworkEvent event) {
    if (event.messageType == NetworkEvent.GLOBAL) {
      Logger.log("Server broadcasted: " + event);
      broadcastNetworkEvent(event);
    } else {
      Logger.log("Server sent: " + event);
      returnEventToSender(event);
    }
  }

  public void returnEventToSender(Serializable event) {
    Identity sender = ((NetworkEvent) event).getSender();
    try {
      for (ClientConnection clientConnection : clientConnections) {
        if (clientConnection.getIdentity() != null && clientConnection.getIdentity().getUsername().toLowerCase().equals(sender.getUsername().toLowerCase())) {
          if (Config.getInstance().encryptionEnabled()) {
            event = CryptoManager.getInstance().encryptNetworkEvent(event);
          }
          clientConnection.getOut().writeObject(event);
          clientConnection.getOut().reset();
        }
      }
    } catch (IOException e) {
      Logger.error("Writing to socket failed. Couldn't return event to sender", e);
    }
  }

  public void broadcastNetworkEvent(Serializable event) {
    for (ClientConnection clientConnection : clientConnections) {
      try {
        if (Config.getInstance().encryptionEnabled()) {
          event = CryptoManager.getInstance().encryptNetworkEvent(event);
        }
        clientConnection.getOut().writeObject(event);
        clientConnection.getOut().reset();
      } catch (IOException e) {
        Logger.error("Writing to socket failed. Couldn't broadcast event to " + clientConnection.toString(), e);
      }
    }
  }
}
