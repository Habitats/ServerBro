package serverBro.broShared;

/**
 * Listener interface for objects that want to listen to changes in the main model.
 * 
 * @author Patrick
 * 
 */
public interface BroModelListener {

  public void processesChanged();

  public void messageAdded(String message);

}
