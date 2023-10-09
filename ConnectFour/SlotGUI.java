package ConnectFour;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

/**
 * SlotGUI
 * This class implements a JTextField observer to display the state of the game
 * to the user/
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class SlotGUI extends JTextField implements Observer {

    /**
     * Class constructor - Updates the text displayed in the SlotGUI. This is called
     * when the class instantiates.
     * 
     * @param slot - The slot associated with this Observer.
     */
    public SlotGUI(Slot slot) {
        setEditable(false);
        setVisible(true);
        setHorizontalAlignment(JTextField.CENTER);
        setText(slot.getState());
    }

    /**
     * Updates the text displayed in the SlotGUI. This is called when the user
     * changes the state of the slot
     * 
     * @param o   - The Observable that notified us
     * @param arg - The argument that was passed to the Observable ( unused )
     */
    @Override
    public void update(Observable o, Object arg) {
        Debugger.log("Update called on SlotGUI");
        Slot slot = (Slot) o;
        this.setText(slot.getState());
    }
}