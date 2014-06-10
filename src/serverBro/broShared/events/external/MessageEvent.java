package serverBro.broShared.events.external;

import serverBro.broShared.Controller;



public class MessageEvent extends NetworkEvent {

  public final String message;

  public MessageEvent(String message) {
    super(PRIVATE);
    this.message = message;
  }

  @Override
  public String toString() {
    return message;
  }


  @Override
  public void execute(Controller controller) {
    controller.model.addMessage("Message: " + message);
  }
}
