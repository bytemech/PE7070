package ConnectFour;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class SlotGUI extends JTextField implements Observer  {
    
    public SlotGUI(Slot slot) {
        setEditable(false);
        setVisible(true);
        setHorizontalAlignment(JTextField.CENTER);
        setText(slot.getState());
    }

    @Override
    public void update(Observable o, Object arg) {
        Debugger.log("Update called on SlotGUI");
        Slot slot = (Slot) o;
        this.setText(slot.getState());
    }
}