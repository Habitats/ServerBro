package serverBro;

import serverBro.events.NetworkEvent;

public interface NetworkController {
  public void evaluteOutgoing(NetworkEvent event);

  public void evaluateIncoming(NetworkEvent event);

  public void sendNetworkEvent(NetworkEvent event);
}
