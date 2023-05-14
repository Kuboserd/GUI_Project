package model;

public abstract class Statistics {
    protected int health;
    protected int attack;
    protected int defense;
    protected int agility;

    protected boolean poisoned = false;

    public boolean isPoisoned() {
        return poisoned;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Statistics(int health, int attack, int defense, int agility) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
    }
}
