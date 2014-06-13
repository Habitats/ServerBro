package serverBro.broShared.view;

import serverBro.broShared.BroModel;
import serverBro.broShared.events.internal.ViewEvent;

public abstract class BroGuiController implements BroView {

  protected BroModel model;
  private BroViewListener controller;

  public BroGuiController() {}

  public void actionPerformed(ViewEvent e) {
    controller.actionPerformed(e);
  }

  public void setName(String name) {
    model.setName(name);
  }

  @Override
  public void setBroViewListener(BroViewListener controller) {
    this.controller = controller;
  }

  @Override
  public abstract void setModel(BroModel model);
}
