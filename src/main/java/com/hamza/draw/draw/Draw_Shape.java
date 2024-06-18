package com.hamza.draw.draw;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;

import java.util.Random;

public class Draw_Shape {

    public void drawArrowLine(double startX, double startY, double endX, double endY, Pane pane) {
        // get the slope of the line and find its angle
        double slope = (startY - endY) / (startX - endX);
        double lineAngle = Math.atan(slope);

        double arrowAngle = startX > endX ? Math.toRadians(45) : -Math.toRadians(225);

        Line line = new Line(startX, startY, endX, endY);

        double lineLength = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        double arrowLength = lineLength / 10;

        // create the arrow legs
        Line arrow1 = new Line();
        arrow1.setStartX(line.getEndX());
        arrow1.setStartY(line.getEndY());
        arrow1.setEndX(line.getEndX() + arrowLength * Math.cos(lineAngle - arrowAngle));
        arrow1.setEndY(line.getEndY() + arrowLength * Math.sin(lineAngle - arrowAngle));

        Line arrow2 = new Line();
        arrow2.setStartX(line.getEndX());
        arrow2.setStartY(line.getEndY());
        arrow2.setEndX(line.getEndX() + arrowLength * Math.cos(lineAngle + arrowAngle));
        arrow2.setEndY(line.getEndY() + arrowLength * Math.sin(lineAngle + arrowAngle));

        pane.getChildren().addAll(line, arrow1, arrow2);
    }

    public void makeCloud(int x, int y, int size, Pane group) {
        int halfSize = size / 2;
        int xMinusSize = x - size;
        int xPlusSize = x + size;
        int yPlusSize = y + size;
        int yMinusSize = y - size;
        int xMinusHalfSize = x - halfSize;
        double angle = 15.1;

        // ellipse
        Ellipse ellipse1 = new Ellipse(x, y, size, size);
        Ellipse ellipse2 = new Ellipse(x + size * .70, y - size * .2, size * 1.2, size * 1.2);
        Ellipse ellipse3 = new Ellipse(x + size * 2, y + size * .15, size * .9, size * .9);
        Ellipse ellipse4 = new Ellipse(x + size * 1.8, y - size * .6, size * .3, size * .3);

        Color rgb = Color.rgb(21, 144, 197);
        Color color = Color.color(.3, .8, .2, .2);
        ellipse1.setFill(color);
        ellipse2.setFill(Color.color(.5, .3, .2, .3));
        ellipse1.setFill(rgb);
        ellipse2.setFill(rgb);
        ellipse3.setFill(rgb);
        ellipse4.setFill(rgb);

        group.getChildren().add(ellipse1);
        group.getChildren().add(ellipse2);
        group.getChildren().add(ellipse3);
        group.getChildren().add(ellipse4);

        // lines
        Line line1 = line(xMinusSize, y, yPlusSize, y, Color.RED, false);
        Line line2 = line(x, xMinusSize, y, yPlusSize, Color.YELLOW, false);

        double vAngle = Math.toRadians(angle) * halfSize;
        Line line3 = line(xMinusHalfSize, yMinusSize + vAngle, xMinusHalfSize, yPlusSize - vAngle, Color.RED, true);
        Line line4 = line(xMinusHalfSize, yMinusSize + vAngle, x, xMinusSize, Color.BURLYWOOD, true);

//        group.getChildren().add(line1);
//        group.getChildren().add(line2);
//        group.getChildren().add(line3);
//        group.getChildren().add(line4);


        // print angel
        Point2D point2D = new Point2D(xMinusHalfSize, yMinusSize + vAngle);
        Point2D point2D2 = new Point2D(xMinusHalfSize, yPlusSize - vAngle);
        System.out.println(computeAngleOfVector(point2D, point2D2, line4));
    }

    private Line line(double startX, double startY, double endX, double endY, Color color, boolean addStroke) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(color);
        if (addStroke) {
            line.setStrokeWidth(2);
            line.getStrokeDashArray().add(5.0);
        }
        return line;
    }

    public void makeScetsh(double width, double height, Pane pane) {
        DoubleProperty propertyHeight = new SimpleDoubleProperty(height);
        DoubleProperty propertyWidth = new SimpleDoubleProperty(width);
        DoubleBinding doubleBinding = new DoubleBinding() {
            {
                super.bind(propertyHeight);
            }

            @Override
            protected double computeValue() {
                return propertyHeight.get() / 2;
            }
        };

        Line line = new Line(0, doubleBinding.get(), width, doubleBinding.get());
        line.startYProperty().bind(doubleBinding);
        line.endYProperty().bind(doubleBinding);
        line.endXProperty().bind(propertyWidth);
//        line.setStrokeLineJoin(StrokeLineJoin.MITER);
//        line.setStrokeWidth(2);
//        line.getStrokeDashArray().add(5.0);

        pane.heightProperty().addListener((observableValue, number, t1) -> propertyHeight.setValue(t1));
        pane.widthProperty().addListener((observableValue, number, t1) -> propertyWidth.setValue(t1));


        for (int i = 0; i < height; i++) {

            if (i % 5 == 0) {
                Line line1 = new Line(0, i, width, i);
                line1.setStroke(Color.valueOf("#eee"));
//                line1.setStrokeWidth(.5);
//                line1.getStrokeDashArray().add(5.0);
                pane.getChildren().add(line1);
            }
        }

        pane.getChildren().add(line);
    }

    public void makeRect(Group group) {
        int x, y, width, height;
        Random r = new Random();
        for (int i = 1; i < 100; i++) {
            x = r.nextInt(500);
            y = r.nextInt(500);
            width = r.nextInt(50);
            height = r.nextInt(50);

            Rectangle e = new Rectangle(x, y, width, height);
//            e.setFill(Color.WHITE);
            e.setStrokeWidth(2);
            e.setStroke(Color.WHITE);

            if (width <= 10) {
                e.setStroke(Color.GREEN);
                e.setFill(Color.RED);
            } else if (height <= 10) {
                e.setStroke(Color.RED);
                e.setFill(Color.PAPAYAWHIP);
            }
            group.getChildren().add(e);
        }
    }

    private DoubleBinding createAngleBinding(Line line) {
        return Bindings.createDoubleBinding(
                () -> {
                    Point2D vector =
                            new Point2D(line.getEndX() - line.getStartX(), line.getEndY() - line.getStartY());
                    double angle = vector.angle(1, 0);
                    if (vector.getY() > 0) {
                        return 360 - angle;
                    }
                    return angle;
                },
                line.startXProperty(),
                line.endXProperty(),
                line.startYProperty(),
                line.endYProperty());
    }

    public static double computeAngleOfVector(Point2D p1, Point2D p2, Line line) {
        Point2D vector =
                new Point2D(line.getEndX() - line.getStartX(), line.getEndY() - line.getStartY());
//        Point2D vector = new Point2D(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        double angle = vector.angle(1.0, 0.0);
        if (vector.getY() > 0) {
            // vector pointing downwards and thus is in the 3rd or 4th quadrant
            return 360.0 - angle;
        }
        // vector pointing upwards and thus is in the 1st or 2nd quadrant
        return angle;
    }

}
