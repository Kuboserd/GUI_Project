 package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        StackPane root = new StackPane();

        BackgroundImage backgroundImage = new BackgroundImage(new Image("files:resources/menu.png", 1920, 1080, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Scene menu = new Scene(root, 600, 400);
        root.setBackground(new Background(backgroundImage));
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
