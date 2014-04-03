package serverBro.events;

import java.io.Serializable;

import serverBro.Authenticator;
import serverBro.Config;
import serverBro.Identity;

public abstract class NetworkEvent implements Serializable, Event {
  private static final long serialVersionUID = 1L;

  public final Identity senderId;
  public final boolean global;
  public final EventType type;

  private final Identity receiver;

  public enum EventType {
    AUTH, DIAGNOSTIC, BROADCAST;
  }

  public NetworkEvent(boolean global, EventType type) {
    this(global, type, null);
  }

  public NetworkEvent(boolean global, EventType type, Identity receiver) {
    this.receiver = receiver;
    this.senderId = Config.getInstance().getId();
    this.global = global;
    this.type = type;
  }

  public Identity getSender() {
    return senderId;
  }

  public EventType getType() {
    return type;
  }

  @Override
  public void onReceive() {
    executeIncoming();
  }

  @Override
  public void onSend() {
    executeOutgoing();
  }

  public abstract void executeIncoming();

  public abstract void executeOutgoing();
}
