package serverBro.broShared.events;


import serverBro.broShared.events.external.MessageEvent;
import serverBro.broShared.events.external.NetworkEvent;
import serverBro.broShared.events.internal.BroadcastButtonEvent;
import serverBro.broShared.events.internal.ConnectButtonEvent;
import serverBro.broShared.events.internal.DisconnectButtonEvent;
import serverBro.broShared.events.internal.InfoButtonEvent;
import serverBro.broShared.events.internal.MessageButtonEvent;
import serverBro.broShared.events.internal.SendButtonEvent;
import serverBro.broShared.events.internal.ViewEvent;
import serverBro.broShared.view.BroGuiConstants;


/**
 * Encapsulation factory for hiding concrete creation of events.
 * 
 * @author Patrick
 * 
 */
public class EventFactory {


  public static NetworkEvent createOutGoingTestEvent() {
    return new MessageEvent("test message event");
  }

  public static ViewEvent createViewEvent(String name) {
    ViewEvent viewEvent = null;
    if (name.equals(BroGuiConstants.CONNECT)) {
      viewEvent = new ConnectButtonEvent();
    } else if (name.equals(BroGuiConstants.DISCONNECT)) {
      viewEvent = new DisconnectButtonEvent();
    } else if (name.equals(BroGuiConstants.INFO)) {
      viewEvent = new InfoButtonEvent();
    } else if (name.equals(BroGuiConstants.BROADCAST)) {
      viewEvent = new BroadcastButtonEvent();
    } else if (name.equals(BroGuiConstants.MESSAGE)) {
      viewEvent = new MessageButtonEvent();
    } else if (name.equals(BroGuiConstants.SEND)) {
      viewEvent = new SendButtonEvent();
    }
    return viewEvent;
  }
}
