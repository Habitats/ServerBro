package serverBro.broServer;

import serverBro.broServer.networking.ServerNetworkController;
import serverBro.broShared.Controller;
import serverBro.broShared.Logger;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.view.BroGuiController;

/**
 * The main server controller. Handles high level server activities.
 * 
 * @author Patrick
 * 
 */
public class ServerController extends Controller {
  private NetworkController serverController;
  private Authenticator auth;

  public ServerController(BroGuiController view) {
    super(view);

    model.setName("server");
    model.setNetworkStatus("online!");
    auth = new Authenticator();
  }

  @Override
  public void incomingEvent(NetworkEvent event) {
    Logger.log("Server received: " + event);
    event.setController(this);

    if (auth.authenticate(event)) {
      event.execute(this);
    }
  }

  @Override
  public void sendEvent(NetworkEvent event) {
    serverController.sendEvent(event);
  }

  @Override
  public void startService() {
    serverController = new ServerNetworkController(this);
    serverController.connect();
  }

  @Override
  public void stopService() {
    Logger.log("Disconnecting server...");
    serverController.disconnect();
  }

  @Override
  public void actionPerformed(ViewEvent viewEvent) {
    viewEvent.execute(this);
  }

  public Authenticator getAuthenticator() {
    return auth;
  }
}
