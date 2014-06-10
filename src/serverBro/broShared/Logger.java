package serverBro.broShared;

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

  public static void log(String log) {
    String formattedLog = String.format("%4d: %s", logNumber++, log);
    System.out.println(formattedLog);
    addToLogView(formattedLog);
  }

  public static void error(String string) {
    String formattedError = String.format("%8d: %s", errorNumber++, string);
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
