package serverBro.broShared.utilities;

import java.io.Serializable;

public class CpuStats implements Serializable {

  private double cpuLoad;

  public CpuStats(double cpuLoad) {
    this.cpuLoad = cpuLoad;
  }

  @Override
  public String toString() {
    return "CPU load: " + cpuLoad + " %";
  }
}
