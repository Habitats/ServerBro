package serverBro.events.viewEvents;

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
