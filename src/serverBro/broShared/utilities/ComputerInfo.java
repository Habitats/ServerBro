package serverBro.broShared.utilities;

import java.io.Serializable;
import java.util.List;



/**
 * Adapter class for computer info.
 * 
 * @author Patrick
 * 
 */
public class ComputerInfo implements Serializable, ComputerInfoInterface {
  private UptimeStats uptimeStats;
  private CpuStats cpuStats;
  private RamStats ramStats;
  private List<ComputerProcess> runningProcesses;
  private List<ComputerProcess> runningUserProcesses;


  public CpuStats getCpuStats() {
    return cpuStats;
  }

  public RamStats getRamStats() {
    return ramStats;
  }

  public UptimeStats getUptimeStats() {
    return uptimeStats;
  }

  public List<ComputerProcess> getRunningProcesses() {
    return runningProcesses;
  }

  public List<ComputerProcess> getRunningUserProcesses() {
    return runningUserProcesses;
  }

  public void setCpuStats(CpuStats cpuStats) {
    this.cpuStats = cpuStats;
  }

  public void setRamStats(RamStats ramStats) {
    this.ramStats = ramStats;
  }

  public void setRunningProcesses(List<ComputerProcess> runningProcesses) {
    this.runningProcesses = runningProcesses;
  }

  public void setRunningUserProcesses(List<ComputerProcess> runningUserProcesses) {
    this.runningUserProcesses = runningUserProcesses;
  }

  public void setUptimeStats(UptimeStats uptimeStats) {
    this.uptimeStats = uptimeStats;
  }
}
