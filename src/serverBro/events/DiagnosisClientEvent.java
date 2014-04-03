package serverBro.events;

import serverBro.Identity;
import serverBro.client.ClientController;

public class DiagnosisClientEvent extends DiagnosisEvent {

  public DiagnosisClientEvent(boolean global, Identity senderId) {
    super(global, senderId);
  }

  @Override
  public void onReceive() {
    ((ClientController) getController()).getBroController().displayProcesses(getProcesses());
  }

  @Override
  public void onSend() {
  }
}
