package serverBro.broShared.events.external;

import java.io.Serializable;

import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.broShared.Identity;
import serverBro.broShared.events.BroEvent;

public abstract class NetworkEvent implements Serializable, BroEvent {
  private static final long serialVersionUID = 1L;
  public static final int GLOBAL = 0;
  public static final int PRIVATE = 1;

  public final int messageType;
  public final Identity senderId;
  private final Identity receiver;

  private Controller controller;

  public NetworkEvent() {
    this(PRIVATE);
  }

  public NetworkEvent(int global) {
    this(global, null);
  }

  public NetworkEvent(int global, Identity receiver) {
    this.receiver = receiver;
    this.senderId = Config.getInstance().getId();
    this.messageType = global;
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
