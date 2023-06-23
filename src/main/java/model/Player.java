package model;

public class Player extends Statistics {
    private int wallet = 0;
    private boolean enraged = false;

    @Override
    public void attack(Statistics defender) {
        boolean poison = false;
        if (this.getEquipment().containsKey(ITEM_TYPE.WEAPON)) {
            for (Item item : this.equipment.get(ITEM_TYPE.WEAPON)) {
                if (item.poisoned)
                    poison = true;
            }
        }
        if (poison && Math.random() * 10 > 9) {
            defender.setPoisoned(true);
        }
        super.attack(defender);
        if (enraged) {
            this.enraged = false;
            this.attack(defender);
        }
    }

    public Player(int health, int attack, int defense, int agility, int wallet) {
        super(health, attack, defense, agility);
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public boolean isEnraged() {
        return enraged;
    }

    public void setEnraged(boolean enraged) {
        this.enraged = enraged;
    }
}
