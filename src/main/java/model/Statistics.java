package model;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Statistics {
    protected int health;
    protected int attack;
    protected int defense;
    protected int agility;
    protected HashMap<ITEM_TYPE, Item[]> equipment = new HashMap();
    protected boolean poisoned = false;

    public void addItem(Item item) throws Exception {
        if (this.equipment.containsKey(item.getType())) {
            Item[] tempArray = this.equipment.get(item.getType());
            if (tempArray.length == item.getType().getMaxAmmount()) {
                throw new Exception("Can't wear nore items of type " + item.getType());
            } else {
                tempArray = Arrays.copyOf(tempArray, tempArray.length + 1);
                this.equipment.replace(item.getType(), tempArray);
            }
        } else {
            this.equipment.put(item.getType(), new Item[]{item});
        }
    }

    public void dropItem(Item item) throws Exception {
        if (this.equipment.containsKey(item.getType())) {
            Item[] array = this.equipment.get(item.getType());
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(item)) {
                    if (array.length == 1) {
                        this.equipment.remove(item.getType());
                    } else {
                        Item temp = array[i];
                        array[0] = temp;
                        array = Arrays.copyOf(array, array.length - 1);
                        this.equipment.replace(item.getType(), array);
                    }
                } else if (i == array.length - 1) {
                    throw new Exception("You don't poses such an item");
                }
            }
        } else {
            throw new Exception("You don't poses any items of this type");
        }
    }

    public void attack(Statistics defender) {
        int attackEffectiveness = defender.defense - this.getAttack();
        int damage = 0;
        if (this.equipment.get(ITEM_TYPE.WEAPON).length == 0) {
            damage = (int) (3 + Math.random() * 3);
        } else {
            for (Item item : this.equipment.get(ITEM_TYPE.WEAPON)) {
                damage += item.attackDamage();
                defender.setPoisoned(item.willItPoison());
            }
        }
        defender.health -= (int) (damage + damage * 0.1 * attackEffectiveness);
    }

    public int takePoisonDamage() {
        int damage = 3 + (int) (Math.random() * 4);
        this.health -= damage;
        return damage;
    }

    public boolean run() {
        float value = (float) (Math.random() * 10);
        return (value + 0.1 * this.agility) >= 5;
    }

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
