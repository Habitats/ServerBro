package serverBro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Config {

  private static Config instance;


  private Config() {
    properties = loadConfig("ServerBro.properties");
    id = new Identity("mrherp");

    initCustomConfig();
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
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

  private Properties loadConfig(String path) {
    properties = new Properties();
    try {
      properties.load(new FileInputStream(new File(path)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }

  private Properties properties;

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
