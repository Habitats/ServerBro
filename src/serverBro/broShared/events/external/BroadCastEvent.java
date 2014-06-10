package serverBro.broShared.events.external;

import serverBro.broShared.Controller;

public class BroadCastEvent extends NetworkEvent {

  private final String message;


  public BroadCastEvent(String message) {
    super(GLOBAL);
    this.message = message;
  }

  @Override
  public void execute(Controller controller) {
    controller.model.addMessage("Broadcast: " + message);
  }
}
