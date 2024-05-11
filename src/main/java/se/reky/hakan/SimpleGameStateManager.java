package se.reky.hakan;

import se.reky.hakan.model.Goblin;
import se.reky.hakan.model.Guard;
import se.reky.hakan.model.Player;
import se.reky.hakan.model.Actor;

public class SimpleGameStateManager implements GameStateManager {
    private Player player;
    private final IOHandler ioHandler;

    public SimpleGameStateManager(IOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    @Override
    public void executeTownGate() {
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("You are at the gate of the town.");
        ioHandler.output("A guard is standing in front of you.\n");
        ioHandler.output("What do you want to do?\n");
        ioHandler.output("1: Talk to the guard");
        ioHandler.output("2: Attack the guard");
        ioHandler.output("3: Leave");
        ioHandler.output("\n------------------------------------------------------------------\n");

        int choice = ioHandler.nextInt();
        ioHandler.nextLine();

        switch (choice) {
            case 1:
                if (player.isSilverRingObtained()) {
                    ending();
                } else {
                    ioHandler.output("Guard: Hello there, stranger. So your name is " + player.getName() + "? Sorry, but we cannot let a stranger enter our town.");
                    ioHandler.output("Press RETURN to continue");
                    ioHandler.nextLine();
                    executeTownGate();
                }
                break;
            case 2:
                executeFight(new Guard());
                break;
            case 3:
                executeCrossRoad();
                break;
            default:
                executeTownGate();
        }
    }



    @Override
    public void executeFight(Actor opponent) {
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("Ok, so you want to fight! Let's rumble!");

        while (opponent.isAlive() && player.isAlive()) {
            ioHandler.output(String.format("Your HP: %d", player.getHp()));
            ioHandler.output(String.format("Opponent HP: %d", opponent.getHp()));
            ioHandler.output("\n1: Attack");
            ioHandler.output("2: Run");
            ioHandler.output("\n------------------------------------------------------------------\n");

            int choice = ioHandler.nextInt();
            if (choice == 1) {
                player.attack(opponent);
                if (opponent.isAlive()) {
                    opponent.attack(player);
                }
            } else {
                executeCrossRoad();
                return;
            }
        }

        if (!player.isAlive()) {
            ioHandler.output("You have died.");
            ioHandler.nextLine();
            return;
        }

        ioHandler.output("You defeated the opponent!");
        if (opponent instanceof Goblin) {
            player.setSilverRingObtained(true);
            player.addExperience(5);
            ioHandler.output("The goblin dropped a silver ring! You pick it up and return to the crossroad.");

        }
        executeCrossRoad();
    }

    @Override
    public void executeCrossRoad() {
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("You are at a crossroad. If you go south, you will go back to the town.\n\n");
        ioHandler.output("1: Go north");
        ioHandler.output("2: Go east");
        ioHandler.output("3: Go south");
        ioHandler.output("4: Go west");
        ioHandler.output("5: Give up");
        ioHandler.output("\n------------------------------------------------------------------\n");

        int choice = ioHandler.nextInt();

        switch (choice) {
            case 1:
                executeNorth();
                break;
            case 2:
                executeEast();
                break;
            case 3:
                executeTownGate();
                break;
            case 4:
                executeWest();
                break;
            case 5:
                giveUp();
                break;
            default:
                executeCrossRoad();
        }
    }

    @Override
    public void executeNorth() {
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("There is a river. You drink the water and rest at the riverside.");
        player.setHp(player.getHp() + 1);
        ioHandler.output("Your HP is recovered by 1. You return to the crossroad");
        player.addExperience(1);
        executeCrossRoad();
    }

    @Override
    public void executeEast() {
        ioHandler.output("\n------------------------------------------------------------------\n");
        if (player.getWeapon() == null) {
            player.setWeapon("Long Sword"); // Assuming Weapon constructor takes name and damage
            ioHandler.output("You walked into a forest and found a Long Sword!");
            ioHandler.output("You return to the crossroad.");
            player.addExperience(2);
        } else {
            ioHandler.output("You have already picked up the weapon.");
        }
        player.addExperience(1);
        executeCrossRoad();
    }

    @Override
    public void executeWest() {
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("You encounter a goblin!");
        executeFight(new Goblin());
        player.addExperience(1);
    }

    private void ending() {
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("Guard: Oh you have a silver ring! That's the sign of a hero. Welcome to our town!");
        ioHandler.output("\n\n           THE END                    ");
        ioHandler.output("\n------------------------------------------------------------------\n");
        player.addExperience(10);
    }

    private void giveUp(){
        ioHandler.output("\n------------------------------------------------------------------\n");
        ioHandler.output("You gave up cowardly. Your experience will be set to -1");
        ioHandler.output("\n\n           THE END                    ");
        ioHandler.output("\n------------------------------------------------------------------\n");
        player.setExperience(-1);
    }
    @Override
    public void setPlayer(Player player){
        this.player = player;
    }
    @Override
    public Player getPlayer(){
        return player;
    }
}

