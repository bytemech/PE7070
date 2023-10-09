package ConnectFour;

/**
 * ConnectFourUIInterface
 * This is the parent interface for all UIs
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public interface ConnectFourUIInterface {

    /**
     * startNewGame
     * This method sets up a new game in the console, displaying the welcome
     * message, the game, and the menu.
     */
    void startNewGame();

    /**
     * saveGame
     * To be implemented by student - This method should save the current game state
     */
    void saveGame();

    /**
     * undoMove
     * Undoes the previous move made in the game, along with the corresponding
     * computer move
     */
    void undoMove();

    /**
     * loadGame
     * Loads the saved state of the game.
     */
    void loadGame();

    /**
     * clearGame
     * This method clears the game board and any record of moves, to reset the game.
     */
    void clearGame();

    /**
     * displayWelcomeMessage
     * This method displays a welcome message to the user
     */
    void displayWelcomeMessage();

    /**
     * checkWin
     * This method displays the winner of the game
     */
    void checkWin();

    /**
     * replay
     * This method gives the user the option to play again, or quit, once the game
     * is won
     */
    void replay();

    /**
     * printToUser
     * This method is for printing information to the user - to allow them to
     * understand the game flow.
     * 
     * @param message - the message to be sent to the user.
     */
    void printToUser(String message);
}