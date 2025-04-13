import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private Image backgroundImage;

    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640); // Match the game dimensions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        backgroundImage = new ImageIcon("assets/background.png").getImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(new BorderLayout());

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                JFrame frame = new JFrame("Flappy Bird");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(360, 640);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                FlappyBird flappyBird = new FlappyBird();
                frame.add(flappyBird);
                frame.pack();
                flappyBird.requestFocus();
                frame.setVisible(true);
            }
        });

        panel.add(startButton, BorderLayout.SOUTH); // Place the button at the bottom

        add(panel);
    }
}