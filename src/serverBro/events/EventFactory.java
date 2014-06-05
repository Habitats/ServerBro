package serverBro.events;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import serverBro.Controller;
import serverBro.events.interaction.ConnectEvent;
import serverBro.events.interaction.InfoEvent;
import serverBro.events.interaction.DisconnectEvent;
import serverBro.events.interaction.SendEvent;
import serverBro.events.interaction.ViewEvent;
import serverBro.events.networkEvents.MessageEvent;
import serverBro.events.networkEvents.NetworkEvent;
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
    } else if (sourceName.equals(BroGuiConstants.INFO)) {
      viewEvent = new InfoEvent(controller);
    } else if (sourceName.equals(BroGuiConstants.SEND)) {
      viewEvent = new SendEvent(controller);
    }
    return viewEvent;
  }
}
