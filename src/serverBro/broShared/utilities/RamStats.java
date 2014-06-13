package serverBro.broShared.utilities;

import java.io.Serializable;

public class RamStats implements Serializable {

  private long freeRam;
  private long totalRam;
  private long usedRam;

  public RamStats(long freeRam, long totalRam, long usedRam) {
    this.freeRam = freeRam;
    this.totalRam = totalRam;
    this.usedRam = usedRam;
  }

  @Override
  public String toString() {
    int toMb = (int) Math.pow(1024, 2);
    return "RAM usage: " + usedRam / toMb + "/" + totalRam / toMb + " MB";
  }
}
