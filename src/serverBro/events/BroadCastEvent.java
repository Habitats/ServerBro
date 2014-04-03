package serverBro.events;

import serverBro.Identity;

public class BroadCastEvent extends NetworkEvent {
  private final static boolean GLOBAL = true;

  public BroadCastEvent() {
    super(GLOBAL );
  }


  @Override
  public String toString() {
    return "GLOBAL EVENT!";
  }


  @Override
  public void onReceive() {}


  @Override
  public void onSend() {}
}
