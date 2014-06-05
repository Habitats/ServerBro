package serverBro.broShared;

import serverBro.broShared.events.external.NetworkEvent;

public interface NetworkController {
  public void sendEvent(NetworkEvent event);

  public void evaluateIncoming(NetworkEvent event);

  public void connect();

  public void disconnect();
}
