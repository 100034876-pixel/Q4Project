import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JFrame implements ActionListener {

    private JButton startButton;
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
            "1. Each player begins with seven red apple cards.\n" +
            "2. The judge reveals a green apple card for the round.\n" +
            "3. Everyone except the judge secretly submits one red card.\n" +
            "4. The judge picks the best anonymous match.\n" +
            "5. The winning player keeps the green card; all hands refill.\n" +
            "6. The judge role passes to the next player each round.\n" +
            "7. The first player to collect eight green cards wins."
        );
        instructionsArea.setBounds(150, 210, 520, 175);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionsArea.setBackground(new Color(220, 255, 220));
        instructionsArea.setForeground(Color.BLACK);
        instructionsArea.setEditable(false);
        instructionsArea.setFocusable(false);
        add(instructionsArea);

        // Start Button
        startButton = new JButton("Start Game");
        startButton.setBounds(300, 410, 200, 60);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
        add(startButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("start")) {
            dispose();
            openGameScreen();
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
