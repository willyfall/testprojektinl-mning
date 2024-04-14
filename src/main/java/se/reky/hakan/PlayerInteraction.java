package se.reky.hakan;
import se.reky.hakan.model.Player;

public interface PlayerInteraction {
    void setupPlayer();
    void updatePlayerHealth(int hp);
    void greetPlayer();
    void setPlayer(Player player);
}