package serverBro.events;

import serverBro.Authenticator;
import serverBro.Config;
import serverBro.Identity;

public class AuthEvent extends NetworkEvent {
  private static final long serialVersionUID = 1406998928869635813L;

  public enum EventType {
    LOG_OUT
  }

  private EventType type;
  private final static boolean GLOBAL = false;
  private Authenticator auth;
  private Identity authId;

  public AuthEvent(EventType type) {
    super(GLOBAL, NetworkEvent.EventType.AUTH);
    this.type = type;
  }

  @Override
  public String toString() {
    return "AUTH EVENT from " + senderId.getUsername();
  }

  @Override
  public void executeIncoming() {
    auth.authenticateUser(authId);
  }

  @Override
  public void executeOutgoing() {
    this.authId = Config.getInstance().getId();
  }
}
