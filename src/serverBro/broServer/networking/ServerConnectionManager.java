package serverBro.broServer.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import serverBro.Config;
import serverBro.Logger;


public class ServerConnectionManager implements Runnable {
  private int port;
  private boolean listening;
  private ServerNetworkController serverController;

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
      Logger.log("Server already running! Skipping...");
    }
    return serverSocket;
  }

  private Socket listenForIncomfingConnections(ServerSocket serverSocket) {
    Logger.log("Listening for connections on port: " + port);
    Socket socket = null;
    try {
      socket = serverSocket.accept();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return socket;
  }

  @Override
  public void run() {

    ServerSocket serverSocket;
    Socket clientSocket;

    if ((serverSocket = setUpServer(port)) == null)
      return;

    while (Config.getInstance().isNetworkEnabled()) {
      clientSocket = listenForIncomfingConnections(serverSocket);
      startNetClientThread(clientSocket);
      String clientIp = clientSocket.getRemoteSocketAddress().toString().split("[/:]")[1];
      String localPort = clientSocket.getRemoteSocketAddress().toString().split("[/:]")[2];
      Logger.log("New client with IP: " + clientIp + " and local port: " + localPort);
    }
  }

  public void setListening(boolean listening) {
    this.listening = listening;
  }

  private void startNetClientThread(Socket clientSocket) {
    ServerConnectionIncoming serverConnection = new ServerConnectionIncoming(clientSocket, serverController);
    Thread serverConnectionThread = new Thread(serverConnection);
    serverConnectionThread.start();
  }


}
