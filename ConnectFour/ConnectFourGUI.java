package ConnectFour;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectFourGUI extends ConnectFourUI implements ActionListener {
    private JLabel title = new JLabel("ConnectFour");
    private JPanel container = new JPanel();
    private JPanel grid = new JPanel(new GridLayout(game.getRowCount() + 1, game.getColCount())); // Add 1 to rowCount
                                                                                                  // for row controls.
    private JPanel controls = new JPanel();
    private JPanel controlButtons = new JPanel();
    private JTextArea textArea = new JTextArea(20, 20);

    public ConnectFourGUI() {
        Debugger.textArea = textArea;

        this.setTitle("ConnectFour");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(title);
        Dimension fillerDimension = new Dimension(Short.MAX_VALUE, 10);
        add(new Box.Filler(fillerDimension, fillerDimension, fillerDimension));

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        add(container);
        container.add(grid);
        displayGame();
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
        container.add(controls);

        Dimension buttonDimension = new Dimension(100, 30);
  
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> startNewGame());
        newGameButton.setMinimumSize(buttonDimension);
        controlButtons.add(newGameButton);

        JButton clearGameButton = new JButton("Clear Game");
        clearGameButton.addActionListener(e -> clearGame());
        clearGameButton.setMinimumSize(buttonDimension);
        controlButtons.add(clearGameButton);

        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(e -> saveGame());
        saveButton.setMinimumSize(buttonDimension);
        controlButtons.add(saveButton);

        JButton undoButton = new JButton("Undo Move");
        undoButton.addActionListener(e -> undoMove());
        undoButton.setMinimumSize(buttonDimension);
        controlButtons.add(undoButton);

        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(e -> loadGame());
        loadButton.setMinimumSize(buttonDimension);
        controlButtons.add(loadButton);

        controlButtons.setLayout(new GridLayout(controlButtons.getComponentCount(),1));
        controls.add(controlButtons);


        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        controls.add(scrollPane);

        setSize(500, 500);
        setVisible(true);

        displayWelcomeMessage();
    }

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
     * Public static void main - the first method that runs when the project is run.
     * This method initialises a new instance of the ConnectFour UI.
     *
     * @param args
     */
    public static void main(String[] args) {
        ConnectFourGUI ui = new ConnectFourGUI();
    }

    @Override
    public void startNewGame() {
        game.clearGame();
        displayGame();
        displayWelcomeMessage();
    }

    @Override
    public void saveGame() {
        SaveController.writeSaveFile(game);
        printToUser("Game saved.");
    }

    @Override
    public void undoMove() {
        game.undoLastMove();
        printToUser("Last move has been undone.");
    }

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

    @Override
    public void clearGame() {
        textArea.setText("");
        game.clearGame();
        displayGame();
    }

    @Override
    public void displayWelcomeMessage() {
        printToUser("**********************\n"
                + "Welcome to ConnectFour\n"
                + "**********************");
    }

    public void makeMove(int playerMove) {
        boolean move = game.addMove(playerMove, true);
        if (!move) {
            printToUser("Your move was impossible. Please select another action.");
        } else {
            game.generateComputerMove();
            checkWin();
        }
    }

    @Override
    public void checkWin() {
        if (game.checkWin() != null) {
            printToUser("And the winner is... " + game.checkWin());
            replay();
        }
    }

    @Override
    public void replay() {
        printToUser("Starting a new game.");
        startNewGame();
    }

    @Override
    public void printToUser(String message) {
        textArea.append(message + "\r\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
