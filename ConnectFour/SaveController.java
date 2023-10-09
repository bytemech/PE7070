package ConnectFour;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SaveController {
    private static String userHome = System.getProperty("user.home");
    private static String saveFileExtension = ".connectFourSave";

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

    public static boolean readSaveFile(ConnectFour game) {
        File saveFile = findSaveFile();

        if (!saveFile.exists()) {
            return false;
        } else {
            try {
                Debugger.log("Reading from: " + saveFile.getAbsolutePath());
                ObjectInputStream readerStream = new ObjectInputStream(new FileInputStream(saveFile));
                Slot[][] moveSlots = (Slot[][]) readerStream.readObject();
                Debugger.log("State read as: " + Arrays.deepToString(moveSlots));
                readerStream.close();
                game.setMoves(moveSlots);
                saveFile.delete(); //Remove the save file once this is read into the game.
                return true;
            } catch (Exception e) {
                Debugger.log(e.toString());
                return false;
            }
        }
    }
}