package serverBro.events;

import serverBro.Identity;
import serverBro.utilities.ComputerInfo;

public class DiagnosisServerEvent extends DiagnosisEvent {
  public DiagnosisServerEvent(boolean global, Identity senderId) {
    super(global, senderId);
  }

  @Override
  public void onReceive() {
    setProcesses(new ComputerInfo().getRunningProcesses());
  }

  @Override
  public void onSend() {}
}
