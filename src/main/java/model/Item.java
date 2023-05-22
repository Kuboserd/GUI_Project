package model;

public class Item extends Statistics {
    private ITEM_TYPE type;
    private int cost;
    private int minDamage, maxDamage;

    public int attackDamage() {
        if (this.type.compareTo(ITEM_TYPE.WEAPON) != 0)
            return -1;
        return minDamage + (int) (Math.random() * (maxDamage - minDamage));
    }

    public boolean willItPoison() {
        if (this.poisoned) {
            return !(Math.random() * 10 >= 2);
        }
        return false;
    }

    public int getCost() {
        return cost;
    }

    public ITEM_TYPE getType() {
        return type;
    }

    public Item(int health, int attack, int defense, int agility, ITEM_TYPE type, int cost, int minDamage, int maxDamage) {
        super(health, attack, defense, agility);
        this.type = type;
        this.cost = cost;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
}
