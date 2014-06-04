package serverBro.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

import serverBro.Controller;
import serverBro.events.EventFactory;
import serverBro.gui.BroModel;
import serverBro.gui.BroView;
import serverBro.gui.BroViewListener;
import serverBro.utilities.ComputerProcess;

public class BroGui implements ActionListener, BroView {

  private BroModel model;
  private BroPanel view;
  private BroFrame frame;
  private BroViewListener controller;

  public BroGui() {
    view = new BroPanel();
    frame = new BroFrame((BroPanel) view);
    view.setController(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand());
    controller.actionPerformed(EventFactory.createViewEvent(e, (Controller) controller));
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
    model.addObserver((Observer) view);
    this.model = model;
  }

  @Override
  public void setBroViewListener(BroViewListener controller) {
    this.controller = controller;
  }
}
