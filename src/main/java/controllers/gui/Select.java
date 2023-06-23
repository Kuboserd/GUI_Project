package controllers.gui;

import controllers.Game;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Item;
import model.Player;

public class Select extends Scene {

    private TableView stats;
    private TableView items;

    public Select(Parent parent, Game game) {
        super(parent, 1600, 900);
        this.init(game);
    }

    private void init(Game game) {

        StackPane root = new StackPane();
        BorderPane borderPane = new BorderPane();
        root.setMaxHeight(320);
        root.setMaxWidth(640);
        Image background = new Image("crossroads.jpg", 1600, 900, false, false);
        ImageView menuBackground = new ImageView(background);

        Button fightButton = new Button("Venture forth");
        Button shopButton = new Button("Go to Shop");
        Button exitButton = new Button("Exit");
        fightButton.setFont(Font.font(String.valueOf(fightButton.getFont()), FontWeight.BOLD, 40));
        shopButton.setFont(Font.font(String.valueOf(shopButton.getFont()), FontWeight.BOLD, 40));
        exitButton.setFont(Font.font(String.valueOf(exitButton.getFont()), FontWeight.BOLD, 40));
        fightButton.setMinWidth(220);
        shopButton.setMinWidth(220);
        exitButton.setMinWidth(220);
        fightButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.startFight());
        shopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.goToShop(this));
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.getGui().getStage().setScene(new Menu(game.getGui().getRoot(), game.getGui())));

        stats = new TableView(FXCollections.observableArrayList(game.getPlayer()));
        stats.setMaxHeight(50);
        stats.setMaxWidth(300);
        TableColumn<Player, Integer> health = new TableColumn<>("HP");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        TableColumn<Player, Integer> attack = new TableColumn<>("ATK");
        attack.setCellValueFactory(new PropertyValueFactory<>("attack"));
        TableColumn<Player, Integer> defense = new TableColumn<>("DEF");
        defense.setCellValueFactory(new PropertyValueFactory<>("defense"));
        TableColumn<Player, Integer> agility = new TableColumn<>("AG");
        agility.setCellValueFactory(new PropertyValueFactory<>("agility"));
        TableColumn<Player, Integer> gold = new TableColumn<>("GP");
        gold.setCellValueFactory(new PropertyValueFactory<>("wallet"));

        stats.getColumns().addAll(health, attack, defense, agility, gold);

        items = new TableView();
        items.setEditable(false);
        items.setMaxHeight(60 * game.getPlayer().getEquipment().size());
        items.setMaxWidth(300);
        TableColumn<Item, String> name = new TableColumn<>("Item");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> type = new TableColumn<>("type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Item, Boolean> poison = new TableColumn<>("Poison");
        poison.setCellValueFactory(new PropertyValueFactory<>("poisoned"));

        items.getColumns().addAll(name, type, poison);
        for (Item[] value : game.getPlayer().getEquipment().values()) {
            for (Item item : value) {
                items.getItems().add(item);
            }
        }

        VBox vbox = new VBox(fightButton, shopButton, exitButton);
        vbox.setSpacing(20);

        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(menuBackground);
        root.getChildren().add(borderPane);
        borderPane.setCenter(vbox);
        borderPane.setRight(stats);
        borderPane.setLeft(items);
        this.setRoot(root);

    }

    public TableView getStats() {
        return stats;
    }

    public TableView getItems() {
        return items;
    }
}
