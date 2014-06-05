package serverBro.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import serverBro.broShared.events.EventFactory;
import serverBro.broShared.view.BroGuiController;

public class BroButton extends JButton {


  public BroButton(final String name, final BroGuiController controller) {
    super(name);
    setName(name);
    addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        controller.actionPerformed(EventFactory.createViewEvent(name));
      }
    });
  }
}
