package com.hamza.draw.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Quilt extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Paint squareColor = Color.AQUAMARINE ;

        for (int x = 0 ; x <= 500 ; x+=100) {
            for (int y = 0 ; y <= 500 ; y+=100) {
                Rectangle square = new Rectangle(x, y, 50, 50);
                square.setFill(squareColor);
                root.getChildren().add(square);
            }
        }

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
