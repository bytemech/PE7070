package ConnectFour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * SaveController
 * This class implements statics to facilitate File I/O for saving and reading
 * game state
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class SaveController {
    private static String userHome = System.getProperty("user.home");
    private static String saveFileExtension = ".connectFourSave";

    /**
     * Finds and returns the save file. It looks in the user home directory and if
     * it finds a file with the extension saveFileExtension.
     * 
     * 
     * @return The save file or null if not found or there is no save file to search
     *         for ( in which case the return value is null
     */
    private static File findSaveFile() {
        File dirObj = new File(userHome);
        File files[] = dirObj.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getAbsolutePath().contains(saveFileExtension)) {
                Debugger.log("Save file found: " + files[i].getAbsolutePath());
                return files[i];
            }
        }
        Debugger.log("No save files found.");
        return null;
    }

    /**
     * Writes the state of the game to a file.
     * 
     * @param game - Game to write the state of.
     */
    public static void writeSaveFile(ConnectFour game) {

        LocalDateTime timeStamp = LocalDateTime.now();
        File saveFile = new File(userHome, ((Integer.toString(timeStamp.hashCode()) + saveFileExtension)));
        Slot[][] moveSlots = game.getMoves();
        try {
            Debugger.log("Writing to: " + saveFile.getAbsolutePath());
            Debugger.log("Current state of the game is:" + Arrays.deepToString(moveSlots));
            saveFile.createNewFile();
            ObjectOutputStream writerStream = new ObjectOutputStream(new FileOutputStream(saveFile));
            writerStream.writeObject(moveSlots);
            writerStream.flush();
            writerStream.close();
        } catch (Exception e) {
            Debugger.log(e.toString());
        }

    }

    /**
     * Reads the save file from disk and sets the game state. This is done by
     * reading the state stored in the file and then deleting the file.
     * 
     * @param game - The ConnectFour to be modified
     * 
     * @return True if the file was read false if it wasn't found or could not be
     *         read ( in which case nothing is done )
     */
    public static boolean readSaveFile(ConnectFour game) {
        File saveFile = findSaveFile();

        if (saveFile == null || !saveFile.exists()) {
            return false;
        } else {
            try {
                Debugger.log("Reading from: " + saveFile.getAbsolutePath());
                ObjectInputStream readerStream = new ObjectInputStream(new FileInputStream(saveFile));
                Slot[][] moveSlots = (Slot[][]) readerStream.readObject();
                Debugger.log("State read as: " + Arrays.deepToString(moveSlots));
                readerStream.close();
                game.setMoves(moveSlots);
                saveFile.delete(); // Remove the save file once this is read into the game.
                return true;
            } catch (Exception e) {
                Debugger.log(e.toString());
                return false;
            }
        }
    }
}