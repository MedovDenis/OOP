package Main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void createGUI()
    {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Test label");
        JButton button = new JButton("Click");
        frame.getContentPane().add(label);
        frame.getContentPane().add(button);

        frame.setPreferredSize(new Dimension(330, 540));


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
}
