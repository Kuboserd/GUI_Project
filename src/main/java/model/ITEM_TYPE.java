package model;

public enum ITEM_TYPE {
    HELM(1),
    ARMOR(1),
    LEGGINGS(1),
    GLOVES(1),
    RINGS(2),
    CONSUMABLE(5),
    WEAPON(2);

    private int maxAmmount;

    public int getMaxAmmount() {
        return maxAmmount;
    }

    public void setMaxAmmount(int maxAmmount) {
        this.maxAmmount = maxAmmount;
    }

    ITEM_TYPE(int maxAmmount) {
        this.maxAmmount = maxAmmount;
    }
}
