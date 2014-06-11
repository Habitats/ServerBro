package serverBro.broShared;

import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.utilities.ComputerInfo;
import serverBro.broShared.view.BroGuiController;
import serverBro.broShared.view.BroView;
import serverBro.broShared.view.BroViewListener;



/**
 * Abstract controller implementation for handling incoming and outgoing events, as well as starting
 * and stopping the application services.
 * 
 * @author Patrick
 * 
 */
public abstract class Controller implements BroViewListener {

  public final BroView view;
  public final BroModel model;

  public Controller(BroGuiController view) {
    // startService();
    this.view = view;
    model = new BroModel();
    view.setBroViewListener(this);
    view.setModel(model);
  }

  public abstract ComputerInfo generateComputerInfo(NetworkEvent event);

  public abstract void incomingEvent(NetworkEvent event);

  public abstract void sendEvent(NetworkEvent event);

  public abstract void startService();

  public abstract void stopService();



}
