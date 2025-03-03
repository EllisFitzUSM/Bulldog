package llmsrc;

public interface GameEventListener {
    void onRoll(Player player, int roll);
    void onMessage(String message);
    void onTurnDecision(Player player, int currentTurnScore);
    void onTurnEnd(Player player, int scoreAdded);
}