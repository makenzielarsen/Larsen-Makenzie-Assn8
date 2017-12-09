package cs2410.controller;

import com.sun.rowset.internal.Row;
import cs2410.view.Cell;
import cs2410.view.View;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Controller {
    View view = new View();
    private MineField mineField;
    private GridPane grid;
    int row = 0;
    int column = 0;
    double bombPercentage = 0;
    AnimationTimer animationTimer;
    long startTime;
    String endTime;
    boolean firstClick = true;
    private AudioClip buttonClick = new AudioClip(new File("images/normalButtonClip.mp3").toURI().toString());
    private AudioClip rightClickBeep = new AudioClip(new File("images/beep.mp3").toURI().toString());
    private AudioClip bombClip = new AudioClip(new File("images/bomb.mp3").toURI().toString());
    private AudioClip youWonClip = new AudioClip(new File("images/youWon.mp3").toURI().toString());
    private AudioClip youLoseClip = new AudioClip(new File("images/youLose.mp3").toURI().toString());


    @FXML
    Button startButton;
    @FXML
    ToggleButton muteButton;
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
    private void muteSounds() {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                Cell buttonCell = mineField.getCell(i, j);
                if(!muteButton.isSelected()) {
                    muteButton.setText("Mute");
                    buttonCell.setMuted(true);
                } else {
                    muteButton.setText("unMute");
                    buttonCell.setMuted(false);
                }

            }
        }
    }

    @FXML
    public void initialize() {
        difficultyLevelBox.getItems().addAll("Easy", "Medium", "Hard");
        sizeBox.getItems().addAll("Small", "Medium", "Large");
        setBombsLeft("0");
        setTimeElapsed("0");

        Alert implementationInformation = new Alert(Alert.AlertType.INFORMATION);
        implementationInformation.setTitle("Information for the TA");
        implementationInformation.setHeaderText(null);
        implementationInformation.setContentText("I chose to implement: \n (10 points) The size feature \n (10 points) The Difficulty Feature \n (15 points) Sounds");
        implementationInformation.showAndWait();
    }

    private void setBombsLeft(String bombs) {
        bombsLeft.setText("Bombs Left \n " + bombs);
    }

    private void setTimeElapsed(String time) {
        timeElapsed.setText("Time \n " + time);
    }

    @FXML
    private void pressedStart() {
        if(difficultyLevelBox.getValue() == "") {
            return;
        }
        if(sizeBox.getValue() == "") {
            return;
        }

        startButton.setText("Restart");

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

        sizeBox.getSelectionModel().selectedIndexProperty().addListener(e -> sizeChanged());
        difficultyLevelBox.getSelectionModel().selectedIndexProperty().addListener(e -> difficultyLevelChanged());

        grid = new GridPane();

//        if(sizeBox.getValue() == "Small") {
//            ColumnConstraints columnConstraints = new ColumnConstraints();
//            columnConstraints.setFillWidth(true);
//            for(int i = 0; i < column; i++) {
//                grid.getColumnConstraints().add(columnConstraints);
//            }
//
//            RowConstraints rowConstraints = new RowConstraints();
//            rowConstraints.setFillHeight(true);
//            for(int i = 0; i < row; i++) {
//                grid.getRowConstraints().add(rowConstraints);
//            }
//       } // else if(sizeBox.getValue() == "Medium") {
//            ColumnConstraints columnConstraints = new ColumnConstraints();
//            columnConstraints.setPercentWidth(0.4);
//            grid.getColumnConstraints().add(columnConstraints);
//            RowConstraints rowConstraints = new RowConstraints();
//            rowConstraints.setPercentHeight(0.4);
//            grid.getRowConstraints().add(rowConstraints);
//        } else if(sizeBox.getValue() == "Large") {
//            ColumnConstraints columnConstraints = new ColumnConstraints();
//            columnConstraints.setPercentWidth(0.5);
//            grid.getColumnConstraints().add(columnConstraints);
//            RowConstraints rowConstraints = new RowConstraints();
//            rowConstraints.setPercentHeight(0.4);
//            grid.getRowConstraints().add(rowConstraints);
//        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                Cell buttonCell = mineField.getCell(i, j);
                buttonCell.setOnMousePressed(e -> clickedCell(e));
                buttonCell.setMinSize(grid.getBoundsInParent().getWidth(), grid.getBoundsInParent().getHeight());
                grid.add(buttonCell, i, j);
            }
        }

        setBombsLeft(String.valueOf(mineField.numberBombsLeft()));
        setTimeElapsed("0");

        grid.setHgap(0);
        grid.setVgap(0);
        grid.setMinSize(gridPane.getBoundsInParent().getWidth(), gridPane.getBoundsInParent().getHeight());
        grid.setAlignment(Pos.CENTER);
        gridPane.setCenter(grid);
    }

    private void difficultyLevelChanged() {
        if(animationTimer == null) {
            startButton.setText("Start");
            firstClick = true;
            mineField.endGame();
            return;
        }
        animationTimer.stop();
        long elapsedMillis = System.currentTimeMillis() - startTime;
        endTime = Long.toString(elapsedMillis / 1000);
        firstClick = true;
        startButton.setText("Start");

        mineField.endGame();
    }

    private void sizeChanged() {
        if(animationTimer == null) {
            startButton.setText("Start");
            firstClick = true;
            mineField.endGame();
            return;
        }
        animationTimer.stop();
        long elapsedMillis = System.currentTimeMillis() - startTime;
        endTime = Long.toString(elapsedMillis / 1000);
        firstClick = true;
        startButton.setText("Start");

        mineField.endGame();
    }

    public void clickedCell(MouseEvent actionEvent) {
        if(firstClick) {
            startTime = System.currentTimeMillis();

            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    long elapsedMillis;
                    elapsedMillis = System.currentTimeMillis() - startTime;
                    setTimeElapsed(Long.toString(elapsedMillis / 1000));
                }
            };

            animationTimer.start();
            firstClick = false;
        }

        Cell buttonCell = (Cell)actionEvent.getSource();
        int x = buttonCell.getX();
        int y = buttonCell.getY();

        if(actionEvent.isSecondaryButtonDown()) {
            if (!muteButton.isSelected()) {
                rightClickBeep.play();
            }
            if(buttonCell.isFlagged()) {
                buttonCell.setFlagged(false);
                buttonCell.setQuestionable(true);
                buttonCell.updateCell(mineField.hasLost());
            } else if(buttonCell.isQuestionable()) {
                buttonCell.setQuestionable(false);
                buttonCell.clearGraphic();
                buttonCell.updateCell(mineField.hasLost());
            } else if(mineField.numberBombsLeft() > 0) {
                buttonCell.setFlagged(true);
                buttonCell.updateCell(mineField.hasLost());
            }
            buttonCell.setSelected(false);
        } else {
            if(!buttonCell.isFlagged() && !buttonCell.isQuestionable()) {
                mineField.click(x, y);
                if(!muteButton.isSelected()) {
                    buttonClick.play();
                }
            }

            mineField.refreshCells();

            if(mineField.hasLost()) {
                if(!muteButton.isSelected()) {
                    bombClip.play();
                }
                animationTimer.stop();
                long elapsedMillis = System.currentTimeMillis() - startTime;
                endTime = Long.toString(elapsedMillis / 1000);
                firstClick = true;
                startButton.setText("Start");

                if(!muteButton.isSelected()) {
                    youLoseClip.play();
                }
                Alert youLost = new Alert(Alert.AlertType.INFORMATION);
                youLost.setTitle("Game Over");
                youLost.setHeaderText("You Lost.");
                youLost.setContentText("Better luck next time!");
                youLost.showAndWait();
            } else if(mineField.hasWon()){
                animationTimer.stop();
                long elapsedMillis = System.currentTimeMillis() - startTime;
                endTime = Long.toString(elapsedMillis / 1000);
                firstClick = true;
                startButton.setText("Start");

                if(!muteButton.isSelected()) {
                    youWonClip.play();
                }

                Alert youWon = new Alert(Alert.AlertType.INFORMATION);
                youWon.setTitle("Game Over");
                youWon.setHeaderText("You Won!");
                youWon.setContentText("You won in " + endTime + " seconds.");
                youWon.showAndWait();
            }
        }
        setBombsLeft(String.valueOf(mineField.numberBombsLeft()));
    }

    public void clickedReset() {
        pressedStart();
    }
}
