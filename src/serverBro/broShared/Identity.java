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

  private String password;

  public Identity(String username, String password) {
    this.username = username.trim();
    this.password = password.trim();
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
