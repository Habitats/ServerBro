package serverBro.events.interaction;

import serverBro.Controller;
import serverBro.events.BroEvent;

public abstract class ViewEvent implements BroEvent {

  protected final Controller controller;

  public ViewEvent(Controller controller) {
    this.controller = controller;
  }

}
