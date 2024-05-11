package se.reky.hakan.model;

public class Guard extends Actor {
    public Guard() {
        super("Guard", 20, 20);
    }
    public Guard(String name, int hp, int damage){
        super(name, hp, damage);
    }
}
