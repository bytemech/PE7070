package ConnectFour;

import java.util.Random;

/**
 * ConnectFour
 * This is the main controller class for the game ConnectFour
 * 
 * @author Lauren Scott
 * @version Student Sample Code
 */
public class ConnectFour {
    private Slot[][] moves;// 2D array of the moves of the game
    public static final String EMPTYSLOT = "-"; // Blank slot game state
    public static final String PLAYERMOVE = "x";// Player slot game state
    public static final String COMPUTERMOVE = "o";// computer slot game state
    private static final int rowCount = 6;
    private static final int colCount = 7;

    /**
     * Constructor of the class ConnectFour
     * Initialises the slots of the game ready to play
     */
    public ConnectFour() {
        createGame();
    }

    /**
     * createGame
     * This method creates a new set of blank moves in preparation to play the game.
     */
    public void createGame() {
        moves = new Slot[rowCount][colCount];
        for (int c = 0; c < colCount; c++) {
            for (int r = 0; r < rowCount; r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
    }

    /**
     * getMoves
     * This method returns a 2D array with all of the current moves in the game
     * 
     * @return the moves in the game currently
     */
    public Slot[][] getMoves() {
        return moves;
    }

    /**
     * getRowCount
     * This method returns a 2D array with all of the current moves in the game
     * 
     * @return the number of rows in the game, as an int.
     */
    public int getRowCount() {
        return ConnectFour.rowCount - 1;
    }

    /**
     * getColCount
     * This method returns a 2D array with all of the current moves in the game
     * 
     * @return the number of columns in the game, as an int.
     */
    public int getColCount() {
        return ConnectFour.colCount - 1;
    }

    /**
     * addMove
     * This method adds a move to the game
     * 
     * @param col    - The column that the player/computer has selected
     * @param player - A Boolean value denoting whether the move is made by the
     *               player or the computer
     */
    public void addMove(int col, boolean player) {
        Assign move = new Assign(this, col, player);
    }

    /**
     * generateComputerMove
     * This method generates the computer's move using a random number generator
     */
    public void generateComputerMove() {
        Random randomGen = new Random();

        addMove(randomGen.nextInt(colCount), false);
    }

    /**
     * clearGame
     * This method resets the move history table to clear the current values.
     */
    public void clearGame() {
        createGame();
    }

    /**
     * checkWin
     * This method checks whether there is a vertical or horizontal match of four
     * slots, to determine who has won the game
     * 
     * @return This value is the winner of the game, if it is null, there is no
     *         current winner.
     */
    public String checkWin() {
        var $player = checkGrid(moves, PLAYERMOVE, "player");
        var $computer = checkGrid(moves, COMPUTERMOVE, "computer");

        if ($player != null) {
            return $player;
        }
        if ($computer != null) {
            return $computer;
        }
        return null;
    }

    /**
     * checkGrid
     * This method checks whether there is a vertical or horizontal match of four
     * slots, to determine who has won the game
     * 
     * @param MOVETOKEN   - The token to check the grid for.
     * @param returnValue - The value to return if a match found in the grid.
     * @return The value of returnValue, if a match is found. If a match is not
     *         found, this will be null.
     */
    public String checkGrid(Slot[][] moves, String MOVETOKEN, String returnValue) {
        try {
            // Start at bottom of grid and move up.
            for (int r = 0; r < rowCount; r++) {
                // Start at left hand side of grid and move across.
                for (int c = 0; c < colCount; c++) {
                    if (moves[r][c].getState().equals(MOVETOKEN)) {
                        System.out.println("Row " + r + " column " + c + "player move detected");
                        if (
                        // Check for 4 in same row
                        moves[r][c + 1].getState().equals(MOVETOKEN) &&
                                moves[r][c + 2].getState().equals(MOVETOKEN) &&
                                moves[r][c + 3].getState().equals(MOVETOKEN)) {
                            return returnValue;
                        }
                        if (
                        // Check for 4 in same column
                        moves[r + 1][c].getState().equals(MOVETOKEN) &&
                                moves[r + 2][c].getState().equals(MOVETOKEN) &&
                                moves[r + 3][c].getState().equals(MOVETOKEN)) {
                            return returnValue;
                        }
                        if (
                        // Check for 4 in rising, right-handed diagonal (check starts from bottom left)
                        moves[r + 1][c + 1].getState().equals(MOVETOKEN) &&
                                moves[r + 2][c + 2].getState().equals(MOVETOKEN) &&
                                moves[r + 3][c + 3].getState().equals(MOVETOKEN)) {
                            return returnValue;
                        }
                        if (
                        // Check for 4 in rising, left handed, diagonal (check starts from bottom left)
                        moves[r + 1][c - 1].getState().equals(MOVETOKEN) &&
                                moves[r + 1][c - 2].getState().equals(MOVETOKEN) &&
                                moves[r + 1][c - 3].getState().equals(MOVETOKEN)) {
                            return returnValue;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // As the move could begin in slot 5, this allows the user to continue playing
            // the game when the check contains higher numbers than the size of the array
        }
        return null;
    }

}// End of class ConnectFour
