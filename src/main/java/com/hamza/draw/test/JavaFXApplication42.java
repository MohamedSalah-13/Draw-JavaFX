package com.hamza.draw.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Sedrick
 */
public class JavaFXApplication42 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        for(int i=0; i<=30;i++)
        {
            float x = (float)(Math.random()*513);
            float y = (float)(Math.random()*513);

            Rectangle r = new Rectangle(x,y,40,40);
            root.getChildren().add(r);//Add each rectangle to the Group.
        }

        Scene scene = new Scene(root, 512, 512, Color.WHITE);
        primaryStage.setTitle("Assignment 5, a QR Code");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
