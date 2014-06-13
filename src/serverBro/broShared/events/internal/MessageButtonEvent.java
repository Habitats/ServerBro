package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.misc.Config;


public class MessageButtonEvent extends ViewEvent {

  @Override
  public void execute(Controller controller) {
    // controller.sendEvent(new MessageEvent(message));
    if (Config.getInstance().isConnected()) {

    }
  }
}
