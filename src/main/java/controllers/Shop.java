package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ITEM_TYPE;
import model.Item;
import model.Player;

import java.util.ArrayList;
import java.util.Arrays;

public final class Shop {
    private ArrayList<Item> items;
    private ObservableList<Item> stock;

    public Shop() {
        this.items = new ArrayList<>();

        items.addAll(Arrays.asList(
                new Item("knife", 0, 0, 0, 0, ITEM_TYPE.WEAPON, 3, 3, 6, true),
                new Item("sword", 0, 0, 0, 0, ITEM_TYPE.WEAPON, 8, 5, 6),
                new Item("mace", 0, 0, 0, 0, ITEM_TYPE.WEAPON, 6, 1, 10),

                new Item("leather armor", 0, 0, 4, 0, ITEM_TYPE.ARMOR, 8, 0, 0),
                new Item("plate armor", 0, 0, 12, -4, ITEM_TYPE.ARMOR, 15, 0, 0),

                new Item("helm", 0, 0, 2, 0, ITEM_TYPE.HELM, 3, 0, 0),

                new Item("gloves of haste", 0, 0, 1, 2, ITEM_TYPE.GLOVES, 8, 0, 0),
                new Item("gloves of attack", 0, 2, 1, 0, ITEM_TYPE.GLOVES, 10, 0, 0),

                new Item("leather trousers", 0, 0, 1, 0, ITEM_TYPE.LEGGINGS, 2, 0, 0),

                new Item("ring of health", 5, 0, 1, 0, ITEM_TYPE.RINGS, 10, 0, 0),
                new Item("ring of supermancy", 5, 5, 5, 5, ITEM_TYPE.RINGS, 20, 0, 0),

                new Item("potion of health", 5, 0, 0, 0, ITEM_TYPE.CONSUMABLE, 3, 0, 0),
                new Item("potion of attack", 0, 5, 0, 0, ITEM_TYPE.CONSUMABLE, 5, 0, 0),
                new Item("potion of defense", 0, 0, 5, 0, ITEM_TYPE.CONSUMABLE, 6, 0, 0),
                new Item("potion of swiftness", 0, 0, 0, 5, ITEM_TYPE.CONSUMABLE, 4, 0, 0)
        ));
    }

    public void newStock() {
        Item item1 = items.get((int) (Math.random() * items.size()));
        Item item2 = items.get((int) (Math.random() * items.size()));
        Item item3 = items.get((int) (Math.random() * items.size()));
        this.stock = FXCollections.observableArrayList(item1, item2, item3);
    }

    public void buy(Item item, Player player) {
        try {
            if (player.getWallet() >= item.getCost()) {
                player.setWallet(player.getWallet() - item.getCost());
                player.addItem(item);
            } else {
                System.out.println("Not enough money");
            }
        } catch (Exception e) {
            player.dropItem(player.getEquipment().get(item.getType())[(int) (Math.random() * item.getType().getMaxAmmount())]);
            buy(item, player);
        }
    }

    public ObservableList<Item> getStock() {
        return stock;
    }
}
