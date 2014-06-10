package serverBro.broServer.networking;

import java.io.IOException;
import java.util.List;

import serverBro.broShared.Identity;
import serverBro.broShared.Logger;
import serverBro.broShared.events.external.NetworkEvent;

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

  public void returnEventToSender(NetworkEvent event) {
    Identity sender = event.getSender();
    try {
      for (ClientConnection clientConnection : clientConnections) {
        if (clientConnection.getIdentity() != null && clientConnection.getIdentity().getUsername().toLowerCase().equals(sender.getUsername().toLowerCase())) {
          clientConnection.getOut().writeObject(event);
          clientConnection.getOut().reset();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void broadcastNetworkEvent(NetworkEvent event) {
    for (ClientConnection clientConnection : clientConnections) {
      try {
        clientConnection.getOut().writeObject(event);
        clientConnection.getOut().reset();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
