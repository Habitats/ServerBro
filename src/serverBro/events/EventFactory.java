package serverBro.events;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import serverBro.Config;
import serverBro.Controller;
import serverBro.events.networkEvents.AuthEvent;
import serverBro.events.networkEvents.MessageEvent;
import serverBro.events.networkEvents.NetworkEvent;
import serverBro.events.viewEvents.ConnectEvent;
import serverBro.events.viewEvents.DisconnectEvent;
import serverBro.events.viewEvents.SendEvent;
import serverBro.events.viewEvents.ViewEvent;
import serverBro.gui.swing.BroGuiConstants;


public class EventFactory {


  public static NetworkEvent createOutGoingTestEvent() {
    return new MessageEvent("test message event");
  }

  public static ViewEvent createViewEvent(ActionEvent e, Controller controller) {
    String sourceName = ((JComponent) e.getSource()).getName();
    ViewEvent viewEvent = null;
    if (sourceName.equals(BroGuiConstants.CONNECT)) {
      viewEvent = new ConnectEvent(controller);
    } else if (sourceName.equals(BroGuiConstants.DISCONNECT)) {
      viewEvent = new DisconnectEvent(controller);
    } else if (sourceName.equals(BroGuiConstants.SEND)) {
      viewEvent = new SendEvent(controller);
    }
    return viewEvent;
  }

  public static AuthEvent createAuthEvent() {
    return new AuthEvent(Config.getInstance().getId());
  }
}
