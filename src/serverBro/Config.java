package serverBro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

  private static Config instance;


  private Config() {
    properties = loadConfig("ServerBro.properties");

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
  }

  // SERVER
  private int serverPort;
  private boolean loginEnabled;
  private String serverHostname;

  public boolean loginEnabled() {
    return loginEnabled;
  }

  public int getServerPort() {
    return serverPort;
  }

  public String getServerHostName() {
    return serverHostname;
  }

}
