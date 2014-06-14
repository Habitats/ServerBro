package serverBro.broShared.utilities;

import java.io.Serializable;
import java.util.ArrayList;
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

  private List<String> simpleInfoList;
  private NetworkStats networkStats;

  public ComputerInfo() {
    simpleInfoList = new ArrayList<String>();
  }


  @Override
  public CpuStats getCpuStats() {
    return cpuStats;
  }

  @Override
  public RamStats getRamStats() {
    return ramStats;
  }

  @Override
  public UptimeStats getUptimeStats() {
    return uptimeStats;
  }

  @Override
  public List<ComputerProcess> getRunningProcesses() {
    return runningProcesses;
  }

  @Override
  public List<ComputerProcess> getRunningUserProcesses() {
    return runningUserProcesses;
  }

  public void setCpuStats(CpuStats cpuStats) {
    this.cpuStats = cpuStats;
    simpleInfoList.add(cpuStats.toString());
  }

  public void setRamStats(RamStats ramStats) {
    this.ramStats = ramStats;
    simpleInfoList.add(ramStats.toString());
  }

  public void setRunningProcesses(List<ComputerProcess> runningProcesses) {
    this.runningProcesses = runningProcesses;
  }

  public void setRunningUserProcesses(List<ComputerProcess> runningUserProcesses) {
    this.runningUserProcesses = runningUserProcesses;
  }

  public void setUptimeStats(UptimeStats uptimeStats) {
    this.uptimeStats = uptimeStats;
    simpleInfoList.add(uptimeStats.toString());
  }

  public ArrayList<String> getSimpleInfoList() {
    return (ArrayList<String>) simpleInfoList;
  }

  public List<String> getSimpleProcessInfo() {
    List<String> simpleList = new ArrayList<String>();
    for (ComputerProcess process : runningProcesses) {
      simpleList.add(process.toString());
    }
    return simpleList;
  }

  public void setNetworkStats(NetworkStats networkStats) {
    this.networkStats = networkStats;
    simpleInfoList.add(networkStats.toString());
  }

  @Override
  public NetworkStats getNetworkStats() {
    return networkStats;
  }
}
