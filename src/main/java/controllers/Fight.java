package controllers;

import controllers.gui.Select;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import model.Enemy;
import model.Player;

public class Fight {
    private Game game;
    private Player player;
    private Enemy enemy;

    public Fight(Game game, Player player, Enemy enemy) {
        this.game = game;
        this.player = player;
        this.enemy = enemy;
    }

    public void turn(GraphicsContext gc) {
        player.attack(enemy);
/*        animate(gc, true);*/
        if (enemy.isPoisoned()) {
            enemy.takePoisonDamage();
        }
        if (enemy.getHealth() <= 0) {
            player.setWallet(player.getWallet() + enemy.getAward());
            game.getGui().changeScene(new Select(game.getGui().getRoot(), game));
        } else {
            enemy.attack(player);
/*            animate(gc, false);*/
        }
        if (player.isPoisoned()) {
            player.takePoisonDamage();
        }
        if (player.getHealth() <= 0) {
            game.getGui().startOver(new Stage());
        }
    }

    public void runAway(GraphicsContext gc) {
        if (player.runAway()) {
            game.getGui().changeScene(new Select(game.getGui().getRoot(), game));
        } else {
            enemy.attack(player);
        }
        if (player.getHealth() <= 0) {
            game.getGui().startOver(new Stage());
        }
    }

/*    private void animate(GraphicsContext gc, Boolean player) {
        int start, end, step;
        int size = 128;
        Image image;
        if (player) {
            start = 300;
            end = 900;
            step = 30;
            image = new Image("swordman.png", 128, 128, true, true);
        } else {
            start = 900;
            end = 300;
            step = -30;
            image = new Image("mage.png", 128, 128, true, true);
        }
        for (int i = start; i < end; i += step) {
            gc.clearRect(i, 300, size, size);
            gc.drawImage(image, i + step, 300);
        }
        gc.clearRect(end + step, 300, size, size);
        gc.drawImage(image, start, 300);
    }*/
}
