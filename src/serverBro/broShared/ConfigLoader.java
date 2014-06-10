package serverBro.broShared;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigLoader {
  private static Properties loadConfigAndroid() {
    Properties p = new Properties();
    p.setProperty("server_port", "1337");
    p.setProperty("login_enabled", "False");
    p.setProperty("server", "False");
    p.setProperty("client", "True");
    p.setProperty("server_hostname", "192.168.1.209");
    return p;
  }

  private static Properties loadConfigWindows(String path) {

    Properties properties = new Properties();
    try {
      // properties.load(this.getClass().getClassLoader().getResourceAsStream(path));
      properties.load(new FileInputStream(new File(path)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }

  public static Properties loadConfig() {

    findSystemProperties();
    Properties prop;
    if (System.getProperty("java.vm.specification.vendor").equalsIgnoreCase("The Android Project")) {
      prop = loadConfigAndroid();
    } else {
      prop = loadConfigWindows("serverbro.properties");
    }
    return prop;
  }

  public static void findSystemProperties() {
    Properties p = System.getProperties();
    Enumeration keys = p.keys();
    while (keys.hasMoreElements()) {
      String key = (String) keys.nextElement();
      String value = (String) p.get(key);
      System.out.println(key + " >>>> " + value);
    }
  }
}
