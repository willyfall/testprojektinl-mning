package se.reky.hakan;
import se.reky.hakan.model.Actor;
import se.reky.hakan.model.Player;

public interface GameStateManager {
    void executeTownGate();
    void executeFight(Actor opponent);
    void executeCrossRoad();
    void executeNorth();
    void executeEast();
    void executeWest();
    Player getPlayer();
    void setPlayer(Player player);
}

