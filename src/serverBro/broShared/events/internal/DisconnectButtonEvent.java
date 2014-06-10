package serverBro.broShared.events.internal;

import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.broShared.Logger;
import serverBro.broShared.events.external.BroadCastEvent;


public class DisconnectButtonEvent extends ViewEvent {


  @Override
  public void execute(Controller controller) {
    if (Config.getInstance().isConnected()) {
      controller.sendEvent(new BroadCastEvent(Config.getInstance().getId().getUsername() + " is disconnecting!"));
      controller.stopService();
    } else {
      Logger.log("Already disconnected.");
    }
  }
}
