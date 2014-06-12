package serverBro.broShared.events.external;

import java.io.Serializable;

import serverBro.broShared.Identity;
import serverBro.broShared.events.BroEvent;
import serverBro.broShared.misc.Config;

/**
 * Abstract implementation of events to be sent between client and server.
 * 
 * @author Patrick
 * 
 */
public abstract class NetworkEvent implements Serializable, BroEvent {
  private static final long serialVersionUID = 1L;
  public static final int GLOBAL = 0;
  public static final int PRIVATE = 1;

  public final int messageType;
  public final Identity senderId;
  private final Identity receiver;

  public NetworkEvent() {
    this(PRIVATE);
  }

  public NetworkEvent(int global) {
    this(global, null);
  }

  public NetworkEvent(int global, Identity receiver) {
    this.receiver = receiver;
    this.senderId = Config.getInstance().getId();
    this.messageType = global;
  }

  public Identity getSender() {
    return senderId;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }
}
