package se.reky.hakan;

import se.reky.hakan.model.Player;

public class SimplePlayerInteraction implements PlayerInteraction {
    private final IOHandler ioHandler;

    public SimplePlayerInteraction(IOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    @Override
    public void setupPlayer(Player player) {
        ioHandler.output("Please enter your name:");
        String playerName = ioHandler.nextLine();
        player.setName(playerName);
        player.setHp(10);  // Default health
        player.setDamage(7); // Default damage
        ioHandler.output(String.format("Hello %s, your adventure begins with %d HP and %d damage.", playerName, player.getHp(), player.getDamage()));
    }

    @Override
    public void updatePlayerHealth(Player player, int hp) {
        player.setHp(player.getHp() + hp);
        ioHandler.output(String.format("Updated HP: %d", player.getHp()));
    }

    @Override
    public void greetPlayer(Player player) {
        ioHandler.output(String.format("Welcome %s, let's start the game!", player.getName()));
    }
}

