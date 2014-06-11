package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.diagnostic.ComputerInfoRequestEvent;

public class ComputerInfoButtonEvent extends ViewEvent {

  @Override
  public void execute(Controller controller) {
    // TODO Auto-generated method stub
    controller.sendEvent(new ComputerInfoRequestEvent());

  }
}
