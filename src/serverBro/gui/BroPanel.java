package serverBro.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BroPanel extends JPanel implements BroView, Observer {

  private BroModel model;
  private JTextArea statusField;
  private JButton disconnectButton;
  private JButton connectButton;
  private JButton sendButton;

  public BroPanel() {
    connectButton = new JButton("CONNECT");
    disconnectButton = new JButton("DISCONNECT");
    sendButton = new JButton("SEND");
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


  public void setModel(BroModel model) {
    this.model = model;
  }

  @Override
  public void update(Observable o, Object arg) {

    statusField.setText(arg.toString());
  }

  @Override
  public void addController(BroController controller) {
    connectButton.addActionListener(controller);
    disconnectButton.addActionListener(controller);
    sendButton.addActionListener(controller);
  }
}
