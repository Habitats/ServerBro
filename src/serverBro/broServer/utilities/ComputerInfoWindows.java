package serverBro.broServer.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.apache.commons.io.IOUtils;

import serverBro.broShared.utilities.ComputerInfoInterface;
import serverBro.broShared.utilities.ComputerProcess;
import serverBro.broShared.utilities.CpuStats;
import serverBro.broShared.utilities.RamStats;
import serverBro.broShared.utilities.UptimeStats;

import com.sun.management.OperatingSystemMXBean;

public class ComputerInfoWindows implements ComputerInfoInterface {
  private List<ComputerProcess> processes;

  public ComputerInfoWindows() {
    processes = updateRunningProcesses();
  }

  public List<ComputerProcess> updateRunningProcesses() {
    Process proc = null;
    String myString = null;
    List<ComputerProcess> processes = Collections.synchronizedList(new ArrayList<ComputerProcess>());
    try {
      proc = Runtime.getRuntime().exec("tasklist.exe /v");
      InputStream procOutput = proc.getInputStream();
      myString = IOUtils.toString(procOutput, "UTF-8");

      // }
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<Integer> lengths = new ArrayList<Integer>();
    for (String length : myString.split("\n")[2].split(" ")) {
      lengths.add(length.length());
    }
    List<String> processList = new ArrayList<String>(Arrays.asList(myString.split("\n")));
    processList.remove(0);
    processList.remove(0);
    processList.remove(0);

    for (String ps : processList) {
      // int nextStart = 0;
      // int nextEnd = nextStart + lengths.get(0) + 1;
      String processName = getField(0, lengths, ps);
      String sessionId = getField(2, lengths, ps);
      int pid = Integer.parseInt(getField(1, lengths, ps));
      String memory = getField(4, lengths, ps);
      String status = getField(5, lengths, ps);
      ComputerProcess process = new ComputerProcess(processName, sessionId, pid, memory, status);
      processes.add(process);
    }
    return processes;
  }

  private String getField(int index, List<Integer> lengths, String processLine) {
    int start = 0;
    for (int i = 0; i < lengths.size(); i++) {
      if (i == index) {
        break;
      }
      start += lengths.get(i) + 1;
    }
    return processLine.substring(start, start + lengths.get(index)).trim();
  }

  @Override
  public List<ComputerProcess> getRunningUserProcesses() {
    List<ComputerProcess> processes = new ArrayList<ComputerProcess>();
    for (ComputerProcess p : this.processes) {
      if (p.getSessionId().equalsIgnoreCase("console"))
        processes.add(p);
    }
    return processes;
  }

  @Override
  public List<ComputerProcess> getRunningProcesses() {
    return processes;
  }

  @Override
  public CpuStats getCpuStats() {
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    ObjectName name = null;
    AttributeList list = null;
    double cpuLoad = Double.NaN;
    Double value = Double.NaN;
    while (true) {
      try {
        name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        list = mbs.getAttributes(name, new String[] {"SystemCpuLoad"});
      } catch (MalformedObjectNameException | NullPointerException | InstanceNotFoundException | ReflectionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      if (list.isEmpty())
        cpuLoad = Double.NaN;

      Attribute att = (Attribute) list.get(0);
      value = (Double) att.getValue();

      if (value != -1.0) {
        break;
      }
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
      }


    }
    cpuLoad = ((int) (value * 1000) / 10.0);
    CpuStats cpu = new CpuStats(cpuLoad);
    return cpu;
  }

  @Override
  public RamStats getRamStats() {
    OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    long freeRam = bean.getFreePhysicalMemorySize();
    long totalRam = bean.getTotalPhysicalMemorySize();
    long usedRam = totalRam - freeRam;
    RamStats ram = new RamStats(freeRam, totalRam, usedRam);

    return ram;
  }

  @Override
  public UptimeStats getUptimeStats() {
    Process uptimeProc;
    String line;
    BufferedReader in;
    long uptimeInMs = -1;
    try {
      uptimeProc = Runtime.getRuntime().exec("net stats srv");
      in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
      // TODO Auto-generated catch block
      while ((line = in.readLine()) != null) {
        if (line.startsWith("Statistics since")) {
          // TODO: support for more locales!
          SimpleDateFormat format = new SimpleDateFormat("'Statistics since' dd.MM.yyyy HH:mm:ss");
          Date boottime = format.parse(line);
          uptimeInMs = System.currentTimeMillis() - boottime.getTime();
          break;
        }
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    UptimeStats uptime = new UptimeStats(uptimeInMs);
    return uptime;
  }

  public static void main(String[] args) {
    ComputerInfoWindows c = new ComputerInfoWindows();
    String dick = c.getRamStats().toString();
    String uptime = c.getUptimeStats().toString();
    String cpu = c.getCpuStats().toString();
    System.out.println(dick);
    System.out.println(uptime);
    System.out.println(cpu);
  }
}
