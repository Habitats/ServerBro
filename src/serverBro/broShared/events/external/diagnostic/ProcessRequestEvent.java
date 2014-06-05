package serverBro.broShared.events.external.diagnostic;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.NetworkEvent;

public class ProcessRequestEvent extends NetworkEvent{

  @Override
  public void execute(Controller controller) {
    getController().sendEvent(new ProcessEvent());
  }
}
