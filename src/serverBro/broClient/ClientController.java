package serverBro.broClient;

import serverBro.broClient.networking.ClientNetworkController;
import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.broShared.Logger;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.view.BroGuiController;

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

  @Override
  public void incomingEvent(NetworkEvent event) {
    event.execute(this);
  }

  @Override
  public void sendEvent(NetworkEvent event) {
    if (clientNetworkController != null) {
      clientNetworkController.sendEvent(event);
    } else {
      Logger.log("Unable to send event. No connection!");
    }
  }

  @Override
  public void startService() {
    clientNetworkController = new ClientNetworkController(this);
    clientNetworkController.connect();
  }

  @Override
  public void stopService() {
    clientNetworkController.disconnect();
  }

  @Override
  public void actionPerformed(ViewEvent viewEvent) {
    viewEvent.execute(this);
  }
}
