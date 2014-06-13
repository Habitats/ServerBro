package serverBro.broShared.events.external;

import serverBro.broShared.Controller;
import serverBro.broShared.misc.Config;

public class ConnectedMessageEvent extends NetworkEvent {
  public ConnectedMessageEvent() {
    super(PRIVATE, Config.getInstance().getId());
  }

  @Override
  public void execute(Controller controller) {
    controller.sendEvent(new MessageEvent("Connection successful"));
  }
}
