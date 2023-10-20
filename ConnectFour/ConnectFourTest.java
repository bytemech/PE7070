 

import static org.junit.jupiter.api.Assertions.*;
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
    private ConnectFour fixture;

    /**
     * Initializes the data before each test. This is called by the
     * TestCase#beforeEach ( Object ) method
     */
    @BeforeEach
    public void BeforeEach() {
        fixture = new ConnectFour();
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
    }

    /**
     * Clears the fixture and moves properties after each test.
     */
    @AfterEach
    public void tearDown() {
        // Ensure the fixture and moves properties are cleared after each test.
        fixture = null;
        moves = null;
    }

    /**
     * Check that a winner by the row is correctly detected
     */
    @Test
    public void checkForWinneronRow() {
        moves[0][0].setState("x");
        moves[0][1].setState("x");
        moves[0][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    /**
     * Checks to see if a winner is detected by the column.
     */
    @Test
    public void checkForWinneronColumn() {
        moves[0][0].setState("x");
        moves[1][0].setState("x");
        moves[2][0].setState("x");
        moves[3][0].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    /**
     * Checks if a winner by the right diagonal is detected.
     */
    @Test
    public void checkForWinneronRightDiagonal() {
        moves[0][0].setState("x");
        moves[1][1].setState("x");
        moves[2][2].setState("x");
        moves[3][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    /**
     * Checks if a winner by the left diagonal is detected.
     */
    @Test
    public void checkForWinneronLeftDiagonal() {
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x", x);
    }

    /**
     * Checks to see if there is no winner correctly detected in the grid. This is a
     * test for bug #
     */
    @Test
    public void checkForNoWinnerCorrectlyDetected() {
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][4].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertNotEquals("x", x);
    }

    /**
     * Tests adding a move to the state performs as expected.
     */
    @Test
    void testAddMove() {
        fixture.addMove(0, true);
        assertEquals("x", fixture.getMoves()[fixture.getRowCount() - 1][0].getState());
    }

    /**
     * Tests that the clearGame method clears the game to it's blank state.
     */
    @Test
    void testClearGame() {
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][4].setState("x");
        moves[0][3].setState("x");
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

    /**
     * Tests that getMoves returns an object of the correct type
     */
    @Test
    void testGetMoves() {
        assertSame(moves.getClass(), fixture.getMoves().getClass());
    }

    /**
     * Test that setMoves sets moves that are accurately returned by getMoves and
     * checks that the result is the same
     */
    @Test
    void testSetAndGetMoves() {
        moves[0][0].setState("x");
        fixture.setMoves(moves);
        assertEquals(moves, fixture.getMoves());
    }
}
