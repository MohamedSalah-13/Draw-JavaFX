package com.hamza.draw.draw;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public record Rectangle_show(StackPane stack) {

    public void draw_Rectangle(double x, double y, double width, double height) {

        Text text_width = new Text_Size(0, width, 0, "");
        Text text_height = new Text_Size(0, height, 270, "height");

        Rectangle square = new Rectangle(width, height);
        square.setFill(Color.color(Math.random(), Math.random(), Math.random()));
//        square.setFill(Color.rgb(31, 115, 171));
//        square.setId(width + "*" + height);
        square.setTranslateX(x);
        square.setTranslateY(y);

        text_width.setTranslateX(x + (width / 2));
        text_width.setTranslateY(y + (height / 2));


        stack.getChildren().addAll(text_height, text_width, square);
    }

}
