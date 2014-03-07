package serverBro;

import java.io.Serializable;

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
