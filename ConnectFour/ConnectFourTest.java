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

    @Test
    public void checkForWinneronRow() {
        ConnectFour fixture = new ConnectFour();
      
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
        moves[0][0].setState("x");
        moves[0][1].setState("x");
        moves[0][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForWinneronColumn() {
        ConnectFour fixture = new ConnectFour();
      
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
        moves[0][0].setState("x");
        moves[1][0].setState("x");
        moves[2][0].setState("x");
        moves[3][0].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForWinneronRightDiagonal() {
        ConnectFour fixture = new ConnectFour();
      
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
        moves[0][0].setState("x");
        moves[1][1].setState("x");
        moves[2][2].setState("x");
        moves[3][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForWinneronLeftDiagonal() {
        ConnectFour fixture = new ConnectFour();
      
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
        moves[3][0].setState("x");
        moves[2][1].setState("x");
        moves[1][2].setState("x");
        moves[0][3].setState("x");
        String x = fixture.checkGrid(moves, "x", "x");
        assertEquals("x",x);
    }

    @Test
    public void checkForNoWinnerCorrectlyDetected() {
        ConnectFour fixture = new ConnectFour();
      
        moves = new Slot[fixture.getRowCount()][fixture.getColCount()];
        for (int c = 0; c < fixture.getColCount(); c++) {
            for (int r = 0; r < fixture.getRowCount(); r++) {
                moves[r][c] = new Slot(r, c);
            }
        }
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

        //Ensure the moves array is cleared after each test.
        moves = null;
    }
}
