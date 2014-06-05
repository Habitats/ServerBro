package serverBro.broShared;

public interface BroModelListener {

  public void processesChanged();

  public void messageAdded(String message);

}
