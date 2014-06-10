package serverBro.broServer.networking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import serverBro.broShared.Config;
import serverBro.broShared.Controller;
import serverBro.broShared.Logger;
import serverBro.broShared.NetworkController;
import serverBro.broShared.events.external.NetworkEvent;

/**
 * The main network controller for the server, handles low level server activities
 * 
 * @author Patrick
 * 
 */
public class ServerNetworkController implements NetworkController {
  private ServerConnectionOutgoing serverConnectionOutgoing;
  private List<ClientConnection> clientConnections;
  private ServerConnectionManager serverConnectionManager;


  private Controller serverController;

  public ServerNetworkController(Controller serverController) {
    this.serverController = serverController;
  }


  public List<ClientConnection> getClientConnections() {
    return clientConnections;

  }


  @Override
  public void connect() {
    int port = Config.getInstance().getServerPort();

    clientConnections = new ArrayList<ClientConnection>();

    serverConnectionOutgoing = new ServerConnectionOutgoing(clientConnections);
    serverConnectionManager = new ServerConnectionManager(port, this);
    Thread serverThread = new Thread(serverConnectionManager);
    serverThread.start();
    Config.getInstance().setConnected(true);
  }

  @Override
  public void disconnect() {
    boolean isRunning = Config.getInstance().isConnected();
    if (isRunning) {
      Config.getInstance().setConnected(false);
      for (ClientConnection clientConnection : clientConnections) {
        try {
          Logger.log("Disconnecting " + clientConnection.getIdentity().getUsername());
          clientConnection.getClientSocket().close();
          clientConnection.getOut().close();
        } catch (IOException e) {
        }
      }
      try {
        Logger.log("Closing server socket...");
        serverConnectionManager.getServerSocket().close();
      } catch (IOException e) {
      }
      Logger.log("Server disconnected!");
    } else {
      Logger.log("Unable to disconnect. Server already offline!");
    }
  }


  @Override
  public void sendEvent(NetworkEvent event) {
    if (Config.getInstance().isConnected()) {
      serverConnectionOutgoing.sendEvent(event);
    } else {
      Logger.log("Unable to send network event. Not connected!");
    }
  }


  @Override
  public void evaluateIncoming(NetworkEvent event) {
    serverController.incomingEvent(event);
  }
}
