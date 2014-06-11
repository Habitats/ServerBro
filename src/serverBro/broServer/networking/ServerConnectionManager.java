package serverBro.broServer.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import serverBro.broShared.Config;
import serverBro.broShared.Logger;


/**
 * The listening part of the server. It handles, delegates and accepts incoming connections.
 * 
 * @author Patrick
 * 
 */
public class ServerConnectionManager implements Runnable {
  private int port;
  private boolean listening;
  private ServerNetworkController serverController;
  private ServerSocket serverSocket;

  public ServerConnectionManager(int port, ServerNetworkController serverController) {
    this.port = port;
    this.serverController = serverController;
    listening = true;
  }

  private ServerSocket setUpServer(int port) {
    Logger.log("Setting up server on port: " + port);
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      Logger.error("Server already running! Skipping...", e);
    }
    return serverSocket;
  }

  private Socket listenForIncomfingConnections(ServerSocket serverSocket) {
    Logger.log("Listening for connections on port: " + port);
    Socket clientSocket = null;
    try {
      clientSocket = serverSocket.accept();
    } catch (IOException e) {
      Logger.error("ClientSocket dropped!", e);
    }
    return clientSocket;
  }

  @Override
  public void run() {

    Socket clientSocket;

    if ((serverSocket = setUpServer(port)) == null)
      return;

    while (Config.getInstance().isConnected()) {
      clientSocket = listenForIncomfingConnections(serverSocket);
      if (clientSocket != null) {
        startNetClientThread(clientSocket);
        String clientIp = clientSocket.getRemoteSocketAddress().toString().split("[/:]")[1];
        String localPort = clientSocket.getRemoteSocketAddress().toString().split("[/:]")[2];
        Logger.log("New client with IP: " + clientIp + " and local port: " + localPort);
      }
    }
  }

  private void startNetClientThread(Socket clientSocket) {
    ServerConnectionIncoming serverConnection = new ServerConnectionIncoming(clientSocket, serverController);
    Thread serverConnectionThread = new Thread(serverConnection);
    serverConnectionThread.start();
  }

  public ServerSocket getServerSocket() {
    return serverSocket;
  }
}
