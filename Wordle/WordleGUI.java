import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

// WordleGUI class is the graphical user interface for the Wordle game.
public class WordleGUI {
    // Frame for the main window of the application
    private JFrame frame;
    // Grid of text fields for displaying guesses and feedback
    private JTextField[][] letterFields;
    // Text field for user input
    private JTextField inputField;
    // Game logic handler
    private GameLogic gameLogic;
    // Word bank handler
    private WordBank wordBank;
    // Counter for the current attempt number
    private int currentAttempt = 0;

    // Constructor that sets up the game logic and word bank
    public WordleGUI(GameLogic gameLogic, WordBank wordBank) {
        this.gameLogic = gameLogic;
        this.wordBank = wordBank;
        initializeUI();
    }

    // Initializes the user interface components
    private void initializeUI() {
        // Setting up the main frame
        frame = new JFrame("Wordle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Hint button to provide a hint to the player
        JButton hintButton = new JButton("Get Hint");
        hintButton.addActionListener(e -> {
            // Fetches and shows the hint for the current answer
            String currentWord = gameLogic.getAnswer();
            String hint = wordBank.getHintForWord(currentWord);
            JOptionPane.showMessageDialog(frame, hint);
        });
        frame.add(hintButton, BorderLayout.NORTH);

        // Panel for the grid of text fields
        JPanel wordGridPanel = new JPanel(new GridLayout(GameConfig.MAX_TRIES, GameConfig.WORD_LENGTH));
        letterFields = new JTextField[GameConfig.MAX_TRIES][GameConfig.WORD_LENGTH];
        
        // Initialize each text field in the grid
        for (int i = 0; i < GameConfig.MAX_TRIES; i++) {
            for (int j = 0; j < GameConfig.WORD_LENGTH; j++) {
                JTextField field = createTextField();
                letterFields[i][j] = field;
                wordGridPanel.add(field);
            }
        }
        frame.add(wordGridPanel, BorderLayout.CENTER);

        // Input field for the player's guesses
        inputField = new JTextField();
        setupTextField(inputField); // Configuring the input field
        // Listener for the enter key in the input field
        inputField.addActionListener(e -> {
            // Processing the guess when the user submits it
            String guess = inputField.getText().toUpperCase();
            if (guess.matches("[A-Z]+") && guess.length() == GameConfig.WORD_LENGTH) {
                // Get feedback from the game logic
                List<String> feedback = gameLogic.guessWord(guess);
                // Check if the guess is correct
                boolean hasWon = gameLogic.isWordCorrect(guess);
                // Update the grid with the feedback from the guess
                updateGridWithFeedback(currentAttempt, guess, feedback);
                inputField.setText(""); // Clear the input field
                // Show the game over message if the game is won or if the max tries are reached
                if (hasWon) {
                    inputField.setEditable(false);
                    showGameOverMessage(true);
                } else {
                    currentAttempt++;
                    if (currentAttempt == GameConfig.MAX_TRIES) {
                        inputField.setEditable(false);
                        showGameOverMessage(false);
                    }
                }
            } else {
                // Prompt the user to input valid guesses
                JOptionPane.showMessageDialog(frame, "Please enter a 5-letter word using only letters.");
            }
        });
        frame.add(inputField, BorderLayout.SOUTH);

        // Prepare the frame for display
        frame.pack();
        frame.setVisible(true);
    }

    // Creates and configures a new text field
    private JTextField createTextField() {
        JTextField field = new JTextField(1);
        setupTextField(field);
        field.setEditable(false); // Text fields in the grid should not be editable
        return field;
    }

    // Applies standard configuration to a text field
    private void setupTextField(JTextField field) {
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD, 24f));
        field.setBackground(Color.DARK_GRAY);
        field.setForeground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    // Updates the grid of text fields with feedback for each letter in the guess
    private void updateGridWithFeedback(int attemptNumber, String guess, List<String> feedback) {
        // Determine if this is the last attempt or if the guess is correct
        boolean isLastAttempt = currentAttempt == GameConfig.MAX_TRIES - 1;
        boolean isCorrectGuess = gameLogic.isWordCorrect(guess);

        // Update each text field with the corresponding feedback
        for (int i = 0; i < GameConfig.WORD_LENGTH; i++) {
            JTextField field = letterFields[attemptNumber][i];
            field.setText(String.valueOf(guess.charAt(i)));

            // Change the background color based on the feedback type
            if (isCorrectGuess) {
                field.setBackground(Color.ORANGE);
            } else if (isLastAttempt && !isCorrectGuess) {
                field.setBackground(Color.RED);
            } else {
                switch (feedback.get(i)) {
                    case "correct":
                        field.setBackground(Color.GREEN);
                        break;
                    case "present":
                        field.setBackground(Color.YELLOW);
                        break;
                    case "absent":
                        field.setBackground(Color.DARK_GRAY);
                        break;
                }
            }
        }
    }

    // Displays a message indicating the end of the game and the result
    private void showGameOverMessage(boolean hasWon) {
        String message;
        // Customize the message based on whether the player has won or lost
        if (hasWon) {
            message = "Congratulations! The correct word was: " + gameLogic.getAnswer();
            JOptionPane.showMessageDialog(frame, message, "You Win!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            message = "Not Quite Right! The correct word was: " + gameLogic.getAnswer();
            JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to launch the Wordle game
    public static void main(String[] args) {
        // Create instances of WordBank and GameLogic
        WordBank wordBank = new WordBank();
        GameLogic gameLogic = new GameLogic(wordBank);
        // Start the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new WordleGUI(gameLogic, wordBank));
    }
}