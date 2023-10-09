package ConnectFour;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * SaveControllerTest
 * This class implements tests for File I/O for saving and reading game state
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class SaveControllerTest {

    private Slot moves[][];
    private ConnectFour gameFixture;

    /**
     * Creates a game and sets up the moves array to be used in the test.
     */
    @BeforeEach
    public void BeforeEach() {
        // Create a clean game instance, clean savecontroller, and populate the
        // test-local moves array with Slots.
        gameFixture = new ConnectFour();
        moves = new Slot[gameFixture.getRowCount()][gameFixture.getColCount()];
        for (int c = 0; c < gameFixture.getColCount(); c++) {
            for (int r = 0; r < gameFixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
    }

    /**
     * Clears the move and game fixture after each test.
     */
    @AfterEach
    public void AfterEach() {
        moves = null;
        gameFixture = null;
    }

    /**
     * Tests that SaveController writes and reads the game's moves correctly.
     */
    @Test
    public void saveFileWritesAndReadsCorrectly() {

        moves[0][0].setState("x");
        moves[0][1].setState("x");
        moves[0][2].setState("x");
        moves[0][3].setState("x");

        gameFixture.setMoves(moves);
        System.out.println(Arrays.deepToString(gameFixture.getMoves()));
        SaveController.writeSaveFile(gameFixture);
        SaveController.readSaveFile(gameFixture);
        System.out.println(Arrays.deepToString(moves));
        System.out.println(Arrays.deepToString(gameFixture.getMoves()));

        assertEquals(Arrays.deepToString(moves), Arrays.deepToString(gameFixture.getMoves()));
    }

}
