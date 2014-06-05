package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;

public class ConnectButtonEvent extends ViewEvent {

  @Override
  public void execute(Controller controller) {
    controller.startService();
  }
}
