package serverBro.events;

import serverBro.Identity;

public class AuthEvent extends NetworkEvent {
  private static final long serialVersionUID = 1406998928869635813L;

  public enum EventType {
    LOG_OUT
  }

  private EventType type;
  private final static boolean GLOBAL = false;

  public AuthEvent(EventType type, Identity id) {
    super(id, GLOBAL, NetworkEvent.EventType.AUTH);
    this.type = type;
  }

  @Override
  public String toString() {
    return "AUTH EVENT from " + id.getUsername();
  }
}
