package se.reky.hakan.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Actor {
    protected int hp = 0;
    protected String name;
    protected int damage;
    @Id
    @GeneratedValue
    private Long id;

    public Actor(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Actor() {

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attack(Actor other) {
        other.setHp(other.getHp() - this.damage);
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void setName(String name) {
        this.name = name;
    }


}