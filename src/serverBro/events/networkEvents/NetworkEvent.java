package serverBro.events.networkEvents;

import java.io.Serializable;

import serverBro.Config;
import serverBro.Controller;
import serverBro.Identity;
import serverBro.events.BroEvent;

public abstract class NetworkEvent implements Serializable, BroEvent {
  private static final long serialVersionUID = 1L;

  public final Identity senderId;
  public final boolean global;

  private final Identity receiver;

  private Controller controller;

  public NetworkEvent() {
    this(false);
  }

  public NetworkEvent(boolean global) {
    this(global, null);
  }

  public NetworkEvent(boolean global, Identity receiver) {
    this.receiver = receiver;
    this.senderId = Config.getInstance().getId();
    this.global = global;
  }

  public Identity getSender() {
    return senderId;
  }

  public void setController(Controller controller) {
    this.controller = controller;
  }

  public Controller getController() {
    return controller;
  }
}
