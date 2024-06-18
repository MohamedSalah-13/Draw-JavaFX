package com.hamza.draw.test;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class ConvinienceMethodsExample extends Application {

    private static final double OUTER_RADIUS =300, INNER_RADIUS = 180, ORIGIN_X = 300, ORIGIN_Y = 300;
    private static final double START_ANGLE =20, END_ANGLE = 70, STEP_ANGEL = 10;

    @Override
    public void start(Stage primaryStage) {

        Group group = new Group();

        for(double angle =  START_ANGLE; angle < END_ANGLE ; angle += STEP_ANGEL ){

            Point2D innerArcStart = getPoint(INNER_RADIUS, angle);
            Point2D innerArcEnd = getPoint(INNER_RADIUS, angle + STEP_ANGEL);
            Point2D outerArcStart = getPoint(OUTER_RADIUS, angle);
            Point2D outerArcEnd = getPoint(OUTER_RADIUS, angle + STEP_ANGEL);
            var path = getPath(innerArcStart, innerArcEnd, outerArcStart, outerArcEnd);
            group.getChildren().add(path);
        }
        primaryStage.setScene(new Scene(new Pane(group)));
        primaryStage.show();
    }

    private Point2D getPoint(double radius, double angle){

        double x = ORIGIN_X - radius * Math.cos(Math.toRadians(angle));
        double y = ORIGIN_Y - radius * Math.sin(Math.toRadians(angle));
        return new Point2D(x, y);
    }

    private Shape getPath(Point2D innerArcStart, Point2D innerArcEnd, Point2D outerArcStart, Point2D outerArcEnd){
        var path = new Path(
                new MoveTo(innerArcStart.getX(), innerArcStart.getY()),
                new LineTo(outerArcStart.getX(), outerArcStart.getY()), //left line
                new ArcTo(OUTER_RADIUS, OUTER_RADIUS, 0, outerArcEnd.getX(), outerArcEnd.getY(), false, true), //outer arc
                new LineTo(innerArcEnd.getX(),innerArcEnd.getY()), //right line
                new ArcTo(INNER_RADIUS, INNER_RADIUS, 0, innerArcStart.getX(), innerArcStart.getY(), false, false)
        );
        path.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        return path;
    }

    public static void main(String args[]){
        launch(args);
    }
}
