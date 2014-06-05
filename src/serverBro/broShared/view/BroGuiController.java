package serverBro.broShared.view;

import java.util.ArrayList;

import serverBro.broShared.BroModel;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.utilities.ComputerProcess;
import serverBro.swing.BroFrame;
import serverBro.swing.BroPanel;

public class BroGuiController implements BroView {

  private BroModel model;
  private BroPanel view;
  private BroFrame frame;
  private BroViewListener controller;

  public BroGuiController() {
    view = new BroPanel(this);
    frame = new BroFrame(view);
  }

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
  public void setModel(BroModel model) {
    model.addObserver(view);
    this.model = model;
  }

  @Override
  public void setBroViewListener(BroViewListener controller) {
    this.controller = controller;
  }
}
