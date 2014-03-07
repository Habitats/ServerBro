package serverBro.server;

import java.util.ArrayList;
import java.util.List;

import serverBro.Config;
import serverBro.Logger;
import serverBro.events.NetworkEvent;

public class ServerController {
  private ServerConnectionOutgoing serverConnectionOutgoing;
  private List<ClientConnection> clientConnections;

  public ServerController() {
    int port = Config.getInstance().getServerPort();

    clientConnections = new ArrayList<ClientConnection>();

    serverConnectionOutgoing = new ServerConnectionOutgoing(clientConnections);

    Thread serverThread = new Thread(new ServerConnectionManager(port, this));
    serverThread.start();

  }

  public void evaluateIncoming(NetworkEvent event) {
    Logger.log("SERVER CONTROLLER GOT EVENT");
//     serverConnectionOutgoing.broadcastNetworkEvent(event);
    serverConnectionOutgoing.returnEventToSender(event);
  }


  public void evaluteOutgoing(NetworkEvent event) {
    serverConnectionOutgoing.evaluate(event);
  }


  public List<ClientConnection> getClientConnections() {
    return clientConnections;

  }


}
