package ConnectFour;

/**
 * Assign
 * This class handles the creation of all moves in the game
 * @author Lauren Scott
 * @version Student Sample Code
 */
public class Assign {
    private int col, row;//The row and column being assigned
    private ConnectFour game;//The game 
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
        assignMove(player);
        
    }
    /**
     * calculateRow
     * This method finds the lowest empty slot in the selected column, which will be assigned
     * @param c - the selected column
     * @return the row value
     */
    public int calculateRow(int c) {
        
        Boolean columnAvailable = false;
        int rowCount = game.getRowCount();
        do {
            if(moves[rowCount][c].getState().equals(ConnectFour.EMPTYSLOT)) {
                columnAvailable = true;
            } else {
                rowCount--;
            }
            
        } while (columnAvailable == false);
        return rowCount;
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
            moves[row][row].setState(ConnectFour.COMPUTERMOVE);
    }
    /**
     * getRow
     * This method returns the current row value for this move. It allows another element of the program to access this move's current row.
     * @return the row value
     */
    public int getRow() {
        return row;
    }
}//End of class Assign
