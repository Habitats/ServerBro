package serverBro.broShared;

import serverBro.broClient.ClientController;
import serverBro.broServer.ServerController;

/**
 * Main class for ParserBro
 * 
 * @author Patrick Skjennum
 * 
 */
public class ServerBro {
  private ServerBro() {}


  public static void main(String[] args) {
    ServerBro sb = new ServerBro();
    sb.init();
  }

  private void init() {
    if (Config.getInstance().isServer()) {
      Controller server = new ServerController();
    }
    if (Config.getInstance().isClient()) {
      Controller client = new ClientController();
    }
  }
}
