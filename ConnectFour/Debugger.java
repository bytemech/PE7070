package ConnectFour;

/**
 * Debugger
 * This is a simple debug log controller used throughout the application to aid development.
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */

public class Debugger {
    private static boolean isEnabled = false; //Change value to enable/disable logging of debug messages to console. 

        /**
     * Log Method
     * This method logs to console, prepending "Debug: ", for any strings given, if the Debugger.isEnabled property is true.
     * the initial game board and menu.
     */
    public static void log(String s) {
        if (isEnabled) {
            System.out.println("Debug: " + s);
        }
    }
}