package serverBro.broServer;

import serverBro.Controller;
import serverBro.Logger;
import serverBro.NetworkController;
import serverBro.broServer.networking.ServerNetworkController;
import serverBro.events.networkEvents.NetworkEvent;
import serverBro.events.viewEvents.ViewEvent;
import serverBro.gui.BroView;
import serverBro.gui.swing.BroGui;

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
    BroView broView = new BroGui();
    broView.setBroViewListener(this);
    return broView;
  }


  @Override
  public void incomingEvent(NetworkEvent event) {
    Logger.log("SERVER CONTROLLER GOT EVENT");
    event.setController(this);
    event.execute();
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
  public void actionPerformed(ViewEvent e) {
    e.execute();
  }


  public Authenticator getAuthenticator() {
    return auth;
  }
}
