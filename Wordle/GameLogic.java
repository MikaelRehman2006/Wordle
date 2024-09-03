import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// GameLogic class handles the core game mechanics for the Wordle-like game.
public class GameLogic extends PuzzleGame {
    private static final int MAX_GUESSES = 6; // Maximum number of allowed guesses
    private static final int WORD_LENGTH = 5; // Expected length of the target word
    private final List<String> guesses;       // Stores the player's guesses
    private final WordBank wordBank;          // Reference to the word bank containing valid words

    // Constructor to initialize GameLogic with a given WordBank
    public GameLogic(WordBank wordBank) {
        super(generateAnswer(wordBank));    // Call to PuzzleGame constructor with the answer
        this.guesses = new ArrayList<>();   // Initialize the guesses list
        this.wordBank = wordBank;           // Initialize the word bank reference
    }

    // Generates a random word from the word bank to be used as the answer
    private static String generateAnswer(WordBank wordBank) {
        Random random = new Random(); // Random instance to select a random key and word
        // Get all the starting letters (keys) as an array list
        List<Character> keysAsArray = new ArrayList<>(wordBank.getWordsByLetter().keySet());
        // Select a random key
        char randomKey = keysAsArray.get(random.nextInt(keysAsArray.size()));
        // Get all words that start with the random key
        List<String> words = new ArrayList<>(wordBank.getWordsStartingWith(randomKey));
        // Select a random word and convert it to uppercase
        return words.get(random.nextInt(words.size())).toUpperCase();
    }

    // Takes a guess and returns feedback. Also updates the game state.
    @Override
    public List<String> guessWord(String guess) {
        // If the game is over or max guesses reached, end the game and return a message
        if (isGameOver() || guesses.size() >= MAX_GUESSES) {
            setGameOver(true);
            List<String> result = new ArrayList<>();
            result.add("No more guesses allowed!");
            return result;
        }

        // Add the uppercased guess to the list of guesses
        guesses.add(guess.toUpperCase());
        // Check if the guess is correct
        boolean isCorrect = guess.equalsIgnoreCase(getAnswer());
        List<String> feedback;

        // If the guess is correct, set the game as won
        if (isCorrect) {
            setGameOver(true);
            feedback = new ArrayList<>();
            feedback.add("You win! The correct word was " + getAnswer());
        } else {
            // Otherwise, evaluate the guess for feedback
            feedback = evaluateGuess(guess.toUpperCase());
            // If the last guess is reached without a correct guess, end the game
            if (guesses.size() == MAX_GUESSES) {
                setGameOver(true);
                feedback = new ArrayList<>();
                feedback.add("Not Quite Right! The correct word was " + getAnswer());
            }
        }

        return feedback;
    }

    // Evaluates the guess against the answer and provides feedback for each letter
    private List<String> evaluateGuess(String guess) {
        // Initialize feedback list with "absent" for each letter in the word
        List<String> feedback = new ArrayList<>(WORD_LENGTH);
        for (int i = 0; i < WORD_LENGTH; i++) {
            feedback.add("absent");
        }
        char[] answerChars = getAnswer().toCharArray(); // Answer broken down into characters
        boolean[] correct = new boolean[WORD_LENGTH];   // Flags to mark correct characters

        // First pass to find correct letters
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (guess.charAt(i) == answerChars[i]) {
                feedback.set(i, "correct");
                correct[i] = true;
            }
        }

        // Second pass to find present letters that are not in the correct position
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (!correct[i]) {
                for (int j = 0; j < WORD_LENGTH; j++) {
                    if (guess.charAt(i) == answerChars[j] && !correct[j]) {
                        feedback.set(i, "present");
                        correct[j] = true; // Mark the character so it's not used again
                        break;
                    }
                }
            }
        }

        return feedback;
    }

    // Checks if the provided guess is the correct answer
    public boolean isWordCorrect(String guess) {
        return guess.equalsIgnoreCase(getAnswer()); // Compare ignoring case
    }
}