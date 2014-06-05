package serverBro.events.interaction;

import serverBro.Controller;
import serverBro.events.EventFactory;
import serverBro.events.networkEvents.diagnostic.ProcessRequestEvent;


public class InfoEvent extends ViewEvent {

  public InfoEvent(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    controller.sendEvent(new ProcessRequestEvent());
  }
}
