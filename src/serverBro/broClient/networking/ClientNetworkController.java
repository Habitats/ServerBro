package serverBro.broClient.networking;

import serverBro.Config;
import serverBro.Controller;
import serverBro.NetworkController;
import serverBro.events.networkEvents.NetworkEvent;
import serverBro.gui.swing.BroGui;

public class ClientNetworkController implements NetworkController {
  private ClientOutgoing clientOutgoing;
  private BroGui broController;
  private Controller clientController;

  public ClientNetworkController(Controller clientController) {
    this.clientController = clientController;

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
    clientController.incomingEvent(event);
  }

  @Override
  public synchronized void sendNetworkEvent(NetworkEvent event) {
    clientOutgoing.sendNetworkEvent(event);
  }


  public void setServerConnection(ServerConnection serverConnection) {
    clientOutgoing.setServerConnection(serverConnection);
  }

  @Override
  public void sendEvent(NetworkEvent event) {}

  @Override
  public void connect() {}

  @Override
  public void disconnect() {}

  public BroGui getBroController() {
    return broController;
  }
}
