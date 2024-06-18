package com.hamza.draw.draw;

import com.hamza.draw.image.Get_Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main_Screen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setMinHeight(580);
        primaryStage.setMinWidth(400);
        primaryStage.setTitle("Draw");
        primaryStage.getIcons().add(new Image(new Get_Image().IMAGE_MAIN));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
