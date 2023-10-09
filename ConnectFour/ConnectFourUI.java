package ConnectFour;

import javax.swing.JFrame;

public abstract class ConnectFourUI extends JFrame implements ConnectFourUIInterface {
    protected ConnectFour game = new ConnectFour(); // The ConnectFour game
    protected Slot moves[][];// 2D array of moves
}
