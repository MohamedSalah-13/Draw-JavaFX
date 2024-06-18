package com.hamza.draw.test;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import org.hamza.extension.resize.Screen_Size;

public class Draw_Box extends Application {

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private DoubleProperty angleX = new SimpleDoubleProperty(0);
    private DoubleProperty angleY = new SimpleDoubleProperty(0);
    private Label label;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_LEFT);
        SmartObject group = new SmartObject();
        label = new Label("");
        label.setTextFill(Color.RED);
        stackPane.getChildren().addAll(group, label);
        Scene scene = new Scene(stackPane);
        scene.setFill(Color.SILVER);
        scene.setCamera(new PerspectiveCamera());
        Box box = new Box(100, 20, 50);
        group.getChildren().add(box);
        new Screen_Size(stage);

        group.translateXProperty().set(stage.getWidth() / 2);
        group.translateYProperty().set(stage.getHeight() / 2);
        group.translateZProperty().set(-1000);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> group.translateZProperty().set(group.getTranslateZ() + 100);
                case DOWN -> group.translateZProperty().set(group.getTranslateZ() - 100);

                case NUMPAD8 -> group.rotate(10, Rotate.X_AXIS);
                case NUMPAD2 -> group.rotate(-10, Rotate.X_AXIS);
                case NUMPAD6 -> group.rotate(10, Rotate.Y_AXIS);
                case NUMPAD4 -> group.rotate(-10, Rotate.Y_AXIS);
            }
        });

        initMouseControl(group, scene);
        stage.setTitle("Draw_Box");
        stage.setScene(scene);
        stage.show();
    }

    private void initMouseControl(SmartObject group, Scene scene) {
        Rotate rotateX;
        Rotate rotateY;
        group.getTransforms().addAll(
                rotateX = new Rotate(0, Rotate.X_AXIS),
                rotateY = new Rotate(0, Rotate.Y_AXIS));

        rotateX.angleProperty().bind(angleX);
        rotateY.angleProperty().bind(angleY);

        scene.setOnMousePressed(mouseEvent -> {
            anchorX = mouseEvent.getSceneX();
            anchorY = mouseEvent.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseMoved(mouseEvent -> {
            label.translateXProperty().set(mouseEvent.getScreenX() - 50);
            label.translateYProperty().set(mouseEvent.getScreenY() - 50);
            label.setText(mouseEvent.getScreenX() + "*" + mouseEvent.getScreenY());
        });

        scene.setOnMouseDragged(mouseEvent -> {
            angleX.set(anchorAngleX - (anchorY + mouseEvent.getSceneY()));
            angleY.set(anchorAngleY + anchorX - mouseEvent.getSceneX());
        });
    }

    static class SmartObject extends Group {
        Rotate rotate;
        Transform transform = new Rotate();

        void rotate(int ang, Point3D point3D) {
            rotate = new Rotate(ang, point3D);
            transform = transform.createConcatenation(rotate);
            this.getTransforms().clear();
            this.getTransforms().add(transform);
        }

    }
}
