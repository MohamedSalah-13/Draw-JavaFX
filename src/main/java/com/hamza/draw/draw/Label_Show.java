package com.hamza.draw.draw;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class Label_Show {

    private final Random random = new Random();

    public Label label(String text, double w, double h) {
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setText(text);
        label.setPrefSize(w, h);
        label.setStyle("-fx-background-color:white ;-fx-font-size:12px ;-fx-font-weight: bold");
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        return label;
    }

    public Label draw_label(double x, double y, double w, double h) {
        Label label = new Label();
        label.setTranslateX(x);
        label.setTranslateY(y);
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        label.setStyle("-fx-background-color: rgb(" + r + ", " + g + ", " + b + ")");
        label.setPrefSize(w, h);
        return label;
    }
}
