package serverBro.broShared.events.internal;

import serverBro.broShared.Controller;


public class DisconnectButtonEvent extends ViewEvent {


  @Override
  public void execute(Controller controller) {
    controller.stopService();
  }
}
