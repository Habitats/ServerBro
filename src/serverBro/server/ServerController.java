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

  public ServerController(BroController broController) {
    this.broController = broController;
    int port = Config.getInstance().getServerPort();

    clientConnections = new ArrayList<ClientConnection>();

    serverConnectionOutgoing = new ServerConnectionOutgoing(clientConnections);

    Thread serverThread = new Thread(new ServerConnectionManager(port, this));
    serverThread.start();

  }

  public void evaluateIncoming(NetworkEvent event) {
    Logger.log("SERVER CONTROLLER GOT EVENT");
    DiagnosisEvent de =
        new DiagnosisEvent(event.getSender(), false, new ComputerInfo().getRunningProcesses());
    serverConnectionOutgoing.returnEventToSender(de);

    broController.displayNetworkStatus(event.toString());
  }


  public void evaluteOutgoing(NetworkEvent event) {
    serverConnectionOutgoing.evaluate(event);
  }


  public List<ClientConnection> getClientConnections() {
    return clientConnections;

  }

  @Override
  public void sendNetworkEvent(NetworkEvent event) {}
}
