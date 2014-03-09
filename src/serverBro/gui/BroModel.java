package serverBro.gui;

import java.util.ArrayList;
import java.util.Observable;

import serverBro.utilities.ComputerProcess;

public class BroModel extends Observable {

  private ArrayList<ComputerProcess> processes;
  private String networkStatus;
  private String name;

  public void setNetworkStatus(String networkStatus) {
    this.networkStatus = networkStatus;
    setChanged();
    notifyObservers(networkStatus);
  }

  public void setProcesses(ArrayList<ComputerProcess> processes) {
    this.processes = processes;
    setChanged();
    notifyObservers(processes);
  }

  public String getNetworkStatus() {
    return networkStatus;
  }

  public ArrayList<ComputerProcess> getProcesses() {
    return processes;
  }

  public void setName(String name) {
    this.name = name;
    setChanged();
    notifyObservers(name);
  }
}
