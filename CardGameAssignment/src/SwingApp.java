import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SwingApp extends JFrame {

    public SwingApp() {
        setLayout(new BorderLayout());
        add(new JLabel("This is the Swing App", JLabel.CENTER), BorderLayout.CENTER);
        JButton quitButton = new JButton("Exit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton, BorderLayout.SOUTH);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}