package serverBro.broShared.events;

import serverBro.broShared.Controller;

public interface BroEvent {

  /**
   * Execute is executed on the RECEIVER side!
   */
  public void execute(Controller controller);

}
