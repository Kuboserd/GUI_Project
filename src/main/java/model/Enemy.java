package model;

public class Enemy extends Statistics{
    private int award;

    public Enemy(int health, int attack, int defense, int agility, int award) {
        super(health, attack, defense, agility);
        this.award = award;
    }
}
