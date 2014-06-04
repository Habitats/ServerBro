package serverBro.events.viewEvents;

import serverBro.Controller;
import serverBro.events.networkEvents.MessageEvent;
import serverBro.events.networkEvents.NetworkEvent;

public class SendEvent extends ViewEvent {

  public SendEvent(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    NetworkEvent message = new MessageEvent("Hello!");
    controller.sendEvent(message);
  }
}
