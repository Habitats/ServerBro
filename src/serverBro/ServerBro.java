package serverBro;

import serverBro.client.ClientController;
import serverBro.events.AuthEvent;
import serverBro.events.BroadCastEvent;
import serverBro.server.ServerController;

public class ServerBro {
  private ServerBro() {}


  public static void main(String[] args) {
    ServerBro sb = new ServerBro();
    sb.init();
  }

  private void init() {
    ServerController server = new ServerController();
    ClientController client = new ClientController();
    Identity id = new Identity("mrherp");

    while (true) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      client.sendNetworkEvent(new AuthEvent(null, id));
      // server.evaluteOutgoing(new BroadCastEvent(null));
    }
  }
}
