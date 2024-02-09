import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGameGUI extends JFrame {
    private NumberGame numberGame;
    private JTextField guessField;
    private JTextArea feedbackArea;

    public NumberGameGUI() {
        setTitle("Number Game");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        numberGame = new NumberGame();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel instructionLabel = new JLabel("Guess the number (between 1 and 100):");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        feedbackArea = new JTextArea(5, 20);
        feedbackArea.setEditable(false);

        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(new JScrollPane(feedbackArea));

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void processGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            String feedback = numberGame.checkGuess(guess);
            feedbackArea.append(feedback + "\n");

            if (feedback.contains("Congratulations") || numberGame.isGameOver()) {
                handleEndGame();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void handleEndGame() {
        int option = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            numberGame = new NumberGame();
            feedbackArea.setText("");
            guessField.setText("");
        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGameGUI();
            }
        });
    }
}
