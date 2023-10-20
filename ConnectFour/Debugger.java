 

import javax.swing.JTextArea;

/**
 * Debugger
 * This is a simple debug log controller used throughout the application to aid
 * development.
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */

public class Debugger {
    private static boolean isEnabled = false; // Change value to enable/disable logging of debug messages to console.
    public static JTextArea textArea = null;

    /**
     * Log Method
     * This method logs to console, prepending "Debug: ", for any strings given, if
     * the Debugger.isEnabled property is true. If the Debugger has a textArea
     * reference, then print to the TextArea. 
     * @param s String to print to debug log.
     * 
     */
    public static void log(String s) {
        if (isEnabled) {
            if (textArea == null) {
                System.out.println("Debug: " + s);
            } else {
                textArea.append("Debug: " + s + "\r\n");
            }
        }
    }
}
