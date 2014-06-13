package serverBro.broShared;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import serverBro.broShared.misc.Logger;
import serverBro.broShared.utilities.ComputerInfo;

/**
 * Main data model for the application. Contains information passed between the client and the
 * server.
 * 
 * @author Patrick
 * 
 */
public class BroModel extends Observable {

  private List<String> messages;
  private String lastMessage = "lastMessage not set";
  private String name = "name not set";
  private ComputerInfo computerInfo;

  public BroModel() {
    messages = new ArrayList<String>();
  }

  public void setName(String name) {
    this.name = name;
    update();
  }

  public void setComputerInfo(ComputerInfo computerInfo) {
    this.computerInfo = computerInfo;
    update();
  }

  public void addMessage(String message) {
    if (message.length() == 0) {
      Logger.log("Trying to add zero length message. Skipping!");
      return;
    }
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

  public ComputerInfo getComputerInfo() {
    return computerInfo;
  }

  public void eventReceived() {
    update();
  }
}
