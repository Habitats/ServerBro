package serverBro.events;

import serverBro.Identity;

public class BroadCastEvent extends NetworkEvent {
  private final static boolean GLOBAL = true;

  public BroadCastEvent() {
    super(GLOBAL, EventType.BROADCAST);
  }


  @Override
  public String toString() {
    return "GLOBAL EVENT!";
  }


  @Override
  public void executeIncoming() {
    // TODO Auto-generated method stub

  }


  @Override
  public void executeOutgoing() {
    // TODO Auto-generated method stub

  }
}
