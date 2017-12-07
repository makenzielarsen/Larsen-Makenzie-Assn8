package cs2410.controller;

import cs2410.model.Cell;
import cs2410.model.MineField;
import cs2410.view.View;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Controller {
    View view = new View();
    private MineField mineField;
    private GridPane grid;

    @FXML
    Label bombsLeft = new Label();
    @FXML
    Label timeElapsed = new Label();


    @FXML
    private ChoiceBox difficultyLevelBox = new ChoiceBox();
    @FXML
    private ChoiceBox sizeBox = new ChoiceBox();
    @FXML
    private BorderPane gridPane = new BorderPane();

    @FXML
    public void initialize() {
        difficultyLevelBox.getItems().addAll("Easy", "Medium", "Hard");
        sizeBox.getItems().addAll("Small", "Medium", "Large");
        setBombsLeft("0");
        setTimeElapsed("0");
    }

    private void setBombsLeft(String bombs) {
        bombsLeft.setText("Bombs Left \n " + bombs);
    }

    private void setTimeElapsed(String time) {
        timeElapsed.setText("Time \n " + time);
    }

    @FXML
    private void pressedStart() {
        int row = 0;
        int column = 0;
        double bombPercentage = 0;

        if(difficultyLevelBox.getValue() == "Easy") {
            bombPercentage = 0.1;
        } else if(difficultyLevelBox.getValue() == "Medium") {
            bombPercentage = 0.25;
        } else if(difficultyLevelBox.getValue() == "Easy") {
            bombPercentage = 0.4;
        }

        if(sizeBox.getValue() == "Small") {
            row = 10;
            column = 10;
            mineField = new MineField(column, row, bombPercentage);
        } else if(sizeBox.getValue() == "Medium") {
            row = 25;
            column = 25;
            mineField = new MineField(column, row, bombPercentage);
        } else if(sizeBox.getValue() == "Large") {
            row = 50;
            column = 25;
            mineField = new MineField(column, row, bombPercentage);
        }

        grid = new GridPane();

        for(int i = 0; i < row; i++) {
            grid.getRowConstraints().add(new RowConstraints(20));
            for(int j = 0; j < column; j++) {
                if(j == 0) {
                    grid.getColumnConstraints().add(new ColumnConstraints(20));
                }
                grid.add(mineField.getCell(i, j), i, j);
            }
        }

        setBombsLeft(String.valueOf(mineField.numberBombsLeft()));
        setTimeElapsed("0");

        long startTime = System.currentTimeMillis();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedMillis = System.currentTimeMillis() - startTime ;
                setTimeElapsed(Long.toString(elapsedMillis / 1000));
            }
        }.start();

        grid.setHgap(0);
        grid.setVgap(7);
        grid.setAlignment(Pos.CENTER);
        gridPane.setCenter(grid);
    }

    public void clickedCell() {
        //mark as clicked
        //update all cells
        //check won
        //check lost
        
    }

    public void clickedReset() {

    }
}
