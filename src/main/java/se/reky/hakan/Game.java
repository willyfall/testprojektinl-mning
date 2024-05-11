package se.reky.hakan;

import se.reky.hakan.model.Player;
import se.reky.hakan.service.PlayerService;

public class Game {
    private final IOHandler ioHandler; // Handles all inputs and outputs
    private final GameStateManager gameStateManager;
    private final PlayerInteraction playerInteraction;
    private boolean running = true;
    private final PlayerService playerService;
    private Player player;

    public Game(IOHandler ioHandler, GameStateManager gameStateManager,
                PlayerInteraction playerInteraction, PlayerService playerService) {
        this.ioHandler = ioHandler;
        this.gameStateManager = gameStateManager;
        this.playerInteraction = playerInteraction;
        this.playerService = playerService;
    }

    public void start() {
        if (!ioHandler.promptForNewGame()) {
            running = false;
        }
        while (running) {
            this.player = getNewPlayer();
            this.gameStateManager.setPlayer(this.player);

            playRound();
            playerService.savePlayerStats(gameStateManager.getPlayer());

            if (!ioHandler.promptForContinue()) {
                running = false;
            }
        }
        ioHandler.displayEndGameMessage();
    }

    private void playRound() {
        playerInteraction.setupPlayer(this.player);
        gameStateManager.executeTownGate();
    }

    private Player getNewPlayer(){
        return new Player();
    }
}




