package com.hamza.draw.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MouseLocationReporter extends Application {
    private static final String OUTSIDE_TEXT = "Outside Label";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        final Label reporter = new Label(OUTSIDE_TEXT);
        Label monitored = createMonitoredLabel(reporter);

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10px;");
        layout.getChildren().setAll(
                monitored,
                reporter
        );
        layout.setPrefWidth(500);

        stage.setScene(new Scene(layout));
        stage.show();
    }

    private Label createMonitoredLabel(final Label reporter) {
        final Label monitored = new Label("Mouse Location Monitor");

        monitored.setStyle("-fx-background-color: forestgreen; -fx-text-fill: white; -fx-font-size: 20px;");

        monitored.setOnMouseMoved(event -> {
            String msg =
                    "(x: " + event.getX() + ", y: " + event.getY() + ") -- " +
                            "(sceneX: " + event.getSceneX() + ", sceneY: " + event.getSceneY() + ") -- " +
                            "(screenX: " + event.getScreenX() + ", screenY: " + event.getScreenY() + ")";

            reporter.setText(msg);
        });

        monitored.setOnMouseExited(event -> reporter.setText(OUTSIDE_TEXT));

        return monitored;
    }
}
