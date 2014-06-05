package serverBro.broServer;

import java.util.HashMap;
import java.util.Map;

import serverBro.Config;
import serverBro.Identity;
import serverBro.events.networkEvents.NetworkEvent;

public class Authenticator {
  private Map<String, Identity> authenticatedUsers;

  public Authenticator() {
    authenticatedUsers = new HashMap<String, Identity>();
    authenticatedUsers.put(Config.getInstance().getId().getUsername(), Config.getInstance().getId());
  }

  public boolean authenticate(NetworkEvent event) {
    Identity sender = event.getSender();
    if (authenticatedUsers.containsKey(sender.getUsername())) {
      return true;
    }
    return false;
  }
}
