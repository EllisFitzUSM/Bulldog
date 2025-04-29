import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Scoreboard Viewer. Creates a temporary scoreboard between turns which is disposed and GC.
 * @author DeepSeek
 * @version April 14th, 2025
 */
class ScoreboardViewer extends JDialog {

    private JButton okButton;

    /**
     * Creates a Scoreboard JDialog
     * @param frame JFrame to be added to.
     * @param model Model which provides players and their associated scores.
     */
    public ScoreboardViewer(JFrame frame, GameModel model) {
        super(frame, "Current Scores", true);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 200));
        setLocationRelativeTo(frame);

        // Table model setup
        String[] columns = {"Player", "Score"};
        Object[][] data = new Object[model.getNumberOfPlayers()][2];
        for(int i = 0; i < model.getNumberOfPlayers(); i++) {
            data[i][0] = model.getPlayerName(i);
            data[i][1] = model.getPlayerScore(i);
        }

        JTable table = new JTable(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Style table
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setRowHeight(25);
        table.getColumnModel().getColumn(1).setCellRenderer(new RightAlignRenderer());

        okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Listen to the ok button press
     * @param l provided listener to call
     */
    public void addOkButtonActionListener(ActionListener l) {
        okButton.addActionListener(l);
    }

    /**
     * RightAlignRenderer aligns elements to the right.
     * @author DeepSeek
     * @version April 14th, 2025
     */
    private static class RightAlignRenderer extends DefaultTableCellRenderer {
        public RightAlignRenderer() {
            setHorizontalAlignment(JLabel.RIGHT);
        }
    }
}