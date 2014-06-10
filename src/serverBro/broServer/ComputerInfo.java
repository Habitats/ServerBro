package serverBro.broServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import serverBro.broShared.utilities.ComputerProcess;


/**
 * Data class for information about running processes on server.
 * 
 * @author Patrick
 * 
 */
public class ComputerInfo {
  private ArrayList<ComputerProcess> processes;

  public ComputerInfo() {}

  private void updateRunningProcesses() {
    Process proc = null;
    String myString = null;
    ArrayList<ComputerProcess> processes = new ArrayList<ComputerProcess>();
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
    this.processes = processes;
  }

  public ArrayList<ComputerProcess> getRunningProcesses() {
    updateRunningProcesses();
    return processes;
  }

  public ArrayList<ComputerProcess> getRunningUserProcesses() {
    updateRunningProcesses();
    for (ComputerProcess p : processes) {
      if (!p.getSessionId().equalsIgnoreCase("console"))
        processes.remove(p);
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
}
