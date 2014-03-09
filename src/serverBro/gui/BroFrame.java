package serverBro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import serverBro.ServerBro;

public class BroFrame extends JFrame {

  public BroFrame(BroPanel panel) {

    setName("Server Bro v0.1");

    add(panel);

    buildFrame(this);
  }

  private void buildFrame(JFrame frame) {

    frame.getContentPane().setBackground(Color.black);

    frame.setTitle("Status Panel");
    frame.pack();

    frame.setLocationRelativeTo(frame.getRootPane());
    // frame.setLocation(0, 0);
    // frame.setSize(new Dimension(800, 500));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // frame.setResizable(false);
    frame.setVisible(true);

  }

}
