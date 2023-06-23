package controllers.gui;

import controllers.Game;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;

public class Battle extends Scene {

    TableView stats;
    TableView items;
    TableView enemyStats;
    TableView itemsEnemy;

    GraphicsContext gc;


    public Battle(Parent parent, Game game) {
        super(parent, 1600, 900);
        this.init(game);
    }

    public void init(Game game) {

        stats = this.statsTable(game.getPlayer());
        items = this.itemTable(game.getPlayer());
        enemyStats = this.statsTable(game.getEnemy());
        itemsEnemy = this.itemTable(game.getEnemy());

        StackPane root = new StackPane();
        BorderPane borderPane = new BorderPane();
        Canvas canvas = new Canvas(1600,900);
        root.setMaxHeight(320);
        root.setMaxWidth(640);
        Image background = new Image("battleground.jpg", 1600, 900, false, false);
        ImageView menuBackground = new ImageView(background);

        Button fightButton = new Button("Attack!");
        Button runButton = new Button("Run!");
        Button exitButton = new Button("Surrender");
        fightButton.setFont(Font.font(String.valueOf(fightButton.getFont()), FontWeight.BOLD, 40));
        runButton.setFont(Font.font(String.valueOf(runButton.getFont()), FontWeight.BOLD, 40));
        exitButton.setFont(Font.font(String.valueOf(exitButton.getFont()), FontWeight.BOLD, 40));
        fightButton.setMinWidth(220);
        runButton.setMinWidth(220);
        exitButton.setMinWidth(220);
        fightButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            game.getFight().turn(gc);
            this.stats.refresh();
            this.enemyStats.refresh();
        });
        runButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            game.getFight().runAway(gc);
            this.stats.refresh();
            this.enemyStats.refresh();
        });
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> game.getGui().startOver(new Stage()));


        for (Item[] value : game.getPlayer().getEquipment().values()) {
            for (Item item : value) {
                items.getItems().add(item);
            }
        }
        addButtonToTable(items, game);
        for (Item[] value : game.getEnemy().getEquipment().values()) {
            for (Item item : value) {
                itemsEnemy.getItems().add(item);
            }
        }

        HBox hBox = new HBox(fightButton, runButton, exitButton);
        hBox.setSpacing(20);
        VBox playerSide = new VBox(stats, items);
        VBox enemySide = new VBox(enemyStats, itemsEnemy);

        this.gc =canvas.getGraphicsContext2D();
        gc.drawImage(new Image("swordman.png", 128,128,true,true), 300, 300);
        gc.drawImage(new Image("mage.png", 128,128,true,true), 900, 300);


        root.getChildren().add(menuBackground);
        root.getChildren().add(canvas);
        root.getChildren().add(borderPane);
        borderPane.setLeft(playerSide);
        borderPane.setRight(enemySide);
        borderPane.setBottom(hBox);

        this.setRoot(root);

    }

    private TableView statsTable(Statistics person) {
        TableView result = new TableView(FXCollections.observableArrayList(person));

        result.setMaxHeight(50);
        result.setMaxWidth(250);
        TableColumn<Statistics, Integer> health = new TableColumn<>("HP");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        TableColumn<Statistics, Integer> attack = new TableColumn<>("ATK");
        attack.setCellValueFactory(new PropertyValueFactory<>("attack"));
        TableColumn<Statistics, Integer> defense = new TableColumn<>("DEF");
        defense.setCellValueFactory(new PropertyValueFactory<>("defense"));
        TableColumn<Statistics, Integer> agility = new TableColumn<>("AG");
        agility.setCellValueFactory(new PropertyValueFactory<>("agility"));
        if (person instanceof Player) {
            TableColumn<Player, Integer> gold = new TableColumn<>("GP");
            gold.setCellValueFactory(new PropertyValueFactory<>("wallet"));
            result.getColumns().addAll(health, attack, defense, agility, gold);
        } else if (person instanceof Enemy) {
            TableColumn<Enemy, Integer> award = new TableColumn<>("Award");
            award.setCellValueFactory(new PropertyValueFactory<>("award"));
            result.getColumns().addAll(health, attack, defense, agility, award);
        }

        return result;
    }

    private TableView itemTable(Statistics person) {
        TableView result = new TableView();

        result.setEditable(false);
        result.setMaxHeight(60 * person.getEquipment().size());
        result.setMaxWidth(250);
        TableColumn<Item, String> name = new TableColumn<>("Item");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> type = new TableColumn<>("type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Item, Boolean> poison = new TableColumn<>("Poison");
        poison.setCellValueFactory(new PropertyValueFactory<>("poisoned"));
        result.getColumns().addAll(name, type, poison);

        return result;

    }

    private void addButtonToTable(TableView table, Game game) {
        TableColumn<Item, Void> colBtn = new TableColumn("Use?");

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btn = new Button("Use");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            game.getPlayer().addStats(getTableView().getItems().get(getIndex()), true);
                            table.getItems().remove(getTableView().getItems().get(getIndex()));
                            getTableView().refresh();
                            items.refresh();
                            stats.refresh();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else if (getIndex() >= 0 && getIndex() <= 2) {
                            if (getTableView().getItems().get(getIndex()).getType() != ITEM_TYPE.CONSUMABLE) {
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
    public void repaint(){

    }
}
