package serverBro.broClient.networking;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles the clients connection to the server.
 * 
 * @author Patrick
 * 
 */
public class ServerConnection {

  private final ObjectOutputStream out;
  private final Socket socket;

  public ServerConnection(ObjectOutputStream out, Socket socket) {
    this.out = out;
    this.socket = socket;
  }

  public ObjectOutputStream getOut() {
    return out;
  }

}
