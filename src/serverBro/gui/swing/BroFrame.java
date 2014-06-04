package serverBro.gui.swing;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BroFrame {

  public BroFrame(JPanel panel) {

    JFrame frame = new JFrame();
    frame.add((JPanel) panel);
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
