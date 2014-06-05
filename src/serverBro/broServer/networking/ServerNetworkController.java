package serverBro.broServer.networking;

import java.util.ArrayList;
import java.util.List;

import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;

public class ServerNetworkController implements NetworkController {
  private ServerConnectionOutgoing serverConnectionOutgoing;
  private List<ClientConnection> clientConnections;
  private ServerConnectionManager serverConnectionManager;


  private Controller serverController;

  public ServerNetworkController(Controller serverController) {
    this.serverController = serverController;
  }


  public List<ClientConnection> getClientConnections() {
    return clientConnections;

  }


  @Override
  public void connect() {
    int port = Config.getInstance().getServerPort();

    clientConnections = new ArrayList<ClientConnection>();

    serverConnectionOutgoing = new ServerConnectionOutgoing(clientConnections);
    serverConnectionManager = new ServerConnectionManager(port, this);
    Thread serverThread = new Thread(serverConnectionManager);
    serverThread.start();
  }

  @Override
  public void disconnect() {
    serverConnectionManager.setListening(false);
  }


  @Override
  public void sendEvent(NetworkEvent event) {
    serverConnectionOutgoing.sendEvent(event);
  }


  @Override
  public void evaluateIncoming(NetworkEvent event) {
    serverController.incomingEvent(event);
  }
}
