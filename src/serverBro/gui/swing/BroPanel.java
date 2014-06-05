package serverBro.gui.swing;

import java.awt.Color;
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
  private ContinuousFeedArea logFeed;
  private JButton infoButton;

  public BroPanel() {
    connectButton = new JButton(BroGuiConstants.CONNECT);
    connectButton.setName(BroGuiConstants.CONNECT);
    disconnectButton = new JButton(BroGuiConstants.DISCONNECT);
    disconnectButton.setName(BroGuiConstants.DISCONNECT);
    sendButton = new JButton(BroGuiConstants.SEND);
    infoButton = new JButton(BroGuiConstants.INFO);
    infoButton.setName(BroGuiConstants.INFO);

    sendButton.setName(BroGuiConstants.SEND);
    statusField = new JTextArea();
    logFeed = new ContinuousFeedArea();

    setLayout(new GridBagLayout());
    Dimension dim = new Dimension(500, 500);
    setPreferredSize(dim);
    setMinimumSize(dim);
    setMaximumSize(dim);
    statusField.setPreferredSize(new Dimension(40, 500));
    logFeed.setBackground(Color.gray);
    statusField.setBackground(Color.pink);

    add(connectButton, new GBC(0, 0));
    add(infoButton, new GBC(3, 0));
    add(disconnectButton, new GBC(1, 0));
    // add(new JScrollPane(statusField), new GBC(0, 1).setSpan(3, 2).setWeight(1, 1));
    add(statusField, new GBC(0, 4).setSpan(4, 2));
    add(logFeed, new GBC(0, 1).setSpan(4, 2).setWeight(1, 1));
    add(sendButton, new GBC(2, 0));
  }

  @Override
  public void update(Observable o, Object arg) {
    BroModel model = (BroModel) o;
    statusField.setText(model.getNetworkStatus());
    logFeed.append(model.getLastMessage() + "\n");
    logFeed.append(model.getProcesses().toString());
    // infoField.setText(model.getProcesses().toString());
  }

  public void setController(BroGuiController controller) {
    connectButton.addActionListener(controller);
    disconnectButton.addActionListener(controller);
    sendButton.addActionListener(controller);
    infoButton.addActionListener(controller);
  }
}
