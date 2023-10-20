 

import java.util.Scanner;

/**
 * ConnectFourTUI
 * Text-based user interface class for the ConnectFour game
 *
 * @author Lauren Scott & Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class ConnectFourTUI extends ConnectFourUI {
    private Scanner consoleReader; // Reads inputs from the console
    private String menuChoice = "x"; // user's selection from the menu, x by default

    /**
     * Constructor for the ConnectFourUI class.
     * This method creates the game, initialises the scanner to read input, displays
     * the initial game board and menu.
     */
    public ConnectFourTUI() {
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
        ConnectFourTUI ui = new ConnectFourTUI();
    }

    /**
     * startNewGame
     * This method sets up a new game in the console, displaying the welcome
     * message, the game, and the menu.
     */
    @Override
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
    @Override
    public void saveGame() {
        SaveController.writeSaveFile(game);
        displayGame();
        printToUser("Game saved.");
        menu();

    }

    /**
     * undoMove
     * Undoes the previous move made in the game, along with the corresponding computer move
     */
    @Override
    public void undoMove() {
        game.undoLastMove();
        displayGame();
        printToUser("Last move has been undone.");
        menu();
    }

    /**
     * loadGame
     * Loads the saved state of the game.
     */
    @Override
    public void loadGame() {
        if (SaveController.readSaveFile(game)) {
            displayGame();
            printToUser(
                    "Game loaded from save. Please make sure if you want to keep this game saved, save the game again.");
            menu();
        } else {
            displayGame();
            printToUser("No saved game was found.");
            menu();
        }
    }

    /**
     * clearGame
     * This method clears the game board and any record of moves, to reset the game.
     */
    @Override
    public void clearGame() {
        game.clearGame();
        displayGame();
        printToUser("No saved game was found.");
        menu();
    }

     /**
     * printToUser
     * This method is for printing information to the user - to allow them to understand the game flow.
     * @param message - the message to be sent to the user
     */
    
     @Override
     public void printToUser(String message) {
         System.out.println(message);
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
            printToUser("Your move was impossible. Please select another action.");
            menu();
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
    @Override
    public void checkWin() {
        if (game.checkWin() != null) {
            printToUser("And the winner is... " + game.checkWin());
            replay();
        }
    }

    /**
     * replay
     * This method gives the user the option to play again, or quit, once the game
     * is won
     */
    @Override
    public void replay() {
        System.out.println("Would you like to play again? (Y/N)");
        String choice = consoleReader.next();
        if (choice.equalsIgnoreCase("Y")) {
            startNewGame();
        } else if (choice.equalsIgnoreCase("N")) {
            System.exit(0);

        }
    }

    @Override
    public void displayWelcomeMessage() {
        printToUser("**********************\n"
                + "Welcome to ConnectFour\n"
                + "**********************");
    }

}// End of class ConnectFourUI
