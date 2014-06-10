package serverBro.broServer.networking;

import java.io.ObjectOutputStream;
import java.net.Socket;

import serverBro.broShared.Identity;


/**
 * Used to identify the different socket streams. Acts as a link between Identity and the client connection stream.
 * 
 * @author Patrick
 */
public class ClientConnection {

  private final Socket clientSocket;
  private final ObjectOutputStream out;
  private Identity id;

  public ClientConnection(ObjectOutputStream out, Socket clientSocket) {
    this.out = out;
    this.clientSocket = clientSocket;
  }

  public Socket getClientSocket() {
    return clientSocket;
  }

  public ObjectOutputStream getOut() {
    return out;
  }

  public Identity getIdentity() {
    return id;
  }

  public void setIdentity(Identity id) {
    this.id = id;
  }
}
