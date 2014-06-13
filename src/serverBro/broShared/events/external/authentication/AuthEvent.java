package serverBro.broShared.events.external.authentication;

import serverBro.broShared.Identity;
import serverBro.broShared.events.external.NetworkEvent;

public abstract class AuthEvent extends NetworkEvent {
  private static final long serialVersionUID = 1406998928869635813L;

  public enum EventType {
    LOG_OUT
  }

  private EventType type;
  protected final Identity authId;
  protected boolean accessGranted;

  public AuthEvent(Identity authId) {
    super(GLOBAL);
    this.authId = authId;
  }

  public AuthEvent(Identity authId, boolean accessGranted) {
    this.authId = authId;
    this.accessGranted = accessGranted;
  }
}
