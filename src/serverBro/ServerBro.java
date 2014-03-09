package serverBro;

import serverBro.client.ClientController;
import serverBro.gui.BroController;
import serverBro.server.ServerController;

public class ServerBro {
  private ServerBro() {}


  public static void main(String[] args) {
    ServerBro sb = new ServerBro();
    sb.init();
  }

  private void init() {
    ClientController client = null;
    ServerController server = null;
    if (Config.getInstance().isServer()) {
      BroController serverBro = new BroController();
      server = new ServerController(serverBro);
      serverBro.setNetworkController(server);
    }
    if (Config.getInstance().isClient()) {
      BroController clientBro = new BroController();
      client = new ClientController(clientBro);
      clientBro.setNetworkController(client);
    }
  }
}
