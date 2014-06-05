package serverBro.broShared.view;

import serverBro.broShared.events.internal.ViewEvent;

public interface BroViewListener {
  public void actionPerformed(ViewEvent e);

}
