package ConnectFour;

import java.util.Scanner;

/**
 * ConnectFourUI
 * Text-based user interface class for the ConnectFour game
 *
 * @author Lauren Scott & Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class ConnectFourUI {
    private Scanner consoleReader; // Reads inputs from the console
    private ConnectFour game; // The ConnectFour game
    private Slot moves[][];// 2D array of moves
    private String menuChoice = "x"; // user's selection from the menu, x by default

    /**
     * Constructor for the ConnectFourUI class.
     * This method creates the game, initialises the scanner to read input, displays
     * the initial game board and menu.
     */
    public ConnectFourUI() {
        consoleReader = new Scanner(System.in);
        startNewGame();
    }

    /**
     * Public static void main - the first method that runs when the project is run.
     * This method initialises a new instance of the ConnectFour UI.
     *
     * @param args
     */
    public static void main(String args[]) {
        ConnectFourUI ui = new ConnectFourUI();
    }

    /**
     * startNewGame
     * This method sets up a new game in the console, displaying the welcome
     * message, the game, and the menu.
     */
    public void startNewGame() {
        game = new ConnectFour();
        displayWelcomeMessage();
        displayGame();
        menu();
    }

    /**
     * Menu
     * This method displays the menu that provides users with the options needed to
     * play the game
     */
    public void menu() {
        while (!menuChoice.equalsIgnoreCase("Q")) {
            System.out.println("Please select an option: \n"
                    + "[M] make move\n"
                    + "[S] save game\n"
                    + "[L] load saved game\n"
                    + "[U] undo move\n"
                    + "[C] clear game\n"
                    + "[Q] quit game\n");
            menuChoice = getChoice();
        }
    }

    /**
     * getChoice
     * This method processes the selection the user has made in the menu
     *
     * @return the choice of the user
     */
    public String getChoice() {
        String choice = consoleReader.next();
        if (choice.equalsIgnoreCase("M")) {
            makeMove();
            checkWin();
        } else if (choice.equalsIgnoreCase("S")) {
            saveGame();
        } else if (choice.equalsIgnoreCase("U")) {
            undoMove();
        } else if (choice.equalsIgnoreCase("L")) {
            loadGame();
        } else if (choice.equalsIgnoreCase("C")) {
            clearGame();
        } else if (choice.equalsIgnoreCase("Q")) {
            System.exit(0);
        }
        return choice;
    }

    /**
     * saveGame
     * To be implemented by student - This method should save the current game state
     */
    public void saveGame() {
        System.out.println("Code not yet implemented");
    }

    /**
     * undoMove
     * To be implemented by student - this method should undo the previous move made
     * in the game, along with the corresponding computer move
     */
    public void undoMove() {
        System.out.println("Code not yet implemented");
    }

    /**
     * loadGame
     * To be implemented by student - this method should load a previous saved game
     */
    public void loadGame() {
        System.out.println("Code not yet implemented");
    }

    /**
     * clearGame
     * This method clears the game board and any record of moves, to reset the game
     */
    public void clearGame() {

        game.clearGame();
        displayGame();
        menu();

    }

    /**
     * displayWelcomeMessage
     * This method displays a welcome message to the user
     */
    public void displayWelcomeMessage() {
        System.out.println("**********************\n"
                + "Welcome to ConnectFour\n"
                + "**********************");
    }

    /**
     * displayGame
     * This method displays the current state of the board to the user.
     */
    public void displayGame() {
        moves = game.getMoves();

        for (int r = 0; r < game.getRowCount(); r++) {
            String spacers = "";
            for (int c = 0; c < game.getColCount(); c++) {
                if (moves[r][c].getState().equals(ConnectFour.PLAYERMOVE)) {
                    System.out.print("|" + ConnectFour.PLAYERMOVE);
                } else if (moves[r][c].getState().equals(ConnectFour.COMPUTERMOVE)) {
                    System.out.print("|" + ConnectFour.COMPUTERMOVE);
                } else {
                    System.out.print("| ");
                }
                spacers = spacers + " -";
            }
            System.out.print("|\n");
            System.out.println(spacers);
            System.out.println(" ");
        }
        String numbers = "";
        for (int c = 0; c < game.getColCount(); c++) {
            numbers = numbers + " " + Integer.toString(c);
        }
        System.out.println(numbers);
    }

    /**
     * makeMove
     * This method displays the move the player has made, followed by the
     * corresponding computer move
     */
    public void makeMove() {
        System.out.println("Please enter the column you wish to select");
        int playerMove = consoleReader.nextInt();
        boolean move = game.addMove(playerMove, true);
        if (!move) {
            displayGame();
            System.out.println("Your move was impossible. Please select another action.");
            displayGame();
        } else {

            game.generateComputerMove();
            displayGame();
            checkWin();
        }
    }

    /**
     * checkWin
     * This method displays the winner of the game
     */
    public void checkWin() {
        if (game.checkWin() != null) {
            System.out.println("And the winner is... " + game.checkWin());
            replay();
        }
    }

    /**
     * replay
     * This method gives the user the option to play again, or quit, once the game
     * is won
     */
    public void replay() {
        System.out.println("Would you like to play again? (Y/N)");
        String choice = consoleReader.next();
        if (choice.equalsIgnoreCase("Y")) {
            startNewGame();
        } else if (choice.equalsIgnoreCase("N")) {
            System.exit(0);

        }
    }
}// End of class ConnectFourUI
