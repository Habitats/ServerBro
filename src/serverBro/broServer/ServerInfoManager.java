package serverBro.broServer;

import java.util.List;

import serverBro.broServer.utilities.ComputerInfoWindows;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.utilities.ComputerInfo;
import serverBro.broShared.utilities.ComputerProcess;

public class ServerInfoManager {

  public List<ComputerProcess> getRunningProcesses() {
    return new ComputerInfoWindows().getRunningProcesses();
  }

  public ComputerInfo createComputerInfo(NetworkEvent event) {
    ComputerInfoWindows computerInfoWindows = new ComputerInfoWindows();
    ComputerInfo computerInfo = new ComputerInfo();
    computerInfo.setCpuStats(computerInfoWindows.getCpuStats());
    computerInfo.setRamStats(computerInfoWindows.getRamStats());
    computerInfo.setUptimeStats(computerInfoWindows.getUptimeStats());
    computerInfo.setRunningProcesses(computerInfoWindows.getRunningProcesses());
    computerInfo.setRunningUserProcesses(computerInfoWindows.getRunningUserProcesses());
    computerInfo.setNetworkStats(computerInfoWindows.getNetworkStats());
    // TODO Auto-generated method stub
    return computerInfo;
  }
}
