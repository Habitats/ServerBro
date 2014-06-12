package serverBro.broServer;

import serverBro.broServer.networking.ServerNetworkController;
import serverBro.broShared.Controller;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.external.authentication.AccessDeniedEvent;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.misc.Logger;
import serverBro.broShared.utilities.ComputerInfo;
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
  private ServerInfoManager serverInfoManager;

  public ServerController(BroGuiController view) {
    super(view);

    model.setName("server");
    model.setNetworkStatus("online!");
    auth = new Authenticator();
    serverInfoManager = new ServerInfoManager();
  }

  @Override
  public void incomingEvent(NetworkEvent event) {
    Logger.log("Server received: " + event);

    if (auth.authenticate(event)) {
      event.execute(this);
    }
    else{
      NetworkEvent accessDenied = new AccessDeniedEvent(event.getSender());
      sendEvent(accessDenied);
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

  @Override
  public ComputerInfo generateComputerInfo(NetworkEvent event) {
    ComputerInfo computerInfo = serverInfoManager.createComputerInfo(event);
    return computerInfo;
  }
}
