import java.util.List;

// The Game interface defines the contract for any game implementation.
public interface Game {
    // Checks if the game is over.
    boolean isGameOver();

    // Retrieves the answer or solution for the game.
    String getAnswer();

    // Submits a guess and gets feedback on the guess.
    List<String> guessWord(String guess);
}