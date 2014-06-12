package serverBro.broClient.networking;

import serverBro.broShared.Controller;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.misc.Config;
import serverBro.broShared.misc.Logger;
import serverBro.broShared.view.BroGuiController;

public class ClientNetworkController implements NetworkController {
  private ClientOutgoing clientOutgoing;
  private BroGuiController broController;
  private Controller clientController;
  private ClientIncoming clientIncoming;

  public ClientNetworkController(Controller clientController) {
    this.clientController = clientController;

  }

  @Override
  public void evaluateIncoming(NetworkEvent event) {
    Logger.log("Client received: " + event);
    clientController.incomingEvent(event);
  }


  public void setServerConnection(ServerConnection serverConnection) {
    clientOutgoing.setServerConnection(serverConnection);
  }

  @Override
  public void sendEvent(NetworkEvent event) {
    if (Config.getInstance().isConnected()) {
      Logger.log("Client sent: " + event);
      clientOutgoing.sendNetworkEvent(event);
    } else {
      Logger.log("Unable to send network event. Not connected!");
    }
  }

  @Override
  public void connect() {
    boolean connected = Config.getInstance().isConnected();
    if (!connected) {
      Config.getInstance().setConnected(true);
      clientOutgoing = new ClientOutgoing();
      int port = Config.getInstance().getServerPort();
      String hostname = Config.getInstance().getServerHostName();
      clientIncoming = new ClientIncoming(port, hostname, this);
      Thread clientThread = new Thread(clientIncoming);
      clientThread.start();
    } else {
      Logger.log("Client already connected, disconnect first!");
    }
  }

  @Override
  public void disconnect() {
    if (Config.getInstance().isConnected()) {
      Logger.log("Disconnecting client...");
      clientIncoming.kill();
      Config.getInstance().setConnected(false);
    }
  }

  public BroGuiController getBroController() {
    return broController;
  }
}
