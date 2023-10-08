package ConnectFour;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ConnectFourTest.
 *
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class ConnectFourTest {
    private Slot moves[][];
    private Slot blankMoves[][];
    private ConnectFour fixture;

    @BeforeEach
    public void BeforeEach() {
        // Create a clean game instance and populate the test-local moves arrays with
        // Slots.
        fixture = new ConnectFour();
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
        blankMoves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        // Ensure the fixture and moves properties are cleared after each test.
        fixture = null;
        moves = null;
    }

    @Test
    public void checkForWinneronRow() {
        // Check that a winner by the row is correctly detected
        moves[0][0].setState("x");
        moves[0][1].setState("x");
        moves[0][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    @Test
    public void checkForWinneronColumn() {
        // Check that a winner by the column is correctly detected
        moves[0][0].setState("x");
        moves[1][0].setState("x");
        moves[2][0].setState("x");
        moves[3][0].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    @Test
    public void checkForWinneronRightDiagonal() {
        // Check that a winner by the right diagonal is correctly detected
        moves[0][0].setState("x");
        moves[1][1].setState("x");
        moves[2][2].setState("x");
        moves[3][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    @Test
    public void checkForWinneronLeftDiagonal() {
        // Check that a winner by the left diagonal is correctly detected
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    @Test
    public void checkForNoWinnerCorrectlyDetected() {
        // Check that when there is no winner this is correctly detected
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][4].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertNotEquals("x", x);
    }

    @Test
    void testAddMove() {
        fixture.addMove(0, true);
        assertEquals("x",fixture.getMoves()[4][0].getState());
    }

    @Test
    void testClearGame() {
        blankMoves = fixture.getMoves();
        moves[0][0].setState("x");
        fixture.setMoves(moves);
        fixture.clearGame();
        boolean allBlank = true;

        for (int r = fixture.getRowCount() - 1; r >= 0; r--) {
            for (int c = fixture.getColCount() - 1; c >= 0; c--) {
                if (!fixture.getMoves()[r][c].getState().equals("-")) {
                    allBlank = false;
                }
            }
        }

        assertEquals(true, allBlank);
    }

    @Test
    void testGetMoves() {
        // Test that getMoves returns a moves object
        assertSame(moves.getClass(), fixture.getMoves().getClass());
    }

    @Test
    void testSetAndGetMoves() {
        // Test that setMoves sets moves that are accurately returned by getMoves.
        moves[0][0].setState("x");
        fixture.setMoves(moves);
        assertEquals(moves, fixture.getMoves());
    }
}
