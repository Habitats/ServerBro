package serverBro.events.networkEvents;

import serverBro.Identity;
import serverBro.broServer.ServerController;

public class AuthEvent extends NetworkEvent {
  private static final long serialVersionUID = 1406998928869635813L;

  public enum EventType {
    LOG_OUT
  }

  private EventType type;
  private final static boolean GLOBAL = false;
  private final Identity authId;

  public AuthEvent(Identity authId) {
    super(GLOBAL);
    this.authId = authId;
  }

  @Override
  public String toString() {
    return "AUTH EVENT from " + senderId.getUsername();
  }

  @Override
  public void execute() {
    ((ServerController) getController()).getAuthenticator().authenticateUser(authId);
  }
}
