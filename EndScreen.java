import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EndScreen extends JFrame implements ActionListener {

    private JLabel winnerLabel;
    private JButton playAgainButton;
    private JButton quitButton;

    public EndScreen(String winnerName) {

        setTitle("Game Over");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(255, 220, 220));

        // Winner text
        winnerLabel = new JLabel(winnerName + " Wins!");
        winnerLabel.setBounds(220, 150, 500, 80);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        winnerLabel.setForeground(Color.BLUE);
        add(winnerLabel);

        // Play Again button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(250, 320, 140, 60);
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 18));
        playAgainButton.setActionCommand("playAgain");
        playAgainButton.addActionListener(this);
        add(playAgainButton);

        // Quit button
        quitButton = new JButton("Quit");
        quitButton.setBounds(420, 320, 140, 60);
        quitButton.setFont(new Font("Arial", Font.BOLD, 18));
        quitButton.setActionCommand("quit");
        quitButton.addActionListener(this);
        add(quitButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("playAgain")) {
            dispose();
            new StartScreen();
        }

        if (command.equals("quit")) {
            System.exit(0);
        }
    }
}
