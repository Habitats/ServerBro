package serverBro.events;

import java.io.Serializable;

import serverBro.Identity;

public abstract class NetworkEvent implements Serializable {
  private static final long serialVersionUID = 1L;

  public final Identity id;
  public final boolean global;

  public NetworkEvent(Identity id, boolean global) {
    this.id = id;
    this.global = global;
  }

  public Identity getSender() {
    return id;
  }
}
