package serverBro.broShared.events.external.authentication;

import serverBro.broShared.Controller;
import serverBro.broShared.Identity;
import serverBro.broShared.events.external.MessageEvent;
import serverBro.broShared.events.internal.MessageButtonEvent;

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
