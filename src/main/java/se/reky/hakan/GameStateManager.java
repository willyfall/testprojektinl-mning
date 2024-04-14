package se.reky.hakan;
import se.reky.hakan.model.Character;
import se.reky.hakan.model.Player;

public interface GameStateManager {
    void executeTownGate();
    void executeFight(Character opponent);
    void executeCrossRoad();
    void executeNorth();
    void executeEast();
    void executeWest();

    boolean isPlayerAlive();
    Player getPlayer();
    void setPlayer(Player player);
}

