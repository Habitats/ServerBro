package serverBro.events.networkEvents;

import serverBro.Identity;
import serverBro.utilities.ComputerInfo;

public class DiagnosisServerEvent extends DiagnosisEvent {
  public DiagnosisServerEvent(boolean global, Identity senderId) {
    super(global, senderId);
  }

  @Override
  public void execute() {
    setProcesses(new ComputerInfo().getRunningProcesses());
  }
}
