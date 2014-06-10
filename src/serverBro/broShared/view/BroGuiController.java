package serverBro.broShared.view;

import java.util.ArrayList;

import serverBro.broShared.BroModel;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.utilities.ComputerProcess;

public abstract class BroGuiController implements BroView {

  protected BroModel model;
  private BroViewListener controller;

  public BroGuiController() {}

  public void actionPerformed(ViewEvent e) {
    controller.actionPerformed(e);
  }

  public void setName(String name) {
    model.setName(name);
  }

  public void displayProcesses(ArrayList<ComputerProcess> processes) {
    model.setProcesses(processes);
  };

  public void displayNetworkStatus(String status) {
    model.setNetworkStatus(status);
  }

  @Override
  public void setBroViewListener(BroViewListener controller) {
    this.controller = controller;
  }

  @Override
  public abstract void setModel(BroModel model);
}
