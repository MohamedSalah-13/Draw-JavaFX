package com.hamza.draw.test;


import com.hamza.draw.draw.Draw_Shape;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainTest extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane group = new Pane();
        Draw_Shape drawShape = new Draw_Shape();
//        drawShape.makeCloud(100, 100, 80, group);
        drawShape.makeCloud(250, 250, 100, group);
        int width = 600;
        int height = 600;
        Scene scene = new Scene(group, width, height);
        drawShape.makeScetsh(width, height,group);
        primaryStage.setTitle("Draw");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);

        final DoubleProperty radius = new SimpleDoubleProperty(2);

        DoubleBinding volumeOfSphere = new DoubleBinding() {
            {//from  w  ww.  ja v  a  2s  .  com
                super.bind(radius);
            }

            @Override
            protected double computeValue() {
                return ((double) 4 / 3 * Math.PI * Math.pow(radius.get(), 3));
            }
        };

        System.out.println("Current - radius for Sphere: " + radius.get());
        System.out.println("Current - volume for Sphere: " + volumeOfSphere.get());
        System.out.println("Modifying DoubleProperty radius");

        radius.set(50);
        System.out.println("After - radius for Sphere: " + radius.get());
        System.out.println("After - volume for Sphere: " + volumeOfSphere.get());
    }

}