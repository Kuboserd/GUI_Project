package model;


public class Item extends Statistics {
    private String name;
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

    public Item(String name, int health, int attack, int defense, int agility, ITEM_TYPE type, int cost, int minDamage, int maxDamage) {
        super(health, attack, defense, agility);
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public Item(String name, int health, int attack, int defense, int agility, ITEM_TYPE type, int cost, int minDamage, int maxDamage, boolean poisoned) {
        super(health, attack, defense, agility, poisoned);
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", cost=" + cost +
                ", minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +
                ", health=" + health +
                ", attack=" + attack +
                ", defense=" + defense +
                ", agility=" + agility +
                '}';
    }

    public String getAttackDamage() {
        if (type == ITEM_TYPE.WEAPON) {
            return minDamage + "-" + maxDamage;
        } else {
            return "N/A";
        }
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ITEM_TYPE type) {
        this.type = type;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
