package serverBro.swing;

import serverBro.broShared.BroModel;
import serverBro.broShared.view.BroGuiController;

/**
 * GUI controller for SWING implementation.
 * 
 * @author Patrick
 * 
 */
public class GuiControllerSwing extends BroGuiController {
  private final BroPanel view;
  private final BroFrame frame;

  public GuiControllerSwing() {
    view = new BroPanel(this);
    frame = new BroFrame(view);
  }

  @Override
  public void setModel(BroModel model) {
    super.model = model;
    model.addObserver(view);
  }
}
