package serverBro.broClient;

import serverBro.broClient.networking.ClientNetworkController;
import serverBro.broShared.Controller;
import serverBro.broShared.Logger;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.view.BroGuiController;
import serverBro.broShared.view.BroView;

/**
 * The main client controller. Handles high level client activities.
 * 
 * @author Patrick
 * 
 */
public class ClientController extends Controller {
  private NetworkController clientNetworkController;

  public ClientController(BroGuiController view) {
    super(view);
  }

  private void startClient() {
    clientNetworkController = new ClientNetworkController(this);
    clientNetworkController.connect();
  }

  @Override
  public void incomingEvent(NetworkEvent event) {
    Logger.log("CLIENT CONTROLLER GOT EVENT");
    event.execute(this);
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
  public void actionPerformed(ViewEvent viewEvent) {
    viewEvent.execute(this);
  }
}
