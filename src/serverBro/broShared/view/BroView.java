package serverBro.broShared.view;

import serverBro.broShared.BroModel;


public interface BroView {

  public void setModel(BroModel model);

  public void setBroViewListener(BroViewListener listener);
}
