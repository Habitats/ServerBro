package serverBro;

import serverBro.broServer.ServerController;
import serverBro.broShared.Controller;
import serverBro.broShared.misc.Config;
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
    Config.getInstance().loadProperties("serverbro.properties");
    Config.getInstance().loadSecretKey("secret_key");

    if (Config.getInstance().isServer()) {
      Controller server = new ServerController(new GuiControllerSwing());
    }
    if (Config.getInstance().isClient()) {
      // Controller client = new ClientController(new GuiControllerSwing());
    }
  }
}
