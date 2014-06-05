package serverBro.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import serverBro.utilities.ComputerProcess;

public class BroModel extends Observable {

  private List<ComputerProcess> processes;
  private List<String> messages;
  private String lastMessage;
  private String networkStatus;
  private String name;

  public BroModel() {
    messages = new ArrayList<String>();
    processes = new ArrayList<ComputerProcess>();
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
    // keep access to last message for quick access
    lastMessage = message;
    update();
  }

  private void update() {
    setChanged();
    notifyObservers();
  }

  public List<String> getMessages() {
    return messages;
  }

  public String getLastMessage() {
    return lastMessage;
  }

  public String getName() {
    return name;
  }

  public String getNetworkStatus() {
    return networkStatus;
  }

  public List<ComputerProcess> getProcesses() {
    return processes;
  }
}
