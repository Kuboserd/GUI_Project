package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene menu = new Scene(root);
        stage.setScene(menu);
        Canvas canvas = new Canvas(1280, 720);
        root.getChildren().add(canvas);
        GraphicsContext context = canvas.getGraphicsContext2D();
        Image mainMenu = new Image("menu.jpg", 1280, 720, false, false);
        context.drawImage(mainMenu, 0, 0);

        Text text = new Text();
        text.setFont(new Font(45));
        text.setFill(Color.WHITE);
        text.setX(530);
        text.setY(100);
        text.setText("POKEMON");
        root.getChildren().add(text);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(265);
        gridPane.add(new Button("TEST1"), 2,10);
        gridPane.add(new Button("TEST2"), 2,11);
        gridPane.add(new Button("TEST3"), 2,12);

        root.getChildren().add(gridPane);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
