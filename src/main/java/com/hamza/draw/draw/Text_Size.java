package com.hamza.draw.draw;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Text_Size extends Text {

    public Text_Size(int xy, double size, int rotate, String h) {
        setText(size + " سم ");
        setRotate(rotate);

        if (size > 100)
            setFont(Font.font(12));
        else if (size < 60)
            setFont(Font.font(size / 6));

        if (h.equals("height")) {
            setTranslateX(xy);
            setTranslateY(size / 2);
        } else {
            setTranslateX(size / 4);
            setTranslateY(xy);
        }
    }
}
