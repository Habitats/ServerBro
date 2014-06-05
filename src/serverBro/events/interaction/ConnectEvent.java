package serverBro.events.interaction;

import serverBro.Controller;

public class ConnectEvent extends ViewEvent {

  public ConnectEvent(Controller controller) {
    super(controller);
  }

  @Override
  public void execute() {
    controller.startService();
  }
}
