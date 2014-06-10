package serverBro.broShared.events.external.diagnostic;

import java.util.ArrayList;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.utilities.ComputerProcess;

public class ProcessEvent extends NetworkEvent {
  private ArrayList<ComputerProcess> processes;

  public ProcessEvent() {
    super();
    // setProcesses(new ComputerInfo().getRunningProcesses());
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
  public void execute(Controller controller) {
    controller.model.setProcesses(processes);
  }
}
