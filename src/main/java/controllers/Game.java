package controllers;

import controllers.gui.Battle;
import controllers.gui.GUI;
import controllers.gui.Select;
import controllers.gui.ShopGUI;
import javafx.scene.Scene;
import model.Enemy;
import model.Player;

public final class Game {
    private final GUI gui;
    private final Player player;
    private final Shop shop;

    private Fight fight;
    private Enemy enemy;

    private boolean beenInShop;

    public Game(GUI gui) {
        this.gui = gui;
        this.player = new Player(30, 3, 3, 3, 6);
        this.enemy = Enemy.easy();
        this.shop = new Shop();
        this.beenInShop = false;
    }

    public void startGame() {
        gui.changeScene(new Select(gui.getRoot(), this));
    }

    public void goToShop(Select scene) {
        if (!beenInShop) {
            shop.newStock();
            beenInShop = true;
        }
        gui.changeScene(new ShopGUI(gui.getRoot(), this,  scene));
    }

    public void startFight() {
        this.enemy = Enemy.randomEncounter();
        this.fight = new Fight(this, player, this.enemy);
        gui.changeScene(new Battle(gui.getRoot(), this));
        beenInShop = false;
    }

    public Shop getShop() {
        return shop;
    }

    public GUI getGui() {
        return gui;
    }

    public Fight getFight() {
        return fight;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
