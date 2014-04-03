package serverBro.events;

import java.util.ArrayList;

import serverBro.Identity;
import serverBro.utilities.ComputerProcess;

public class DiagnosisEvent extends NetworkEvent {

  private final ArrayList<ComputerProcess> processes;

  public DiagnosisEvent(boolean global, ArrayList<ComputerProcess> processes, Identity senderId) {
    super(global, EventType.DIAGNOSTIC,senderId);
    this.processes = processes;
  }

  public ArrayList<ComputerProcess> getProcesses() {
    return processes;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ComputerProcess process : processes) {
      sb.append(process.toString() + "\n");
    }
    return sb.toString();
  }

  @Override
  public void executeIncoming() {}

  @Override
  public void executeOutgoing() {}
}
