package serverBro.broClient;


import serverBro.Controller;
import serverBro.Logger;
import serverBro.NetworkController;
import serverBro.broClient.networking.ClientNetworkController;
import serverBro.events.interaction.ViewEvent;
import serverBro.events.networkEvents.NetworkEvent;
import serverBro.gui.BroView;
import serverBro.gui.swing.BroGuiController;

public class ClientController extends Controller {
  private NetworkController clientNetworkController;

  public ClientController() {
    super();
  }

  private void startClient() {
    clientNetworkController = new ClientNetworkController(this);
    clientNetworkController.connect();
  }

  @Override
  public void incomingEvent(NetworkEvent event) {
    Logger.log("CLIENT CONTROLLER GOT EVENT");
    event.setController(this);
    event.execute();
  }

  @Override
  public void sendEvent(NetworkEvent event) {
    clientNetworkController.sendEvent(event);
  }

  @Override
  public void startService() {
    startClient();
  }

  @Override
  public void stopService() {
    stopClient();
  }


  private void stopClient() {
    stopServer();
  }

  private void stopServer() {
    clientNetworkController.disconnect();
  }

  @Override
  protected BroView createView() {
    BroGuiController view = new BroGuiController();
    view.setBroViewListener(this);
    return view;
  }

  @Override
  public void actionPerformed(ViewEvent viewEvent) {
    viewEvent.execute();
  }
}
