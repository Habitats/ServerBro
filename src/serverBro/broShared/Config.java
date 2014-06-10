package serverBro.broShared;

import java.util.Properties;

/**
 * Handles configutation of the application.
 * 
 * @author Patrick
 * 
 */
public class Config {

  private static Config instance;


  private Config() {
    properties = ConfigLoader.loadConfig();
    id = new Identity("mrherp");

    initCustomConfig();
  }

  // ########### SINGLETON #######################################


  public synchronized static Config getInstance() {
    if (instance == null)
      instance = new Config();
    return instance;
  }


  // ########### INIT CONFIG #######################################
  public Properties getProperties() {
    return properties;
  }


  private final Properties properties;

  // ########### CUSTOM STUFF #######################################
  private void initCustomConfig() {
    serverPort = Integer.parseInt(properties.getProperty("server_port"));
    loginEnabled = Boolean.parseBoolean(properties.getProperty("login_enabled"));
    serverHostname = properties.getProperty("server_hostname");
    server = Boolean.parseBoolean(properties.getProperty("server"));
    client = Boolean.parseBoolean(properties.getProperty("client"));
  }

  // GENERAL
  private boolean server;
  private boolean client;
  private Identity id;
  private boolean networkEnabled = true;

  // SERVER
  private int serverPort;
  private boolean loginEnabled;
  private String serverHostname;

  public void setNetworkEnabled(boolean networkEnabled) {
    this.networkEnabled = networkEnabled;
  }

  public boolean loginEnabled() {
    return loginEnabled;
  }

  public int getServerPort() {
    return serverPort;
  }

  public String getServerHostName() {
    return serverHostname;
  }

  public boolean isClient() {
    return client;
  }

  public boolean isServer() {
    return server;
  }

  public Identity getId() {
    return id;
  }

  public boolean isNetworkEnabled() {
    return networkEnabled;
  }

}
