package ConnectFour;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ConnectFourTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ConnectFourTest
{
    private Slot moves[][];
    private ConnectFour fixture;

    @BeforeEach
    public void BeforeEach() {
        //Create a clean game instance and populate the test-local moves array with Slots. 
        fixture = new ConnectFour();
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
    }

    @Test
    public void checkForWinneronRow() {
        //Check that a winner by the row is correctly detected
        moves[0][0].setState("x");
        moves[0][1].setState("x");
        moves[0][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForWinneronColumn() {
        //Check that a winner by the column is correctly detected
        moves[0][0].setState("x");
        moves[1][0].setState("x");
        moves[2][0].setState("x");
        moves[3][0].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForWinneronRightDiagonal() {
        //Check that a winner by the right diagonal is correctly detected
        moves[0][0].setState("x");
        moves[1][1].setState("x");
        moves[2][2].setState("x");
        moves[3][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForWinneronLeftDiagonal() {
        //Check that a winner by the left diagonal is correctly detected
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForNoWinnerCorrectlyDetected() {
        //Check that when there is no winner this is correctly detected
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][4].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertNotEquals("x",x);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        //Ensure the fixture and moves properties are cleared after each test.
        fixture = null;
        moves = null;
    }
}
