import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JFrame implements ActionListener {

    private JButton startButton;
    private JButton testEndScreenButton;
    private JLabel titleLabel;
    private JTextArea instructionsArea;

    public StartScreen() {

        setTitle("Apples to Apples");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(180, 255, 180));

        // Title
        titleLabel = new JLabel("APPLES TO APPLES");
        titleLabel.setBounds(180, 120, 500, 80);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(Color.RED);
        add(titleLabel);

        // Instructions
        instructionsArea = new JTextArea(
            "How to Play:\n" +
            "1. Each player gets red apple cards.\n" +
            "2. A green apple card is shown as the round topic.\n" +
            "3. Players choose the red card that best matches the green card.\n" +
            "4. The judge picks the funniest or best match.\n" +
            "5. The player whose card is picked wins the green card.\n" +
            "6. The first player with the most green cards wins."
        );
        instructionsArea.setBounds(170, 210, 460, 150);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionsArea.setBackground(new Color(220, 255, 220));
        instructionsArea.setForeground(Color.BLACK);
        instructionsArea.setEditable(false);
        instructionsArea.setFocusable(false);
        add(instructionsArea);

        // Start Button
        startButton = new JButton("Start Game");
        startButton.setBounds(300, 390, 200, 60);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
        add(startButton);

        // Testing button
        testEndScreenButton = new JButton("Test End Screen");
        testEndScreenButton.setBounds(275, 470, 250, 60);
        testEndScreenButton.setFont(new Font("Arial", Font.BOLD, 22));
        testEndScreenButton.setActionCommand("testEnd");
        testEndScreenButton.addActionListener(this);
        add(testEndScreenButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("start")) {
            dispose();
            openGameScreen();
        }

        if (command.equals("testEnd")) {
            dispose();
            new EndScreen("Player 1");
        }
    }

    private void openGameScreen() {
        JFrame frame = new JFrame("Apples to Apples Game");
        Screen panel = new Screen();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
