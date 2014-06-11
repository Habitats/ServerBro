package serverBro.broShared.events.internal;

import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.broShared.events.external.PingRequest;


public class MessageButtonEvent extends ViewEvent {

  @Override
  public void execute(Controller controller) {
    // controller.sendEvent(new MessageEvent(message));
    if (Config.getInstance().isConnected()) {

    }
    controller.sendEvent(new PingRequest());
  }
}
