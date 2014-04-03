package serverBro;

public class Authenticator {

  private static Authenticator instance;

  public static Authenticator getInstance() {
    if (instance == null) {
      instance = new Authenticator();
    }
    return instance;
  }

  public boolean authenticateUser(Identity id) {
    return true;
  }
}
