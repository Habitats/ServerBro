package serverBro.broShared.utilities;

import java.io.Serializable;

public class NetworkStats implements Serializable{

  private int totalSent;
  private int totalReceived;

  public NetworkStats(int totalSent, int totalReceived) {
    this.totalSent = totalSent;
    this.totalReceived = totalReceived;
  }

  @Override
  public String toString() {
    return "Network (down/up): " + (int) (totalReceived  / 1024) + " / " + (int) (totalSent   / 1024) + " KBs";
  }
}
