package se.reky.hakan;

import se.reky.hakan.model.Player;
import se.reky.hakan.service.PlayerService;

//import se.reky.hakan.model.*;
//import se.reky.hakan.model.Character;
//import se.reky.hakan.service.PlayerService;
//import java.util.Scanner;
//
public class Game {
    private final IOHandler ioHandler; // Handles all inputs and outputs
    private final GameStateManager gameStateManager;
    private final PlayerInteraction playerInteraction;
    private boolean running = true;
    private PlayerService playerService;

    public Game(IOHandler ioHandler, GameStateManager gameStateManager,
                PlayerInteraction playerInteraction, PlayerService playerService) {
        this.ioHandler = ioHandler;
        this.gameStateManager = gameStateManager;
        this.playerInteraction = playerInteraction;
        this.playerService = playerService;
    }

    void start() {
        if (!ioHandler.promptForNewGame()) {
            running = false;
        }
        while (running) {
            Player player = getNewPlayer();
            this.gameStateManager.setPlayer(player);
            this.playerInteraction.setPlayer(player);
            playRound();
            playerService.savePlayerStats(gameStateManager.getPlayer());
            if (!ioHandler.promptForContinue()) {
                running = false;
            }


        }
        ioHandler.displayEndGameMessage();
    }

    void playRound() {
        playerInteraction.setupPlayer();
        gameStateManager.executeTownGate();
    }

    private Player getNewPlayer(){
        return new Player();
    }
    boolean isRunning(){
        return running;
    }

    boolean isPlayerAlive(){
        return gameStateManager.isPlayerAlive();
    }
}
//
//    private final Scanner myScanner;
//    private Player player;
//    int choice;
//    boolean silverRingObtained = false;
//    boolean running = true;
//    private final PlayerService playerService;
//
//    public Game(Scanner scanner, PlayerService playerService){
//        this.myScanner = scanner;
//        this.playerService = playerService;
//
//    }
//
//    void start() {
//        System.out.println("Do you want to start a new game? (yes/no)");
//        if(! myScanner.nextLine().equalsIgnoreCase("yes")){
//            running = false;
//        }
//        while (running) {
//            playRound(); // Method that runs the game logic for a single round
//            // After a round is finished, prompt the player
//            System.out.println("Do you want to play again? (yes/no)");
//            String input = myScanner.nextLine();
//            if (!"yes".equalsIgnoreCase(input)) {
//                running = false; // Stops the game loop and exits the game
//            }
//            // If the input is "yes", the loop continues and starts a new round
//        }
//        System.out.println("Thank you for playing!");
//    }
//    //TODO: åtkomst till metoderna?
//    //TODO: formattera input-strängar?
//    void playRound() {
//        playerSetUp();
//        townGate();
//    }
//
//
//    void playerSetUp() {
//        player = new Player("Default Hero", 10, 2); // Initial player damage is 2 for example
//        System.out.printf("Your HP: %d%n", player.getHp());
//        System.out.println("Please enter your name:");
//
//        player.setName(myScanner.nextLine());
//
//        System.out.printf("Hello %s, let's start the game!%n", player.getName());
//    }
//
//    void townGate() {
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("You are at the gate of the town.");
//        System.out.println("A guard is standing in front of you.");
//        System.out.println();
//        System.out.println("What do you want to do?");
//        System.out.println();
//        System.out.println("1: Talk to the guard");
//        System.out.println("2: Attack the guard");
//        System.out.println("3: Leave");
//        System.out.println("\n------------------------------------------------------------------\n");
//
//        choice = myScanner.nextInt();
//        myScanner.nextLine();
//
//        Guard guard = new Guard(); // Assuming Guard has default attributes
//
//        switch (choice) {
//            case 1:
//                if (silverRingObtained) {
//                    ending();
//                } else {
//                    System.out.printf("Guard: Hello there, stranger. So your name is %s? Sorry but we cannot let a stranger enter our town.%n", player.getName());
//                    System.out.println("Press RETURN to continue");
//                    myScanner.nextLine();
//                    townGate();
//                }
//                break;
//            case 2:
//                fight(guard);
//                break;
//            case 3:
//                crossRoad();
//                break;
//            default:
//                townGate();
//        }
//    }
//
//    void fight(Character opponent) {
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("Ok, so you want to fight me? Let's rumble!");
//        System.out.printf("Your HP: %d%n", player.getHp());
//        System.out.printf("Opponent HP: %d%n", opponent.getHp());
//        System.out.println("\n1: Attack");
//        System.out.println("2: Run");
//        System.out.println("\n------------------------------------------------------------------\n");
//
//        choice = myScanner.nextInt();
//
//        if (choice == 1) {
//            player.attack(opponent);
//            opponent.attack(player);
//            if (!player.isAlive()) {
//                dead();
//            } else if (!opponent.isAlive()) {
//                System.out.println("You defeated the opponent!");
//                if (opponent instanceof Goblin) {
//                    silverRingObtained = true; // Assume the player obtains a silver ring from defeating a Goblin
//                    System.out.println("The goblin dropped a silver ring! You pick it up.");
//                    player.addExperience(3);
//                }
//                crossRoad();
//            } else {
//                fight(opponent);
//            }
//        } else if (choice == 2) {
//            crossRoad();
//        } else {
//            fight(opponent);
//        }
//    }
//
//
//    private void crossRoad() {
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("You are at a crossroad. If you go south, you will go back to the town.\n\n");
//        System.out.println("1: Go north");
//        System.out.println("2: Go east");
//        System.out.println("3: Go south");
//        System.out.println("4: Go west");
//        System.out.println("\n------------------------------------------------------------------\n");
//
//        choice = myScanner.nextInt();
//
//        switch (choice) {
//            case 1:
//                north();
//                break;
//            case 2:
//                east();
//                break;
//            case 3:
//                townGate();
//                break;
//            case 4:
//                west();
//                break;
//            default:
//                crossRoad();
//        }
//    }
//
//    private void north() {
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("There is a river. You drink the water and rest at the riverside.");
//        System.out.println("Your HP is recovered by 1.");
//        player.setHp(player.getHp() + 1);
//        player.addExperience(1);
//        System.out.printf("Your HP: %d%n", player.getHp());
//        System.out.println("\n\n1: Go back to the crossroad");
//        System.out.println("\n------------------------------------------------------------------\n");
//
//        choice = myScanner.nextInt();
//        if (choice == 1) {
//            crossRoad();
//        } else {
//            north();
//        }
//    }
//
//    private void west() {
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("You encounter a goblin!\n");
//
//        Goblin goblin = new Goblin(); // Create a new instance of Goblin
//
//        System.out.println("1: Fight");
//        System.out.println("2: Run");
//        System.out.println("\n------------------------------------------------------------------\n");
//
//        choice = myScanner.nextInt();
//        player.addExperience(1);
//        if (choice == 1) {
//            fight(goblin); // Engage in a fight with the goblin
//        } else if (choice == 2) {
//            crossRoad(); // Escape back to the crossroad
//        } else {
//            west(); // If an invalid choice is made, stay in the west encounter
//        }
//    }
//
//    private void east() {
//        System.out.println("\n------------------------------------------------------------------\n");
//        if(player.getWeapon() == null){
//            System.out.println("You walked into a forest and found a Long Sword!");
//            player.setWeapon("Long Sword"); // Assuming Sword has name and damage
//            System.out.printf("Your Weapon: %s%n", player.getWeapon());
//            player.addExperience(1);
//        }
//        else {
//            System.out.println("Nothing special here");
//        }
//        System.out.println("\n\n1: Go back to the crossroad");
//        System.out.println("\n------------------------------------------------------------------\n");
//        player.addExperience(1);
//        choice = myScanner.nextInt();
//        if (choice == 1) {
//            crossRoad();
//        } else {
//            east();
//        }
//    }
//    // Other game methods remain largely unchanged but should be updated to integrate with the new system
//    // Methods like `north()`, `east()`, `west()`, and combat-related logic need to be adjusted accordingly
//
//    void dead() {
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("You are dead!!!");
//        System.out.printf("Your score: %d%n", player.getExperience());
//        System.out.println("\n\nGAME OVER");
//        System.out.println("\n------------------------------------------------------------------\n");
//        playerService.savePlayerStats(player);
//        resetMyScanner();
//    }
//
//    void ending() {
//        player.addExperience(10);
//        System.out.println("\n------------------------------------------------------------------\n");
//        System.out.println("Guard: Oh you killed that goblin!?? Great!");
//        System.out.println("Guard: It seems you are a trustworthy guy. Welcome to our town!");
//        System.out.println();
//        System.out.printf("Your score: %d%n", player.getExperience());
//        System.out.println("\n\n           THE END                    ");
//        System.out.println("\n------------------------------------------------------------------\n");
//        playerService.savePlayerStats(player);
//        resetMyScanner();
//    }
//
//    private void resetMyScanner(){
//        myScanner.nextLine();
//    }
//
//    String getPlayerName(){
//        return player.getName();
//    }
//
//    int getPlayerHp(){
//        return player.getHp();
//    }
//
//    boolean isRunning(){
//        return running;
//    }
//
//    void setSilverRingObtained(boolean silverRingObtained){
//        this.silverRingObtained = silverRingObtained;
//    }
//
//    void setPlayer(Player player){
//        this.player = player;
//    }



