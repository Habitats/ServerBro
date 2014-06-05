package serverBro.events.networkEvents.authentication;

import serverBro.Identity;

public class AccessDeniedEvent extends AuthEvent {

  public AccessDeniedEvent(Identity authId) {
    super(authId);
  }

  @Override
  public void execute() {
    getController().model.setNetworkStatus("access denied!");
  }
}
