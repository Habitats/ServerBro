package serverBro.events.networkEvents.diagnostic;

import serverBro.events.EventFactory;
import serverBro.events.networkEvents.NetworkEvent;

public class ProcessRequestEvent extends NetworkEvent{

  @Override
  public void execute() {
    getController().sendEvent(new ProcessEvent());
  }
}
