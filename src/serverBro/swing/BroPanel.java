package serverBro.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import serverBro.broShared.BroModel;
import serverBro.broShared.view.BroGuiConstants;
import serverBro.broShared.view.BroGuiController;

public class BroPanel extends JPanel implements Observer {

  private JTextArea statusField;
  private JButton disconnectButton;
  private JButton connectButton;
  private JButton sendButton;
  private SwingLogView logFeed;
  private JButton infoButton;
  private JButton broadcastButton;
  private JButton messageButton;
  private BroGuiController broGuiController;
  private ContinuousFeedArea messageFeed;

  public BroPanel(BroGuiController broGuiController) {
    this.broGuiController = broGuiController;
    connectButton = new BroButton(BroGuiConstants.CONNECT, broGuiController);
    disconnectButton = new BroButton(BroGuiConstants.DISCONNECT, broGuiController);
    sendButton = new BroButton(BroGuiConstants.SEND, broGuiController);
    broadcastButton = new BroButton(BroGuiConstants.BROADCAST, broGuiController);
    infoButton = new BroButton(BroGuiConstants.INFO, broGuiController);
    messageButton = new BroButton(BroGuiConstants.MESSAGE, broGuiController);

    statusField = new JTextArea();
    logFeed = new SwingLogView();
    messageFeed = new ContinuousFeedArea();

    setLayout(new GridBagLayout());
    Dimension dim = new Dimension(500, 200);
    setPreferredSize(dim);
    setMinimumSize(dim);
    setMaximumSize(dim);
    statusField.setPreferredSize(new Dimension(40, 500));
    logFeed.setBackground(Color.gray);
    statusField.setBackground(Color.pink);

    add(connectButton, new GBC(0, 0));
    add(disconnectButton, new GBC(1, 0));
    add(sendButton, new GBC(2, 0));
    add(infoButton, new GBC(3, 0));
    add(broadcastButton, new GBC(4, 0));
    add(messageButton, new GBC(5, 0));

    add(logFeed, new GBC(0, 1).setSpan(3, 2).setWeight(0.5, 1));
    add(messageFeed, new GBC(3, 1).setSpan(3, 2).setWeight(0.5, 1));
    add(statusField, new GBC(0, 4).setSpan(10, 2));
  }

  @Override
  public void update(Observable o, Object arg) {
    BroModel model = (BroModel) o;
    statusField.setText(model.getNetworkStatus());
    messageFeed.append(model.getLastMessage() + "\n");
    statusField.setText(model.getName());
    // infoField.setText(model.getProcesses().toString());
  }
}
