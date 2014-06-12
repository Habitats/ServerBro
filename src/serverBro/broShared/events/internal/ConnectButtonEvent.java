package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.misc.Config;
import serverBro.broShared.misc.Logger;

public class ConnectButtonEvent extends ViewEvent {

  @Override
  public void execute(Controller controller) {
    if (!Config.getInstance().isConnected()) {
      Logger.log("Trying to start service...");
      controller.startService();
    } else {
      Logger.log("Already connected!");
    }
  }
}
