package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.MessageEvent;


public class MessageButtonEvent extends ViewEvent {


  @Override
  public void execute(Controller controller) {
    controller.sendEvent(new MessageEvent("this is a message"));
  }
}
