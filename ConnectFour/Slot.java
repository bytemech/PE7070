 
import java.io.Serializable;
import java.util.Observable;

/**
 * Slot
 * This class handles the state of each slot in the game. Extending observable in preparation for the addition of a GUI.
 * @author Lauren Scott & Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class Slot extends Observable implements Serializable {
    private String state;//The current state of the slot
    private int row, col;//The row and column number of the slot 
    /**
     * Constructor of the class slot
     * This creates the slot and denotes where it is placed in the game board.
     * 
     * @param col - the slot's column number
     * @param row - the slot's row number
     */
    public Slot (int col, int row) {
        this.row = row;
        this.col = col;
        this.state = ConnectFour.EMPTYSLOT;
        
    }
    /**
     * setState
     * This method sets the current state of the slot and notifies any observers.
     * @param newState - the new state of the slot
     */
    public void setState(String newState) {
        if(isValidState(newState) == true) {
            this.state = newState;
            setChanged();
            notifyObservers();
        } 
    }
    /**
     * getState
     * This provides the current state of the slot
     * @return the current state of the slot
     */
    public String getState(){
        return state;
        
    }
    /**
     * getRow
     * This provides the current row of the slot
     * @return the current row of the slot
     */
    public int getRow(){
        return row;
        
    }
    /**
     * getCol
     * This provides the current column of the slot
     * @return the current column of the slot
     */
    public int getCol(){
        return col;
        
    }
    /**
     * isValidState 
     * This method checks whether the selected state is valid
     * @param state - the state that is being checked
     * @return a Boolean value showing whether the state is valid or not
     */
    public static boolean isValidState(String state) {
        if (state.equals(ConnectFour.COMPUTERMOVE)) {
            return true;
        } else if (state.equals(ConnectFour.PLAYERMOVE)){
            return true;
        } else if (state.equals(ConnectFour.EMPTYSLOT) ){
            return true;
        } else{
            return false;
        }
    }  
    /**
     * toString() 
     * This method overrides the toString() method to allow desired formatting when this object is converted toString(). 
     * Used for debugging the values in the state of the game vs. what is being shown in the UI. 
     * @return the current state property as a string.
     */
    @Override
    public String toString() {
        return this.state.toString();
    }

}//End of class Slot

