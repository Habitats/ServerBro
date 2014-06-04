package serverBro.events.networkEvents;

import java.util.ArrayList;

import serverBro.Identity;
import serverBro.utilities.ComputerProcess;

public abstract class DiagnosisEvent extends NetworkEvent {

  private ArrayList<ComputerProcess> processes;

  public DiagnosisEvent(boolean global, Identity senderId) {
    super(global, senderId);
  }

  public ArrayList<ComputerProcess> getProcesses() {
    return processes;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ComputerProcess process : getProcesses()) {
      sb.append(process.toString() + "\n");
    }
    return sb.toString();
  }


  public void setProcesses(ArrayList<ComputerProcess> processes) {
    this.processes = processes;
  }
}
