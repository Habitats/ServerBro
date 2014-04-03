package serverBro.events;

import java.util.ArrayList;

import javax.sound.midi.ControllerEventListener;

import serverBro.Identity;
import serverBro.utilities.ComputerInfo;
import serverBro.utilities.ComputerProcess;
import serverBro.client.ClientController;

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
