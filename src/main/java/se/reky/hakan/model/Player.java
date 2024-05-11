package se.reky.hakan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Player extends Actor {
    private String weapon;
    private int experience = 0;
    private boolean silverRingObtained;
    @Id
    @GeneratedValue
    private Long id;

    public Player(String name, int hp, int damage) {
        super(name, hp, damage);

    }

    public Player() {
        super();
    }

    public boolean isSilverRingObtained() {
        return silverRingObtained;
    }

    public void setSilverRingObtained(boolean silverRing) {
        this.silverRingObtained = silverRing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addExperience(int experience) {

        this.experience += experience;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}