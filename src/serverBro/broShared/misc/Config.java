package serverBro.broShared.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Properties;

import javax.crypto.SecretKey;

import serverBro.broShared.Identity;

/**
 * Handles configutation of the application.
 * 
 * @author Patrick
 * 
 */
public class Config {

  private static Config instance;


  private Config() {
    id = new Identity("mrherp", "derp");
    properties = new Properties();
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

  public void loadProperties(String path) {
    try {
      loadProperties(new FileInputStream(new File(path)));
    } catch (FileNotFoundException e) {
      Logger.error("Unable to create fileInputStream", e);
    }
  }

  public void loadProperties(InputStream in) {
    try {
      properties.load(in);
    } catch (IOException e) {
      Logger.error("Unable to load properties", e);
    }
    initCustomConfig();
  }

  public void loadSecretKey(String path) {
    try {
      loadSecretKey(new FileInputStream(new File(path)));
    } catch (FileNotFoundException e) {
      Logger.error("Unable to load secretKey from disk", e);
    }
  }

  public void loadSecretKey(InputStream in) {
    key = (SecretKey) Serializer.getInstance().deserialize(in);
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
  private boolean connected = false;
  private boolean encryptionEnabled = true;
  private Key key;

  // SERVER
  private int serverPort;
  private boolean loginEnabled;
  private String serverHostname;
  private String speedScriptLocation = "src/serverBro/extra/speed.vbs";

  public synchronized void setConnected(boolean connected) {
    this.connected = connected;
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

  public synchronized boolean isConnected() {
    return connected;
  }

  public boolean encryptionEnabled() {
    return encryptionEnabled;
  }

  public Key getSecretKey() {
    return key;
  }

  public void setId(Identity identity) {
    this.id = identity;
  }

  public void setServerPort(int serverPort) {
    this.serverPort = serverPort;
  }

  public void setServerHostname(String serverHostname) {
    this.serverHostname = serverHostname.trim();
  }

  public String getNetworkStatus() {
    return connected ? ("Connected: " + id.getUsername() + "@" + serverHostname + ":" + serverPort) : "Not connected";
  }

  public String getSpeedScriptLocation() {
    return speedScriptLocation;
  }
}
