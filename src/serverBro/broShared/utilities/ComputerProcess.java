package serverBro.broShared.utilities;

import java.io.Serializable;

public class ComputerProcess implements Serializable {
  private final String processName;
  private final int memory;
  private final int pid;
  private final String sessionId;
  private final String status;

  public ComputerProcess(String processName, String sessionId, int pid, int memory, String status) {
    this.processName = processName;
    this.sessionId = sessionId;
    this.pid = pid;
    this.memory = memory;
    this.status = status;
  }

  public String getMemoryInMB() {
    return memory/1024 + " MB";
  }

  public int getPid() {
    return pid;
  }

  public String getProcessName() {
    return processName;
  }

  public String getSessionId() {
    return sessionId;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "Name: " + processName + " - Session: " + sessionId + " - PID: " + pid + " - MEM: " + memory + " - Status: " + status;
  }
}
