package ConnectFour;

import javax.swing.JFrame;

/**
 * ConnectFourUI
 * This is the parent abstract class for all UIs
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public abstract class ConnectFourUI extends JFrame implements ConnectFourUIInterface {
    protected ConnectFour game = new ConnectFour(); // The ConnectFour game
    protected Slot moves[][];// 2D array of moves
}
