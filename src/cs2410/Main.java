package cs2410;

import cs2410.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/Main.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        primaryStage.setTitle("MineSweeper(ish)");
        primaryStage.setScene(new Scene(page, 600, 500));
        primaryStage.setResizable(false);

        Controller controller = loader.getController();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
