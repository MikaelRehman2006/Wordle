import java.util.List;

// PuzzleGame is an abstract class that provides a template for puzzle game implementations.
public abstract class PuzzleGame implements Game {
    // Indicates whether the game has ended.
    private boolean isGameOver;
    
    // Stores the answer to the puzzle.
    protected String answer;

    // Constructor to initialize the puzzle game with the correct answer.
    protected PuzzleGame(String answer) {
        this.isGameOver = false; // The game starts with a false state for isGameOver.
        this.answer = answer;    // Sets the answer for the game.
    }

    // Implements the isGameOver() method from the Game interface.
    @Override
    public boolean isGameOver() {
        return isGameOver; // Returns the current game over state.
    }

    // Sets the game over state to the given value.
    protected void setGameOver(boolean gameIsOver) {
        this.isGameOver = gameIsOver;
    }

    // Implements the getAnswer() method from the Game interface.
    @Override
    public String getAnswer() {
        return answer; // Returns the answer to the puzzle.
    }
    
    // Abstract method to be implemented by subclasses to handle guessing the puzzle.
    // Each guess returns a list of feedback, allowing for a list of responses to be provided.
    @Override
    public abstract List<String> guessWord(String guess);
}