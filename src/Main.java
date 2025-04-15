import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.util.Objects;

/**
 * The main class for the Bulldog game with Swing GUI implementation.
 * Manages the game flow including player setup and turns through a graphical user interface.
 * Uses CardLayout to switch between different game phases:
 * 1. Title screen
 * 2. Player count input
 * 3. Player type selection
 * 4. Game execution JavaSwing
 * 5. Scoreboard JDialog & JTable
 * @author DeepSeek & Ellis Fitzgerald
 * @version 0.7
 */
public class Main implements GameEventListener {
    private final int WINNING_SCORE = 104;
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cards;
    private GameModel model;
    private int numPlayers;
    private JTextArea consoleOutput;
    private JButton yesButton;
    private JButton noButton;
    private int playerCreationIndex;

    /**
     * Main entry point for the application.
     * Invokes the GUI creation on the Event Dispatch Thread.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().initialize());
    }

    /**
     * Initialize the Game
     */
    private void initialize() {
        model = new GameModel();
        Referee.getInstance().setWinningScore(WINNING_SCORE);
        frame = new JFrame("Bulldog Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(createTitlePanel(), "TitleScreen");
        cards.add(createPlayerTypePanel(), "PlayerSelection");
        cards.add(createGamePanel(), "Game");

        cardLayout.show(cards, "TitleScreen");
        frame.add(cards);
        frame.setVisible(true);
    }

    /**
     * Creates the Title Panel which is used as the card
     * @return JPanel Title JPanel
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout(0, 20));
        JLabel titleLabel = new JLabel("Bulldog", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 64));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        inputPanel.add(new JLabel("How many players are playing?"), gbc);
        JTextField countField = new JTextField(10);
        ((AbstractDocument) countField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        inputPanel.add(countField, gbc);

        JButton startButton = new JButton("Start");
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.setFont(new Font("Serif", Font.BOLD, 14));
        startButton.setPreferredSize(new Dimension(130, 50));
        inputPanel.add(startButton, gbc);

        startButton.addActionListener(e -> onStartOrEnter(countField));
        countField.addActionListener(e -> onStartOrEnter(countField));

        titlePanel.add(inputPanel, BorderLayout.CENTER);
        return titlePanel;
    }

    /**
     * When the player count is entered or the start button is pressed.
     * @param countField The text field used
     */
    private void onStartOrEnter(JTextField countField) {
        try {
            numPlayers = Integer.parseInt(countField.getText());
            if (numPlayers > 1) {
                cardLayout.show(cards, "PlayerSelection");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Must have at least 2 players!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame,
                    "Please enter a valid number >= 2",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Dynamically creates and configures the player type selection panel.
     * @return Player Type Panel
     */
    private JPanel createPlayerTypePanel() {
        model = new GameModel();

        JPanel playerTypePanel = new JPanel(new BorderLayout(0, 20));
        JPanel currentPlayerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        JLabel instructionLabel = new JLabel("Configure Player " + (playerCreationIndex + 1), SwingConstants.CENTER);
        JLabel typeLabel = new JLabel("Select player type:");
        String[] playerTypes = {"Human", "Random", "Fifteen", "Wimp", "Unique", "AIUnique"};
        JComboBox<String> playerTypeComboBox = new JComboBox<>(playerTypes);
        JButton nextButton = new JButton("Next Player");

        currentPlayerPanel.add(instructionLabel, gbc);
        currentPlayerPanel.add(typeLabel, gbc);
        currentPlayerPanel.add(playerTypeComboBox, gbc);
        currentPlayerPanel.add(nextButton, gbc);

        playerTypePanel.add(currentPlayerPanel, BorderLayout.CENTER);

        nextButton.addActionListener(e -> {
            while (true) {
                String name = (String) JOptionPane.showInputDialog(frame,
                        "Enter name for Player " + (playerCreationIndex + 1),
                        "Player Name",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Player " + (playerCreationIndex + 1));
                if (name == null || name.trim().isEmpty()) {
                    name = "Player " + (playerCreationIndex + 1);
                }
                if (!isDuplicatedName(name)) {
                    Player player = createPlayer(
                            (String) Objects.requireNonNull(playerTypeComboBox.getSelectedItem()),
                            name.trim());
                    model.addPlayer(player);
                    break;
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Player already exists with that name!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            playerCreationIndex++;

            if (playerCreationIndex < numPlayers) {
                instructionLabel.setText("Configure Player " + (playerCreationIndex + 1));
                playerTypeComboBox.setSelectedIndex(0);
            } else {
                startGame();
            }
        });
        return playerTypePanel;
    }

    /**
     * Checks if a string is a duplicate of other names.
     * @param name the potential name to check if it is a duplicate
     * @return true if duplicate, false if not duplicate
     */
    private boolean isDuplicatedName(String name) {
        for (int i = 0; i < model.getNumberOfPlayers(); i++) {
            if (name.equals(model.getPlayerName(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Factory method to create Player instances based on type selection.
     * @param type The player type string from the combo box
     * @param name The name of the player to create
     * @return Concrete Player instance of the specified type
     */
    private Player createPlayer(String type, String name) {
        Player player;
        DiceSuper die = new PsuedoRandomDice(6);
        switch (type) {
            case "Human":
                player = new HumanPlayer(name, die);
                break;
            case "Random":
                player = new RandomPlayer(name, die);
                break;
            case "Fifteen":
                player = new FifteenPlayer(name, die);
                break;
            case "Wimp":
                player = new WimpPlayer(name, die);
                break;
            case "Unique":
                player = new UniquePlayer(name, die);
                break;
            case "AIUnique":
                player = new AIPlayer(name, die);
                break;
            default:
                player = new RandomPlayer(name, die);
                break;
        }
        player.setGameEventListener(this);
        return player;
    }

    /**
     * Creates and initializes the Game Panel
     * @return Game JPanel
     */
    private JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(220, 220, 220));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JPanel consolePanel = new JPanel(new BorderLayout());
        consolePanel.setOpaque(false);
        consolePanel.setBackground(new Color(255, 255, 255, 200));
        consolePanel.setPreferredSize(new Dimension(frame.getWidth(), (int)(frame.getHeight() * 0.3)));

        consoleOutput = new JTextArea(5, 40);
        consoleOutput.setEditable(false);
        consoleOutput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane consoleScroll = new JScrollPane(consoleOutput);

        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        consolePanel.add(consoleScroll, BorderLayout.CENTER);
        consolePanel.add(buttonPanel, BorderLayout.SOUTH);
        consolePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        gamePanel.add(consolePanel, BorderLayout.SOUTH);

        yesButton.addActionListener(e -> {
            if (Referee.getInstance().getCurrentPlayer(model) instanceof HumanPlayer) {
                HumanPlayer humanPlayer = (HumanPlayer) Referee.getInstance().getCurrentPlayer(model);
                humanPlayer.makeDecision(true);
            }
        });

        noButton.addActionListener(e -> {
            if (Referee.getInstance().getCurrentPlayer(model) instanceof HumanPlayer) {
                HumanPlayer humanPlayer = (HumanPlayer) Referee.getInstance().getCurrentPlayer(model);
                humanPlayer.makeDecision(false);
            }
        });

        return gamePanel;
    }

    /**
     * Transitions from GUI setup to the console-based game execution.
     * Closes the GUI window and starts the main game loop.
     * Handles turn order and win condition checking.
     */
    private void startGame() {
        cardLayout.show(cards, "Game");
        Referee.getInstance().startGame(model, this);
//        currentPlayerIndex = 0;
//        startNextTurn();
    }


    /**
     * Simple function to add a message to the "visual console"
     * @param message the message to print/add to the console.
     */
    private void addConsoleMessage(String message) {
        consoleOutput.append(message + "\n");
        consoleOutput.setCaretPosition(consoleOutput.getDocument().getLength());
    }

    /**
     * Listener function which is called on a Player turn start
     * @param player Player whose turn started
     */
    @Override
    public void onTurnStart(Player player) {
        yesButton.setVisible(false);
        noButton.setVisible(false);

        consoleOutput.setText("");
        addConsoleMessage("It is " + Referee.getInstance().getCurrentPlayer(model).getName() + "'s turn!\n");
        addConsoleMessage("Total Score: " + Referee.getInstance().getCurrentPlayer(model).getScore());
    }

    /**
     * Listener function which is called on a Player turn end.
     * @param player Player whose turn ended
     * @param turnScore how much score they were given this turn.
     */
    @Override
    public void onTurnEnd(Player player, int turnScore) {
        addConsoleMessage("\nTurn Results:");
        addConsoleMessage("Score added: " + turnScore);
        addConsoleMessage("New total: " + player.getScore() + "\n");

//        if (player.getScore() >= WINNING_SCORE) {
//            onGameOver(player);
//        } else {
        showScoreboard();
//        }
    }

    /**
     * Function to be called when player has rolled
     * @param player The player that just rolled
     * @param roll The score from the roll
     */
    @Override
    public void onRoll(Player player, int roll) {
        SwingUtilities.invokeLater(() -> {
            consoleOutput.append(player.getName() + " rolled: " + roll + "\n");
        });
    }

    /**
     * When a player makes a turn decision. Limited to just a HumanPlayer for now.
     * @param player Input player that made a decision on their turn.
     * @param currentTurnScore Their currentTurnScore as of the decision.
     */
    @Override
    public void onTurnDecision(Player player, int currentTurnScore) {
        SwingUtilities.invokeLater(() -> {
            if (player instanceof HumanPlayer) {
                yesButton.setVisible(true);
                noButton.setVisible(true);
                consoleOutput.append("Turn total: " + currentTurnScore + "\nRoll again?\n");
            }
        });
    }

    @Override
    public void onGameOver(Player winner) {
        addConsoleMessage("\n\n!!! GAME OVER !!!");
        addConsoleMessage(winner.getName() + " WINS WITH " + winner.getScore() + " POINTS!");
        yesButton.setVisible(false);
        noButton.setVisible(false);
    }

    /**
     * Called when the scoreboard is shown
     */
    private void showScoreboard() {
        SwingUtilities.invokeLater(() -> {
            ScoreboardViewer dialog = new ScoreboardViewer(frame, model);
            dialog.setVisible(true);
        });
    }

    //    /**
//     * Called when a player wins the game
//     * @param winner the Player who reached the score
//     */
//    private void gameOver(Player winner) {
//        addConsoleMessage("\n\n!!! GAME OVER !!!");
//        addConsoleMessage(winner.getName() + " WINS WITH " + winner.getScore() + " POINTS!");
//        yesButton.setVisible(false);
//        noButton.setVisible(false);
//    }

    /**
     * Called when it is a new players turn.
     * Will give expected display for a player's turn.
     */
//    private void startNextTurn() {
//        Player currentPlayer = model.getPlayer(currentPlayerIndex);
//        yesButton.setVisible(false);
//        noButton.setVisible(false);
//
//        consoleOutput.setText("");
//        addConsoleMessage("It is " + currentPlayer.getName() + "'s turn!\n");
//        addConsoleMessage("Total Score: " + currentPlayer.getScore());
//
//        messageTimer = new Timer(MESSAGE_DELAY, e -> {
//            messageTimer.stop();
//            new Thread(() -> executePlayerTurn(currentPlayer)).start();
//        });
//        messageTimer.start();
//    }
//
//    /**
//     * Called when a player is expected to execute their turn.
//     * @param player The player that will have their play method called.
//     */
//    private void executePlayerTurn(Player player) {
//        new Thread(() -> {
//            int result = player.play();
//            SwingUtilities.invokeLater(() -> handleTurnResult(player, result));
//        }).start();
//    }
//
//    /**
//     * Called when a Player has decided (or was forced to) end their turn.
//     * @param player The player who just executed their turn.
//     * @param turnScore The amount of score they earned this turn.
//     */
//    private void handleTurnResult(Player player, int turnScore) {
//        model.addToPlayerScore(currentPlayerIndex, turnScore);
//        addConsoleMessage("\nTurn Results:");
//        addConsoleMessage("Score added: " + turnScore);
//        addConsoleMessage("New total: " + model.getPlayerScore(currentPlayerIndex) + "\n");
//
//        if (model.getPlayerScore(currentPlayerIndex) >= WINNING_SCORE) {
//            gameOver(player);
//        } else {
//            showScoreboard();
//            currentPlayerIndex = (currentPlayerIndex + 1) % model.getNumberOfPlayers();
//            startNextTurn();
//        }
//    }

}