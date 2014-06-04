package serverBro;

import serverBro.events.networkEvents.NetworkEvent;
import serverBro.gui.BroModel;
import serverBro.gui.BroView;
import serverBro.gui.BroViewListener;



public abstract class Controller implements BroViewListener {

  public final BroView view;
  public final BroModel model;

  public Controller() {
    // startService();
    view = createView();
    model = new BroModel();
    view.setModel(model);
  }

  public abstract void incomingEvent(NetworkEvent event);

  public abstract void sendEvent(NetworkEvent event);

  public abstract void startService();

  public abstract void stopService();

  protected abstract BroView createView();

}
