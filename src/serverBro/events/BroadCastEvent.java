package serverBro.events;

import serverBro.Identity;

public class BroadCastEvent extends NetworkEvent {
  private final static boolean GLOBAL = true;

  public BroadCastEvent(Identity id) {
    super(id, GLOBAL);
  }


  @Override
  public String toString() {
    return "GLOBAL EVENT!";
  }
}
