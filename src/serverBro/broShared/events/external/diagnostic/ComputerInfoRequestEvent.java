package serverBro.broShared.events.external.diagnostic;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.NetworkEvent;

public class ComputerInfoRequestEvent extends NetworkEvent {

  @Override
  public void execute(Controller controller) {
    controller.sendEvent(new ComputerInfoEvent(controller));
  }
}
