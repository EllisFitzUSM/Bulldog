import javax.swing.*;
import java.awt.*;

/**
 * Bulldog player which is controlled by a Human to determine continuing their turn.
 * @author DeepSeek
 * @version March 3rd 2025
 */
public class HumanPlayer extends Player {
    private volatile boolean decision;
    private volatile boolean decisionMade;

    /**
     * Create a HumanPlayer with given name.
     * @param name Given name to assign to player
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Shows a dialog box to ask the human player if they want to continue their turn
     * @param gameStatus Status of the game
     * @param turnScore Current turn score
     * @param rollsCount Number of rolls made
     * @return true if player wants to continue, false otherwise
     */
    public boolean continueTurn(GameStatus gameStatus, int turnScore, int rollsCount) {
        // Create a custom dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Continue Turn?");
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        // Create message
        JLabel message = new JLabel(
                "<html><center>" +
                        "Current turn score: " + turnScore + "<br>" +
                        "Rolls made: " + rollsCount + "<br>" +
                        "Continue rolling?" +
                        "</center></html>",
                SwingConstants.CENTER
        );
        message.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Add action listeners
        yesButton.addActionListener(e -> {
            decision = true;
            decisionMade = true;
            dialog.dispose();
        });

        noButton.addActionListener(e -> {
            decision = false;
            decisionMade = true;
            dialog.dispose();
        });

        // Add components to dialog
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        dialog.add(message, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Configure and show dialog
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        // Wait for user decision
        while (!decisionMade) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        return decision;
    }


}