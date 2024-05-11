package se.reky.hakan.model;

public class Goblin extends Actor {
    public Goblin() {
        super("Goblin", 20, 3); // Example HP and damage
    }
    public Goblin(String name, int hp, int damage){
        super(name, hp, damage);
    }
}