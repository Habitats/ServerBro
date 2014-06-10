package serverBro.broShared.events.external;

import serverBro.broShared.Controller;

public class PingResponse extends NetworkEvent {
  private final long sentTime;
  private long receivedTime;

  public PingResponse(long sentTime) {
    super(NetworkEvent.PRIVATE);
    this.sentTime = sentTime;
  }

  @Override
  public void execute(Controller controller) {
    receivedTime = System.currentTimeMillis();
    controller.model.addMessage("pong! - " + (receivedTime - sentTime) + " ms");
  }
}
