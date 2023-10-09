package ConnectFour;

import java.util.Arrays;
import java.util.Random;

/**
 * ConnectFour
 * This is the main controller class for the game ConnectFour
 * 
 * @author Lauren Scott & Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class ConnectFour {
    private Slot[][] moves;// 2D array of the moves of the game
    public static final String EMPTYSLOT = "-"; // Blank slot game state
    public static final String PLAYERMOVE = "x";// Player slot game state
    public static final String COMPUTERMOVE = "o";// computer slot game state
    private static final int rowCount = 5; // Should be a natural number, beginning at 1.
    private static final int colCount = 6; // Should be a natural number, beginning at 1.
    private static int[] lastPlayerMove = new int[2];
    private static int[] lastComputerMove = new int[2];

    /**
     * Constructor of the class ConnectFour
     * Initialises the slots of the game ready to play
     */
    public ConnectFour() {
        createGame();
    }

    /**
     * createGame
     * Creates the game. This is called by the constructor
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
     * This method returns a 2D array with all of the current moves in the game.
     * 
     * @return the moves in the game currently
     */
    public Slot[][] getMoves() {
        return moves;
    }

    /**
     * setMoves
     * This method sets a 2D array with all of the current moves in the game.
     * 
     * @param moveSlots - An array of Slot
     */
    public void setMoves(Slot[][] moveSlots) {
        moves = moveSlots;
    }

    /**
     * setLastMove
     * This method sets the last moved property on the ConnectFour class.
     * 
     * @param row    - The row of the last move
     * @param col    - The column of the last move
     * @param player - True if the player is moving
     */
    public void setLastMove(int row, int col, boolean player) {
        if (player) {
            lastPlayerMove[0] = row;
            lastPlayerMove[1] = col;
        } else {
            lastComputerMove[0] = row;
            lastComputerMove[1] = col;
        }
    }

    /**
     * undoLastMove
     * This method undoes the last moves in the game state.
     * 
     */
    public void undoLastMove() {
        moves[lastPlayerMove[0]][lastPlayerMove[1]].setState(EMPTYSLOT);
        moves[lastComputerMove[0]][lastComputerMove[1]].setState(EMPTYSLOT);
    }

    /**
     * getRowCount
     * This method returns a 2D array with all of the current moves in the game.
     * This is a natural number.
     * 
     * @return the number of rows in the game, as an int.
     */
    public int getRowCount() {
        return ConnectFour.rowCount;
    }

    /**
     * getColCount
     * This method returns a 2D array with all of the current moves in the game
     * This is a natural number.
     * 
     * @return the number of columns in the game, as an int.
     */
    public int getColCount() {
        return ConnectFour.colCount;
    }

    /**
     * addMove
     * This method adds a move to the game
     * 
     * @param col    - The column to move to
     * @param player - True if the player is allowed to move
     * 
     * @return True if the move can be
     */
    public boolean addMove(int col, boolean player) {
        Assign move = new Assign(this, col, player);
        // Return whether the move was able to be assigned.
        return move.ableToAssign();
    }

    /**
     * generateComputerMove
     * This method generates the computer's move using a random number generator
     */
    public void generateComputerMove() {
        Random randomGen = new Random();

        // Given the computer tries to make an invalid move (and addMove is false), then
        // retry until addMove is not false.
        boolean move = false;
        do {
            move = addMove(randomGen.nextInt(colCount), false);
        } while (!move);

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
        Debugger.log("Checked for a winner - none found.");
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
        Debugger.log("checkGrid: current grid = " + Arrays.deepToString(moves));
        // Start at bottom of grid and move up. Convert rowcount to int/index from
        // natural number.
        for (int r = rowCount - 1; r >= 0; r--) {
            Debugger.log("checkGrid: Checking row " + r + "for " + MOVETOKEN);
            // Start at left hand side of grid and move across. Convert rowcount to
            // int/index from natural number.
            for (int c = colCount - 1; c >= 0; c--) {
                if (moves[r][c].getState().equals(MOVETOKEN)) {
                    Debugger.log("checkGrid: Found " + MOVETOKEN + " in Row " + r + " column " + c);

                    // Get move values to string - to allow debugging and inspection, as well as
                    // comparison values.
                    // Start with empty string, and then fill in with the values - catching invalid
                    // index
                    // exceptions. Empty strings rather than null to prevent errors with .equals()
                    // comparisons.
                    String rcplus1 = "";
                    String rcplus2 = "";
                    String rcplus3 = "";
                    String rplus1c = "";
                    String rplus2c = "";
                    String rplus3c = "";
                    String rplus1cplus1 = "";
                    String rplus2cplus2 = "";
                    String rplus3cplus3 = "";
                    String rplus1cminus1 = "";
                    String rplus2cminus2 = "";
                    String rplus3cminus3 = "";

                    try {
                        rcplus1 = moves[r][c + 1].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rcplus2 = moves[r][c + 2].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rcplus3 = moves[r][c + 3].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus1c = moves[r + 1][c].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus2c = moves[r + 2][c].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus3c = moves[r + 3][c].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus1cplus1 = moves[r + 1][c + 1].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus2cplus2 = moves[r + 2][c + 2].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus3cplus3 = moves[r + 3][c + 3].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus1cminus1 = moves[r + 1][c - 1].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus2cminus2 = moves[r + 2][c - 2].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        rplus3cminus3 = moves[r + 3][c - 3].getState();
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                    Debugger.log("checkGrid: Row " + r + " column " + c + " rcplus1: " + rcplus1);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rcplus2: " + rcplus2);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rcplus3: " + rcplus3);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus1c: " + rplus1c);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus3c: " + rplus3c);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus1cplus1: " + rplus1cplus1);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus2cplus2: " + rplus2cplus2);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus3cplus3: " + rplus3cplus3);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus1cminus1: " + rplus1cminus1);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus2cminus2: " + rplus2cminus2);
                    Debugger.log("checkGrid: Row " + r + " column " + c + " rplus3cminus3: " + rplus3cminus3);

                    // Check for 4 in same row
                    if (rcplus1.equals(MOVETOKEN) && rcplus2.equals(MOVETOKEN) && rcplus3.equals(MOVETOKEN)) {
                        Debugger.log("checkGrid: Found winner by 4 in row " + MOVETOKEN);
                        Debugger.log("checkGrid: Beginning in Row " + r + " column " + c);
                        Debugger.log(rcplus1 + "," + rcplus2 + "," + rcplus3);
                        return returnValue;
                    }
                    // Check for 4 in same column
                    else if (rplus1c.equals(MOVETOKEN) && rplus2c.equals(MOVETOKEN) && rplus3c.equals(MOVETOKEN)) {
                        Debugger.log("checkGrid: Found winner by 4 in column " + MOVETOKEN);
                        Debugger.log("checkGrid: Beginning in Row " + r + " column " + c);
                        Debugger.log(rplus1c + "," + rplus2c + "," + rplus3c);
                        return returnValue;
                    }
                    // Check for 4 in rising, right-handed diagonal (check starts from bottom left)
                    else if (rplus1cplus1.equals(MOVETOKEN) && rplus2cplus2.equals(MOVETOKEN)
                            && rplus3cplus3.equals(MOVETOKEN)) {
                        Debugger.log("checkGrid: Found winner by right-handed diagonal in column " + MOVETOKEN);
                        Debugger.log("checkGrid: Beginning in Row " + r + " column " + c);
                        Debugger.log(rplus1cplus1 + "," + rplus2cplus2 + "," + rplus3cplus3);
                        return returnValue;
                    }
                    // Check for 4 in rising, left-handed, diagonal (check starts from bottom left)
                    else if (rplus1cminus1.equals(MOVETOKEN) && rplus2cminus2.equals(MOVETOKEN)
                            && rplus3cminus3.equals(MOVETOKEN)) {
                        Debugger.log("checkGrid: Found winner by right-handed diagonal in column " + MOVETOKEN);
                        Debugger.log("checkGrid: Beginning in Row " + r + " column " + c);
                        Debugger.log(rplus1cplus1 + "," + rplus2cplus2 + "," + rplus3cplus3);
                        return returnValue;
                    }
                }
            }
        }
        Debugger.log("checkGrid: " + MOVETOKEN + " not found to be a winner.");
        return null; // If nothing found, returns null.
    }

}// End of class ConnectFour
