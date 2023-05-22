package model;

public class Player extends Statistics {
    private int wallet = 0;
    private boolean enraged = false;

    @Override
    public void attack(Statistics defender) {
        //TODO: make enrage do something :D
        super.attack(defender);
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
