package serverBro.events.networkEvents;

import serverBro.Identity;

public class DiagnosisClientEvent extends DiagnosisEvent {

  public DiagnosisClientEvent(boolean global, Identity senderId) {
    super(global, senderId);
  }

  @Override
  public void execute() {
    // ((ClientNetworkController)
    // getController()).getBroController().displayProcesses(getProcesses());
  }
}
