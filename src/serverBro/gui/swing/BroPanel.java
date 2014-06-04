package serverBro.gui.swing;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import serverBro.gui.BroModel;

public class BroPanel extends JPanel implements Observer {

  private JTextArea statusField;
  private JButton disconnectButton;
  private JButton connectButton;
  private JButton sendButton;

  public BroPanel() {
    connectButton = new JButton("START");
    connectButton.setName(BroGuiConstants.CONNECT);
    disconnectButton = new JButton("STOP");
    disconnectButton.setName(BroGuiConstants.DISCONNECT);
    sendButton = new JButton("BROADCAST");
    sendButton.setName(BroGuiConstants.SEND);
    statusField = new JTextArea();

    setLayout(new GridBagLayout());
    Dimension dim = new Dimension(300, 500);
    setPreferredSize(dim);
    setMinimumSize(dim);
    setMaximumSize(dim);

    add(connectButton, new GBC(0, 0));
    add(disconnectButton, new GBC(1, 0));
    add(new JScrollPane(statusField), new GBC(0, 1).setSpan(3, 2).setWeight(1, 1));
    add(sendButton, new GBC(2, 0));
  }

  @Override
  public void update(Observable o, Object arg) {
    BroModel model = (BroModel) o;
    statusField.setText(model.getNetworkStatus());
    statusField.setText("asdasda" + Math.random());
    // infoField.setText(model.getProcesses().toString());
  }

  public void setController(BroGui controller) {
    connectButton.addActionListener(controller);
    disconnectButton.addActionListener(controller);
    sendButton.addActionListener(controller);
  }
}
