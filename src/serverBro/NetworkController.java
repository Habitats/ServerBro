package serverBro;

import serverBro.events.networkEvents.NetworkEvent;

public interface NetworkController {
  public void sendEvent(NetworkEvent event);

  public void evaluateIncoming(NetworkEvent event);

  public void sendNetworkEvent(NetworkEvent event);

  public void connect();

  public void disconnect();
}
