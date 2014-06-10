package serverBro.broShared.events;

import serverBro.broShared.Controller;

/**
 * Very general event interface. All events should implement it to ensure polymorphism.
 * 
 * @author Patrick
 * 
 */
public interface BroEvent {

  /**
   * Execute is executed on the RECEIVER side!
   */
  public void execute(Controller controller);

}
