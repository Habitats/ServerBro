package serverBro.broShared.misc;

import java.text.SimpleDateFormat;
import java.util.Date;

import serverBro.broShared.view.LogView;

/**
 * Logger class.
 * 
 * @author Patrick
 * 
 */
public class Logger {
  private static int logNumber = 1;
  private static int errorNumber = 1;
  private static LogView LOG_VIEW;
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  private static String formattedTime() {
    return dateFormat.format(new Date());

  }

  public static void log(String log) {

    String formattedLog = String.format("%s > %s", formattedTime(), log);
    System.out.println(formattedLog);
    addToLogView(formattedLog);
  }

  public static void error(String string, Exception e) {
    string += ": " + e.toString();
    String formattedError = String.format("%s > ERR > %s", formattedTime(), string);
    System.err.println(formattedError);
    addToLogView(formattedError);
  }

  private static void addToLogView(String log) {
    if (LOG_VIEW != null) {
      LOG_VIEW.add(log);
    }
  }

  public static void setLogView(LogView logView) {
    LOG_VIEW = logView;
  }

}
