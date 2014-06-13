package serverBro.broShared.events.external.authentication;

import serverBro.broShared.Controller;
import serverBro.broShared.Identity;

public class AccessDeniedEvent extends AuthEvent {

  public AccessDeniedEvent(Identity authId) {
    super(authId);
  }

  @Override
  public void execute(Controller controller) {
    controller.model.addMessage("Access denied! Disconnecting...");
    controller.stopService();
  }
}
