package ConnectFour;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ConnectFour
 * This is the main GUI class and entrypoint for the ConnectFour Game
 * 
 * @author Byron Glover
 * @version Assignment Hand-In - PE7070
 */
public class ConnectFourGUI extends ConnectFourUI implements ActionListener {
    // Creates the panels and UI elements that shows the game's characteristics.
    private JLabel title = new JLabel("ConnectFour");
    private JPanel container = new JPanel();
    private JPanel grid = new JPanel(new GridLayout(game.getRowCount() + 1, game.getColCount())); // Add 1 to rowCount
                                                                                                  // for row controls.
    private JPanel controls = new JPanel();
    private JPanel controlButtons = new JPanel();
    private JTextArea textArea = new JTextArea(20, 20);

    // Class constructor - Sets up the GUI for ConnectFour.
    public ConnectFourGUI() {

        // Set the textArea to be used by the Debugger.
        Debugger.textArea = textArea;

        // Set exit on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the Window Title
        this.setTitle("ConnectFour");

        // Set the top-level JFrame Layout
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Add a title above the other UI components, with a filler between it and the
        // other components.
        add(title);
        Dimension fillerDimension = new Dimension(Short.MAX_VALUE, 10);
        add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        // Set a container to go under the title
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        add(container);

        // Add the game display grid
        container.add(grid);
        displayGame();

        // Add the control pane
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        container.add(controls);

        // Set up the New Game button, add it's listener method, and add it to a
        // controlButtons pane.
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> startNewGame());
        controlButtons.add(newGameButton);

        // Set up the Clear Game button, add it's listener method, and add it to a
        // controlButtons pane.
        JButton clearGameButton = new JButton("Clear Game");
        clearGameButton.addActionListener(e -> clearGame());
        controlButtons.add(clearGameButton);

        // Set up the Undo Move button, add it's listener method, and add it to a
        // controlButtons pane.
        JButton undoButton = new JButton("Undo Move");
        undoButton.addActionListener(e -> undoMove());
        controlButtons.add(undoButton);

        // Set up the Save Game button, add it's listener method, and add it to a
        // controlButtons pane.
        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(e -> saveGame());
        controlButtons.add(saveButton);

        // Set up the Load Game button, add it's listener method, and add it to a
        // controlButtons pane.
        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(e -> loadGame());
        controlButtons.add(loadButton);

        // Set the controlButtons pane to have as many rows as there are buttons, in 1
        // column, and add it to the control Pane.
        controlButtons.setLayout(new GridLayout(controlButtons.getComponentCount(), 1));
        controls.add(controlButtons);

        // Set up the user communication textArea, ensuring it's scrollable, readable,
        // and not editable.
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        controls.add(scrollPane);

        // Set the default application size, and ensure the JFrame is visible.
        setSize(500, 500);
        setVisible(true);

        // Display the welcome message to the user.
        displayWelcomeMessage();
    }

    /**
     * Public static void main - the first method that runs when the project is run.
     * This method initialises a new instance of the ConnectFour UI.
     *
     * @param args
     */
    public static void main(String[] args) {
        ConnectFourGUI ui = new ConnectFourGUI();
    }


    /**
     * displayGame
     * Displays the game on the screen.
     */
    private void displayGame() {
        grid.removeAll();
        for (int r = 0; r <= game.getRowCount(); r++) {
            for (int c = 0; c < game.getColCount(); c++) {

                if (r == game.getRowCount()) {
                    final Integer column = Integer.valueOf(c);
                    JButton colButton = new JButton(Integer.toString(c));
                    colButton.addActionListener(e -> makeMove(column));
                    grid.add(colButton);
                } else {
                    SlotGUI slotGUI = new SlotGUI(game.getMoves()[r][c]);
                    game.getMoves()[r][c].addObserver(slotGUI);
                    grid.add(slotGUI);
                }
                grid.doLayout();
            }
        }
    }

    /**
     * startNewGame
     * Starts a new game. Clears the game and displays the welcome message to the
     */
    @Override
    public void startNewGame() {
        game.clearGame();
        displayGame();
        displayWelcomeMessage();
    }

    /**
     * saveGame
     * Saves the game to disk and prints to the user the success
     */
    @Override
    public void saveGame() {
        SaveController.writeSaveFile(game);
        printToUser("Game saved.");
    }

    /**
     * undoMove
     * Undo the last move and print to user. This is called when user un - moves
     */
    @Override
    public void undoMove() {
        game.undoLastMove();
        printToUser("Last move has been undone.");
    }

    /**
     * loadGame
     * Loads the saved game from the save file and displays it to the user. If there
     * is no saved game the user is informed.
     */
    @Override
    public void loadGame() {
        if (SaveController.readSaveFile(game)) {
            printToUser(
                    "Game loaded from save. Please make sure if you want to keep this game saved, save the game again.");
            displayGame();
        } else {
            printToUser("No saved game was found.");
        }
    }

    /**
     * clearGame
     * Clears the game and refreshes the game display, including textArea. This is
     * called when the user clicks the clear button.
     */
    @Override
    public void clearGame() {
        textArea.setText("");
        game.clearGame();
        displayGame();
    }

    /**
     * displayWelcomeMessage
     * Displays a welcome message to the user.
     */
    @Override
    public void displayWelcomeMessage() {
        printToUser("**********************\n"
                + "Welcome to ConnectFour\n"
                + "**********************");
    }

    /**
     * makeMove
     * Makes a move and checks win. If there is no move to make a printToUser is
     * called
     * 
     * @param playerMove - column of the attempted move
     */
    public void makeMove(int playerMove) {
        boolean move = game.addMove(playerMove, true);
        if (!move) {
            printToUser("Your move was impossible. Please select another action.");
        } else {
            game.generateComputerMove();
            checkWin();
        }
    }

    /**
     * checkWin
     * Checks if the game has won and if so re - plays the game
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
     * Replays the game.
     */
    @Override
    public void replay() {
        printToUser("Starting a new game.");
        startNewGame();
    }

    /**
     * printToUser
     * Prints a message to the user in the textArea
     * 
     * @param message - The message to be printed
     */
    @Override
    public void printToUser(String message) {
        textArea.append(message + "\r\n");
    }

    /**
     * actionPerformed
     * NOT IMPLEMENTED. Included as class extends interface as required for JFrame,
     * but uses Lambda Functions to assign to button.
     * 
     * @param e - The ActionEvent that caused the
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
