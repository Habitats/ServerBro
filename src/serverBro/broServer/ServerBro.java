package serverBro.broServer;

import serverBro.broClient.ClientController;
import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.swing.GuiControllerSwing;

/**
 * Main class for ServerBro 
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
      Controller server = new ServerController(new GuiControllerSwing());
    }
    if (Config.getInstance().isClient()) {
//      Controller client = new ClientController(new GuiControllerSwing());
    }
  }
}
