package serverBro.broShared;

import java.io.Serializable;

/**
 * Identity class used to identify and maintain information about concrete users.
 * 
 * @author Patrick
 * 
 */
public class Identity implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String username;

  public Identity(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
