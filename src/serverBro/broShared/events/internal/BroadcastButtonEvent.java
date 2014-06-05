package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.BroadCastEvent;


public class BroadcastButtonEvent extends ViewEvent {

  @Override
  public void execute(Controller controller) {
    controller.sendEvent(new BroadCastEvent("hello hello"));
  }
}
