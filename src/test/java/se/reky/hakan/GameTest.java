package se.reky.hakan;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import se.reky.hakan.model.Guard;
import se.reky.hakan.model.Player;
import se.reky.hakan.repository.PlayerRepository;
import se.reky.hakan.service.PlayerService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameTest {

    static PlayerService playerServiceMock;
    IOHandler ioHandler;
    SimplePlayerInteraction playerInteraction;
    SimpleGameStateManager gameStateManager;
    Game game;

    @BeforeAll
    public static void beforeAll() {
        PlayerRepository playerRepositoryMock = mock(PlayerRepository.class);
        playerServiceMock = new PlayerService(playerRepositoryMock);
    }

    private void initializeGameWithUserInput(List<String> userInput) {
        Player player = new Player();
        Scanner scannerMock = new ScannerUtil(userInput).getScanner();
        ioHandler = new IOHandler(scannerMock);
        playerInteraction = new SimplePlayerInteraction(player, ioHandler);
        gameStateManager = new SimpleGameStateManager(player, ioHandler);
        game = new Game(ioHandler, gameStateManager, playerInteraction, playerServiceMock);
    }

    @Test
    public void testPlayGameAttackingGuardAndPlayerIsDead() {
        List<String> userInput = Arrays.asList("yes", "John Doe", "2", "1", "no");
        initializeGameWithUserInput(userInput);
        game.start();
        assertFalse(game.isPlayerAlive());
    }
    @Test
    public void testUserSaysNoToPlayingResultingInGameNotRunning(){
        List<String> userInput = List.of("no");
        initializeGameWithUserInput(userInput);
        game.start();
        assertFalse(game.isRunning());
    }



//    @Test
//    public void testGameExitsWhenNotAnsweringYesToStartNewGame() {
//        Scanner scannerMock = mock(Scanner.class);
//        when(scannerMock.nextLine()).thenReturn("no");
//        Game game = new Game(scannerMock, playerServiceMock);
//        game.start();
//
//        assertFalse(game.isRunning());
//    }
//
//    @Test
//    public void testPlayerLosesFight() {
//        Scanner mockScanner = mock(Scanner.class);
//        when(mockScanner.nextInt()).thenReturn(1); // Choose to fight
//        when(mockScanner.nextLine()).thenReturn(""); // Mimic user pressing enter
//
//        Player player = new Player("Hero", 0, 5);
//        Guard guard = new Guard("guard", 10, 5);
//
//        Game game = new Game(mockScanner, playerServiceMock);
//        game.setPlayer(player);
//        game.fight(guard);
//
//        assertFalse(player.isAlive());
//
//    }
//
//    @Test
//    public void testThis() {
//        Scanner mockScanner = new Scanner("yes\nJohn Doe\n1\nno\n");
//        Game game = new Game(mockScanner, playerServiceMock);
//        game.setSilverRingObtained(true);
//        game.start();
//
//    }

    //"yes\nJohn Doe\n1\nno\n


}