package controllers.gui;

import controllers.Game;
import controllers.Shop;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import model.ITEM_TYPE;
import model.Item;

import java.util.Arrays;

public class ShopGUI extends Scene {
    private Game game;

    public ShopGUI(Parent parent, Game game, Select earlierScene) {
        super(parent, 1600, 900);
        this.game = game;
        this.init(game.getShop(),earlierScene);
    }

    private void init(Shop shop, Select earlierScene) {
        StackPane root = new StackPane();
        BorderPane borderPane = new BorderPane();
        root.setMaxHeight(320);
        root.setMaxWidth(640);
        Image background = new Image("shop.jpg", 1600, 900, true, true);
        ImageView menuBackground = new ImageView(background);

        TableView tableView = new TableView(shop.getStock());
        tableView.snappedBottomInset();
        tableView.setMaxHeight(120);
        tableView.setMaxWidth(700);

        TableColumn<Item, String> name = new TableColumn<>("Item");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, Integer> health = new TableColumn<>("Health");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        TableColumn<Item, Integer> attack = new TableColumn<>("Attack");
        attack.setCellValueFactory(new PropertyValueFactory<>("attack"));
        TableColumn<Item, Integer> defense = new TableColumn<>("Defense");
        defense.setCellValueFactory(new PropertyValueFactory<>("defense"));
        TableColumn<Item, Integer> agility = new TableColumn<>("Agility");
        agility.setCellValueFactory(new PropertyValueFactory<>("agility"));
        TableColumn<Item, ITEM_TYPE> type = new TableColumn<>("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Item, Integer> cost = new TableColumn<>("Cost");
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        TableColumn<Item, String> damage = new TableColumn<>("Damage");
        damage.setCellValueFactory(new PropertyValueFactory<>("attackDamage"));
        TableColumn<Item, Boolean> poison = new TableColumn<>("Poison");
        poison.setCellValueFactory(new PropertyValueFactory<>("poisoned"));

        tableView.getColumns().addAll(name, health, attack, defense, agility, type, cost, damage,poison);
        addButtonToTable(tableView,earlierScene);


        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font(String.valueOf(exitButton.getFont()), FontWeight.BOLD, 40));
        exitButton.setMinWidth(220);
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.getGui().getStage().setScene(new Select(game.getGui().getRoot(), game)));

        HBox hbox = new HBox(exitButton);
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER);

        root.getChildren().add(menuBackground);
        root.getChildren().add(borderPane);
        borderPane.setCenter(tableView);
        borderPane.setTop(hbox);


        this.setRoot(root);

    }

    private void addButtonToTable(TableView table, Select earlierScene) {
        TableColumn<Item, Void> colBtn = new TableColumn("Buy");

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btn = new Button("Buy");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            game.getShop().buy(getTableView().getItems().get(getIndex()), game.getPlayer());
                            Item selectedItem = getTableView().getItems().get(getIndex());
                            table.getItems().remove(selectedItem);
                            getTableView().refresh();
                            earlierScene.getStats().refresh();
                            earlierScene.getItems().refresh();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else if (getIndex() >= 0 && getIndex() <= 2) {
                            if (game.getPlayer().getWallet() < getTableView().getItems().get(getIndex()).getCost()) {
                                setGraphic(null);
                            } else {
                                setGraphic(btn);
                            }
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
    }
}
