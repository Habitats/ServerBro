package serverBro.events.networkEvents.diagnostic;

import java.util.ArrayList;

import serverBro.events.networkEvents.NetworkEvent;
import serverBro.utilities.ComputerInfo;
import serverBro.utilities.ComputerProcess;

public class ProcessEvent extends NetworkEvent {
  private ArrayList<ComputerProcess> processes;

  public ProcessEvent() {
    super(true, null);
    setProcesses(new ComputerInfo().getRunningProcesses());
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

  @Override
  public void execute() {
    getController().model.setProcesses(processes);
  }
}
