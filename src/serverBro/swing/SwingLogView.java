package serverBro.swing;

import serverBro.broShared.Logger;
import serverBro.broShared.view.LogView;

public class SwingLogView extends ContinuousFeedArea implements LogView {

  public SwingLogView() {
    Logger.setLogView(this);
  }

  @Override
  public void add(String log) {
    super.append(log + "\n");
  }
}
