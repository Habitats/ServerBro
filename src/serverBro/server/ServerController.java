package serverBro.server;

import java.util.ArrayList;
import java.util.List;

import serverBro.Config;
import serverBro.Logger;
import serverBro.NetworkController;
import serverBro.events.DiagnosisEvent;
import serverBro.events.NetworkEvent;
import serverBro.gui.BroController;
import serverBro.utilities.ComputerInfo;

public class ServerController implements NetworkController {
  private ServerConnectionOutgoing serverConnectionOutgoing;
  private List<ClientConnection> clientConnections;
  private BroController broController;
  private ServerConnectionManager serverConnectionManager;

  public ServerController(BroController broController) {
    this.broController = broController;
    connect();
  }


  @Override
  public void evaluateIncoming(NetworkEvent event) {
    Logger.log("SERVER CONTROLLER GOT EVENT");
    DiagnosisEvent de =
        new DiagnosisEvent(false, new ComputerInfo().getRunningProcesses(),event.getSender());
    serverConnectionOutgoing.returnEventToSender(de);

    broController.displayNetworkStatus(event.toString());
  }


  @Override
  public void evaluteOutgoing(NetworkEvent event) {
    serverConnectionOutgoing.evaluate(event);
  }


  public List<ClientConnection> getClientConnections() {
    return clientConnections;

  }

  @Override
  public void sendNetworkEvent(NetworkEvent event) {}

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
}
