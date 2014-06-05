package serverBro.events.networkEvents.authentication;

import serverBro.Identity;
import serverBro.broServer.ServerController;
import serverBro.events.EventFactory;
import serverBro.events.networkEvents.NetworkEvent;

public abstract class AuthEvent extends NetworkEvent {
  private static final long serialVersionUID = 1406998928869635813L;

  public enum EventType {
    LOG_OUT
  }

  private EventType type;
  private final static boolean GLOBAL = false;
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

  @Override
  public String toString() {
    return "AUTH EVENT from " + senderId.getUsername();
  }

}
