package serverBro.broServer;

import serverBro.broServer.networking.ServerNetworkController;
import serverBro.broShared.Controller;
import serverBro.broShared.Logger;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.view.BroGuiController;
import serverBro.broShared.view.BroView;

public class ServerController extends Controller {
  private NetworkController networkController;
  private Authenticator auth;

  public ServerController() {
    super();

    model.setName("server");
    model.setNetworkStatus("online!");
    auth = new Authenticator();
  }

  public void startServer() {
    networkController = new ServerNetworkController(this);
    networkController.connect();
  }

  @Override
  protected BroView createView() {
    BroView broView = new BroGuiController();
    broView.setBroViewListener(this);
    return broView;
  }


  @Override
  public void incomingEvent(NetworkEvent event) {
    Logger.log("SERVER CONTROLLER GOT EVENT");
    event.setController(this);

    if (auth.authenticate(event)) {
      event.execute(this);
    }
  }

  @Override
  public void sendEvent(NetworkEvent event) {
    Logger.log("SERVER CONTROLLER SENT EVENT");
    networkController.sendEvent(event);
  }

  @Override
  public void startService() {
    startServer();
  }

  @Override
  public void stopService() {
    networkController.disconnect();
  }

  @Override
  public void actionPerformed(ViewEvent viewEvent) {
    viewEvent.execute(this);
  }


  public Authenticator getAuthenticator() {
    return auth;
  }
}
