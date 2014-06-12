package serverBro.broShared.utilities;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class UptimeStats implements Serializable {

  private long uptimeInMs;

  public UptimeStats(long uptimeInMs) {
    this.uptimeInMs = uptimeInMs;
  }

  private String formatMs(long ms) {
    long days = TimeUnit.MILLISECONDS.toDays(ms);
    long hours = TimeUnit.MILLISECONDS.toHours(ms - TimeUnit.DAYS.toMillis(days));
    long minutes = TimeUnit.MILLISECONDS.toMinutes(ms - TimeUnit.DAYS.toMillis(days) - TimeUnit.HOURS.toMillis(hours));

    int year = (int) Math.floor(days / 365.);

    String inclYear = year != 0 ? String.format("%d year%s, ", year, year == 1 ? "" : "s") : "";
    if (inclYear.length() != 0)
      days = (int) (days % 365);
    String inclDays = days != 0 || inclYear.length() != 0 ? String.format("%d day%s, ", days, days == 1 ? "" : "s") : "";
    String inclHours = hours != 0 || inclDays.length() != 0 ? String.format("%d hour%s and ", hours, hours == 1 ? "" : "s") : "";
    String inclMinutes = String.format("%d minute%s", minutes, minutes == 1 ? "" : "s");

    return inclYear + inclDays + inclHours + inclMinutes;
  }

  @Override
  public String toString() {
    return "Uptime: " + formatMs(uptimeInMs);
  }
}
