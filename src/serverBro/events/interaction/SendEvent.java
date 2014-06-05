package serverBro.events.interaction;

import serverBro.Controller;
import serverBro.events.networkEvents.NetworkEvent;
import serverBro.events.networkEvents.diagnostic.ProcessEvent;

public class SendEvent extends ViewEvent {

  public SendEvent(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    // NetworkEvent event = new MessageEvent("Hello!");
    NetworkEvent event = new ProcessEvent();

    controller.sendEvent(event);
  }
}
