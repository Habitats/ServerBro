package serverBro.events;

import java.io.Serializable;

import serverBro.Authenticator;
import serverBro.Config;
import serverBro.Identity;
import serverBro.NetworkController;

public abstract class NetworkEvent implements Serializable, Event {
  private static final long serialVersionUID = 1L;

  public final Identity senderId;
  public final boolean global;

  private final Identity receiver;

  private NetworkController controller;

  public NetworkEvent(boolean global) {
    this(global, null);
  }

  public NetworkEvent(boolean global,  Identity receiver) {
    this.receiver = receiver;
    this.senderId = Config.getInstance().getId();
    this.global = global;
  }

  public Identity getSender() {
    return senderId;
  }

  public void setController(NetworkController controller) {
    this.controller = controller;
  }

  public NetworkController getController() {
    return controller;
  }
}
