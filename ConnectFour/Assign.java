package ConnectFour;

/**
 * Assign
 * This class handles the creation of all moves in the game
 * @author Lauren Scott & Byron Glover
 * @version Assignment Hand-In - PE7070
 */

public class Assign {
    private int col, row;//The row and column being assigned
    private ConnectFour game;//The game 
    private boolean ableToAssign;
    Slot[][] moves;//2D Array to store the game's moves
    /**
     * Constructor for Assign class.
     * This gets the total number of moves and calls methods to determine the row that will be filled, and to set the state of the slot being assigned.
     * @param game - the game
     * @param col - the column the user has selected
     * @param player - a Boolean value that determines whether it is a player/computer move
     */
    public Assign(ConnectFour game, int col, boolean player){
        this.game = game;
        moves = game.getMoves();
        this.col = col;
        this.row = calculateRow(col);
        if (this.row == -1) {
            //If calculateRow returns a -1, then this move cannot be assigned, set the abletoAssign flag to false and return without further action.
            this.ableToAssign = false;
            return;
        }
        assignMove(player);
        this.ableToAssign =  true;
    }
    /**
     * calculateRow
     * This method finds the lowest empty slot in the selected column, which will be assigned. Returns a negative int if no match found.
     * @param c - the selected column
     * @return the row value
     */
    public int calculateRow(int c) {
        for (int r = game.getRowCount() - 1; r >= 0 ; r--) { //-1 from getRowCount as cannot index "r" for array of size "r" as size is a natural number, and indexes are integers. 
            if(moves[r][c].getState().equals(ConnectFour.EMPTYSLOT)) {
                return r;
            }
        }
        return -1;
    }
    /**
     * assignMove
     * This method assigns the move to the game
     * @param player a Boolean value to determine whether it is a computer/player move
     */
    public void assignMove(boolean player) {
        if (player == true) 
            moves[row][col].setState(ConnectFour.PLAYERMOVE);
        else
            moves[row][col].setState(ConnectFour.COMPUTERMOVE);
    }
    /**
     * getRow
     * This method returns the current row value for this move. It allows another element of the program to access this move's current row.
     * @return the row value
     */
    public int getRow() {
        return row;
    }
    /**
     * ableToAssign
     * This method returns whether the move is able to be assigned to a grid. If not, returns false.
     * @return the row value
     */
    public boolean ableToAssign() {
        return ableToAssign;
    }

}//End of class Assign
