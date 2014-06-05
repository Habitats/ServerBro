package serverBro.events.interaction;

import serverBro.Controller;


public class DisconnectEvent extends ViewEvent {

  public DisconnectEvent(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    controller.stopService();
  }
}
