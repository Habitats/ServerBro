package serverBro.client;

import serverBro.Config;
import serverBro.Logger;
import serverBro.events.NetworkEvent;

public class ClientController {
  private ClientOutgoing clientOutgoing;

  public ClientController() {
    int port = Config.getInstance().getServerPort();
    String hostname = Config.getInstance().getServerHostName();

    clientOutgoing = new ClientOutgoing();
    Thread clientThread = new Thread(new ClientIncoming(port, hostname, this));
    clientThread.start();
  }

  public void evaluateIncoming(NetworkEvent event) {
    Logger.log("CLIENT CONTROLLER GOT EVENT");
  }

  public synchronized void sendNetworkEvent(NetworkEvent event) {
    clientOutgoing.sendNetworkEvent(event);
  }


  public void setServerConnection(ServerConnection serverConnection) {
    clientOutgoing.setServerConnection(serverConnection);
  }
}
