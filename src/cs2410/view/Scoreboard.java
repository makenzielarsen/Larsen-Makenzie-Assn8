package cs2410.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Scoreboard {
        private Label bombsLabel = new Label("Bombs Left");
        private Label bombsLeft = new Label("0");
        private VBox bombsBox = new VBox(bombsLabel, bombsLeft);

        private Button startButton = new Button("Start");

        private Label timeLabel = new Label("Time");
        private Label time = new Label("0");
        private VBox timeBox = new VBox(timeLabel, time);

        HBox scoreBox = new HBox(bombsBox, startButton, timeBox);
}