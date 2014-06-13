package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.BroadCastEvent;
import serverBro.broShared.misc.Config;
import serverBro.broShared.misc.Logger;


public class DisconnectButtonEvent extends ViewEvent {


  @Override
  public void execute(Controller controller) {
    if (Config.getInstance().isConnected()) {
      controller.sendEvent(new BroadCastEvent(Config.getInstance().getId().getUsername() + " is disconnecting!"));
      controller.stopService();
      controller.model.addMessage("Disconnected");
    } else {
      Logger.log("Already disconnected.");
    }
  }
}
