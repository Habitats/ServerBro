package serverBro.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import serverBro.broShared.misc.Logger;

public class BroFrame {

  public BroFrame(JPanel panel) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      Logger.error("Unable to set system 'look & feel'", e);
    }
    JFrame frame = new JFrame();
    frame.setAlwaysOnTop(true);
    frame.add(panel);
    frame.setTitle("ServerBro");
    buildFrame(frame);
  }

  private void buildFrame(JFrame frame) {


    frame.getContentPane().setBackground(Color.black);

    frame.pack();

    frame.setLocationRelativeTo(frame.getRootPane());
    // frame.setLocation(0, 0);
    // frame.setSize(new Dimension(800, 500));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // frame.setResizable(false);
    frame.setVisible(true);

  }

}
