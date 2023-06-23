package controllers.gui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Menu extends Scene {
    public Menu(Parent parent, GUI gui) {
        super(parent,1600,900);
        this.init(gui);
    }

    public void init( GUI gui) {
        StackPane root = new StackPane();
        root.setMaxHeight(320);
        root.setMaxWidth(640);
        Image background = new Image("menu.jpg",1600,900,true,true);
        ImageView menuBackground = new ImageView(background);

        Button startButton = new Button("Start");
        Button exitButton = new Button("Exit");
        startButton.setFont(Font.font(String.valueOf(startButton.getFont()), FontWeight.BOLD,40));
        exitButton.setFont(Font.font(String.valueOf(exitButton.getFont()), FontWeight.BOLD,40));
        startButton.setMinWidth(220);
        exitButton.setMinWidth(220);
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> gui.startGame());
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> gui.getStage().close());

        VBox vbox = new VBox(startButton,exitButton);
        vbox.setSpacing(20);

        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(menuBackground);
        root.getChildren().add(vbox);
        this.setRoot(root);
    }
}
