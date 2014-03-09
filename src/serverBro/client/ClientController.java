package serverBro.client;

import serverBro.Config;
import serverBro.Logger;
import serverBro.NetworkController;
import serverBro.events.DiagnosisEvent;
import serverBro.events.NetworkEvent;
import serverBro.gui.BroController;

public class ClientController implements NetworkController {
  private ClientOutgoing clientOutgoing;
  private BroController broController;

  public ClientController(BroController broController) {
    this.broController = broController;
    broController.setName("Client!");

    int port = Config.getInstance().getServerPort();
    String hostname = Config.getInstance().getServerHostName();

    clientOutgoing = new ClientOutgoing();
    Thread clientThread = new Thread(new ClientIncoming(port, hostname, this));
    clientThread.start();



    // while (true) {
    // try {
    // Thread.sleep(5000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // sendNetworkEvent(new AuthEvent(null, id));
    // sendNetworkEvent(new DiagnosisEvent(id, false, null));
    // server.evaluteOutgoing(new BroadCastEvent(null));
    // }
  }

  @Override
  public void evaluateIncoming(NetworkEvent event) {
    Logger.log("CLIENT CONTROLLER GOT EVENT");

    // System.out.println(event.toString());

    // broController.displayProcesses(event.toString());
    switch (event.getType()) {
      case AUTH:
        broController.displayNetworkStatus(event.toString());
        break;
      case BROADCAST:
        break;
      case DIAGNOSTIC:
        broController.displayProcesses(((DiagnosisEvent) event).getProcesses());
        break;
      default:
        break;
    }
  }

  @Override
  public synchronized void sendNetworkEvent(NetworkEvent event) {
    clientOutgoing.sendNetworkEvent(event);
  }


  public void setServerConnection(ServerConnection serverConnection) {
    clientOutgoing.setServerConnection(serverConnection);
  }

  @Override
  public void evaluteOutgoing(NetworkEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public void connect() {
    // TODO Auto-generated method stub

  }

  @Override
  public void disconnect() {
    // TODO Auto-generated method stub

  }
}
