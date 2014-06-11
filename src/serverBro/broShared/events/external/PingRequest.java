package serverBro.broShared.events.external;

import serverBro.broShared.Controller;

public class PingRequest extends NetworkEvent {

  private final long sentTime;

  public PingRequest() {
    super(NetworkEvent.PRIVATE);
    sentTime = System.currentTimeMillis();
  }

  @Override
  public void execute(Controller controller) {
    // long receivedTime = System.currentTimeMillis();
    controller.model.addMessage(getSender().getUsername() + ": ping!");
    controller.sendEvent(new PingResponse(sentTime));
  }
}
