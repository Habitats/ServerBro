package serverBro.broShared.utilities;

import java.util.List;

public interface ComputerInfoInterface {

  public CpuStats getCpuStats();

  public RamStats getRamStats();

  public UptimeStats getUptimeStats();

  public List<ComputerProcess> getRunningProcesses();

  public List<ComputerProcess> getRunningUserProcesses();

  public NetworkStats getNetworkStats();

}
