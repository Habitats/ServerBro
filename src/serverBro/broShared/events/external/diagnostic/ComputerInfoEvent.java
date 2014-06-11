package serverBro.broShared.events.external.diagnostic;

import serverBro.broShared.Controller;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.utilities.ComputerInfo;

public class ComputerInfoEvent extends NetworkEvent {
  private ComputerInfo computerInfo;

  public ComputerInfoEvent(Controller controller) {
    computerInfo = controller.generateComputerInfo(this);
  }

  // @Override
  // public String toString() {
  // StringBuilder sb = new StringBuilder();
  // for (ComputerProcess process : getProcesses()) {
  // sb.append(process.toString() + "\n");
  // }
  // return sb.toString();
  // }

  @Override
  public void execute(Controller controller) {
    controller.model.setComputerInfo(computerInfo);
  }
}
