package se.reky.hakan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.reky.hakan.model.Player;

import java.util.Scanner;

public class PlayerInteractionTest {
    private PlayerInteraction playerInteraction(String dataforscanner){
        PlayerInteraction playerInteraction=new SimplePlayerInteraction(new IOHandler(new Scanner(dataforscanner)));
        return playerInteraction;
    }
    private Player player;
    @BeforeEach
    public void playerInitialize(){
        player=new Player();
    }
    @Test
    public void playerNameTest(){
        playerInteraction("William").setupPlayer(player);
        Assertions.assertEquals(player.getName(),"William");
    }
    @Test
    public void updatePlayerHealthTest(){
        playerInteraction("William").updatePlayerHealth(player, 25);
        Assertions.assertEquals(player.getHp(),25);
    }
}
