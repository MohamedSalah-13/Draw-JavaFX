package com.hamza.draw.draw;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StackPane_draw extends StackPane {

    public StackPane_draw(double x, double y, double width, double height) {
        setAlignment(Pos.TOP_LEFT);
        setPrefSize(width, height);
        setTranslateX(x);
        setTranslateY(y);
        setId(width + " * " + height);
        setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        Text text_width = new Text_Size(0, width, 0, "");
        Text text_height = new Text_Size(0, height, 270, "height");

        Rectangle square = new Rectangle(width, height);
        square.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        square.setFill(Color.rgb(31, 115, 171));

        getChildren().addAll(square, text_width, text_height);
    }

    public StackPane_draw(double width, double height) {
        setAlignment(Pos.TOP_LEFT);
        setPrefSize(width, height);
        setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        Text text_width = new Text_Size(0, width, 0, "");
        Text text_height = new Text_Size(0, height, 270, "height");

        Rectangle square = new Rectangle(width, height);
        square.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        square.setFill(Color.rgb(31, 115, 171));

        getChildren().addAll(square, text_width, text_height);
    }
}
