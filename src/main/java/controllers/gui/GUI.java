package controllers.gui;

import controllers.Game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public final class GUI extends Application {
    private Stage stage;
    private Game game;
    Group root;

    @Override
    public void start(Stage stage) {
        this.game = new Game(this);
        root = new Group();
        stage.setResizable(false);
        this.stage = stage;
        stage.setScene(new Menu(root, this));
        stage.show();
    }

    public void startOver(Stage stage) {
        this.stage.close();
        this.start(stage);
    }

    void startGame() {
        game.startGame();
    }

    void goToShop(Select earlierScene) {
        game.goToShop(earlierScene);
    }

    public void changeScene(Scene scene) {
        this.stage.setScene(scene);
    }

    public Stage getStage() {
        return stage;
    }

    public Group getRoot() {
        return root;
    }

    public void goFight() {
        game.startFight();
    }
}
