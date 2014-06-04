package serverBro.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import serverBro.utilities.ComputerProcess;

public class BroModel extends Observable {

  private ArrayList<ComputerProcess> processes;
  private String networkStatus;
  private String name;
  private ArrayList<String> messages;

  public BroModel() {
    messages = new ArrayList<String>();
  }

  public void setNetworkStatus(String networkStatus) {
    this.networkStatus = networkStatus;
    update();
  }

  public void setProcesses(ArrayList<ComputerProcess> processes) {
    this.processes = processes;
    update();
  }

  public void setName(String name) {
    this.name = name;
    update();
  }

  public void addMessage(String message) {
    messages.add(message);
    update();
  }

  private void update() {
    setChanged();
    notifyObservers();
  }

  public ArrayList<String> getMessages() {
    return messages;
  }

  public String getName() {
    return name;
  }

  public String getNetworkStatus() {
    return networkStatus;
  }

  public ArrayList<ComputerProcess> getProcesses() {
    return processes;
  }
}
