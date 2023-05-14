package model;

public class Item extends Statistics{
    private ITEM_TYPE type;
    private int cost;

    public Item(int health, int attack, int defense, int agility, ITEM_TYPE type, int cost) {
        super(health, attack, defense, agility);
        this.type = type;
        this.cost = cost;
    }
}
