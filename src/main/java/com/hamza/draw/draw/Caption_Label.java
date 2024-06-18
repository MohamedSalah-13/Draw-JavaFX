package com.hamza.draw.draw;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Caption_Label {

    public Caption_Label(SplitPane splitPane, StackPane stackPane) {

        VBox node = (VBox) splitPane.getItems().get(0);
        Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 16 Tahoma;");
        stackPane.getChildren().add(caption);

        splitPane.getItems().forEach(node1 -> {
            node1.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> {
                caption.translateXProperty().set(e.getScreenX() - 10);
                caption.translateYProperty().set(e.getScreenY() - 10);

                String msg = "(x: " + e.getX() + ", y: " + e.getY() + ") -- " +
                        "(sceneX: " + e.getSceneX() + ", sceneY: " + e.getSceneY() + ") -- " +
                        "(screenX: " + e.getScreenX() + ", screenY: " + e.getScreenY() + ")";
                caption.setText(String.valueOf(node.getWidth()));

            });
        });

        splitPane.getDividers().forEach(divider -> {
            divider.positionProperty().addListener((observable, oldValue, newValue) -> caption.setText(String.valueOf(node.getWidth())));
        });
    }


    public Caption_Label(StackPane stackPane, Pane pane) {

        Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 16 Tahoma;");
        stackPane.getChildren().add(caption);

        System.out.println(pane.getScaleY() + " , " + pane.getPrefWidth());

        pane.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent e) -> {
            caption.translateXProperty().set(e.getScreenX() + 10);
            caption.translateYProperty().set(e.getScreenY() + 10);

            String msg = "(x: " + e.getX() + ", y: " + e.getY() + ") -- " +
                    "(sceneX: " + e.getSceneX() + ", sceneY: " + e.getSceneY() + ") -- " +
                    "(screenX: " + e.getScreenX() + ", screenY: " + e.getScreenY() + ")";
            caption.setText(msg);

        });
       /* caption.translateXProperty().set(pane.getTranslateX() + 50);
        caption.translateYProperty().set(pane.getTranslateY() - 10);

        caption.setText(String.valueOf("gfgfhfghf"));*/


    }
}
