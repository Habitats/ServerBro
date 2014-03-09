package serverBro.events;

import java.io.Serializable;

import serverBro.Identity;

public abstract class NetworkEvent implements Serializable {
  private static final long serialVersionUID = 1L;

  public final Identity id;
  public final boolean global;
  public final EventType type;

  public enum EventType {
    AUTH, DIAGNOSTIC, BROADCAST;
  }

  public NetworkEvent(Identity id, boolean global, EventType type) {
    this.id = id;
    this.global = global;
    this.type = type;
  }

  public Identity getSender() {
    return id;
  }

  public EventType getType() {
    return type;
  }
}
