package com.hamza.draw.test;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class TooltipOnSlider extends Application {

    @Override
    public void start(Stage primaryStage) {
        Slider slider = new Slider(5, 25, 15);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(5);

        Label label = new Label();
        Popup popup = new Popup();
        popup.getContent().add(label);

        double offset = 10 ;

        slider.setOnMouseMoved(e -> {
            NumberAxis axis = (NumberAxis) slider.lookup(".axis");
            Point2D locationInAxis = axis.sceneToLocal(e.getSceneX(), e.getSceneY());
            double mouseX = locationInAxis.getX() ;
            double value = axis.getValueForDisplay(mouseX).doubleValue() ;
            if (value >= slider.getMin() && value <= slider.getMax()) {
                label.setText(String.format("Value: %.1f", value));
            } else {
                label.setText("Value: ---");
            }
            popup.setAnchorX(e.getScreenX());
            popup.setAnchorY(e.getScreenY() + offset);
        });

        slider.setOnMouseEntered(e -> popup.show(slider, e.getScreenX(), e.getScreenY() + offset));
        slider.setOnMouseExited(e -> popup.hide());

        StackPane root = new StackPane(slider);
        primaryStage.setScene(new Scene(root, 350, 80));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
