package serverBro.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observer;

import serverBro.Config;
import serverBro.NetworkController;
import serverBro.events.AuthEvent;
import serverBro.utilities.ComputerProcess;

public class BroController implements ActionListener {

  private BroModel model;
  private BroView view;
  private BroFrame frame;
  private NetworkController networkController;

  public BroController() {
    model = new BroModel();
    view = new BroPanel();

    frame = new BroFrame((BroPanel) view);

    model.addObserver((Observer) view);

    view.addController(this);
  }

  public void setNetworkController(NetworkController networkController) {
    this.networkController = networkController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand());
    networkController.sendNetworkEvent(new AuthEvent(null));
  }

  public void setName(String name) {
    model.setName(name);

  }

  public void displayProcesses(ArrayList<ComputerProcess> processes) {
    model.setProcesses(processes);
  };

  public void displayNetworkStatus(String status) {
    model.setNetworkStatus(status);
  };

}
