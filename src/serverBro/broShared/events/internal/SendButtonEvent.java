package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.external.diagnostic.ProcessEvent;

public class SendButtonEvent extends ViewEvent {


  @Override
  public void execute(Controller controller) {
    // NetworkEvent event = new MessageEvent("Hello!");
    NetworkEvent event = new ProcessEvent();

    controller.sendEvent(event);
  }
}
