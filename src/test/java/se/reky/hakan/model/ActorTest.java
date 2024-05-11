package se.reky.hakan.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActorTest {
    Goblin goblin=new Goblin();
    Guard guard=new Guard();
    @Test
    public void actorAttackTest(){
        goblin.attack(guard);
        Assertions.assertEquals(guard.getHp(),17);
    }
}
