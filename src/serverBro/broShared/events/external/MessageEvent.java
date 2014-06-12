package serverBro.broShared.events.external;

import serverBro.broShared.Controller;
import serverBro.broShared.Identity;



public class MessageEvent extends NetworkEvent {

  public final String message;

  public MessageEvent(String message) {
    this(message,null);
  }

  public MessageEvent(String message, Identity sender) {
    super(PRIVATE,sender);
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
