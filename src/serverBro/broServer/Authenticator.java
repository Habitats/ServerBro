package serverBro.broServer;

import java.util.HashMap;
import java.util.Map;

import serverBro.broShared.Identity;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.misc.Config;

/**
 * Authentication services.
 * 
 * @author Patrick
 * 
 */
public class Authenticator {
  private Map<String, Identity> authenticatedUsers;

  public Authenticator() {
    loadTrustedIdentities();
  }
  
  public Map<String, Identity> loadTrustedIdentities(){
    authenticatedUsers = new HashMap<String, Identity>();
    Identity herp = new Identity("mrherp", "dicks");
    Identity hab = new Identity("habitats", "dicks");
    authenticatedUsers.put(herp.getUsername(),herp);
    authenticatedUsers.put(hab.getUsername(), hab);
    
    return authenticatedUsers;
  }

  public boolean authenticate(NetworkEvent event) {
    Identity sender = event.getSender();
    if (authenticatedUsers.containsKey(sender.getUsername().toLowerCase())) {
      if (authenticatedUsers.get(sender.getUsername()).getPassword().equals(sender.getPassword()))
        return true;
    }
    return false;
  }
}
