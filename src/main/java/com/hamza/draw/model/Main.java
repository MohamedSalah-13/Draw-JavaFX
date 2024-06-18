package com.hamza.draw.model;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableDoubleValue;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application {
    private static final double SCENE_WIDTH = 1000;
    private static final double SCENE_HEIGHT = 700;
    @Override
    public void start(Stage primaryStage) {
        Circle sun = createCelestialBody(50, Color.YELLOW);
        Circle earth = createCelestialBody(20, Color.BLUE);
        Circle earthOrbitIndicator = createOrbitIndicator(150);
        Circle jupiter = createCelestialBody(35, Color.BROWN);
        Circle jupiterOrbitIndicator = createOrbitIndicator(300);
        Line earthJupiterVector = createBodyToBodyVector(earth, jupiter);
        DoubleBinding angleObservable = createAngleBinding(earthJupiterVector);
        Line xAxisIndicator = createXAxisIndicator(earth);
        Arc angleIndicator = createAngleIndicator(earth, angleObservable);
        Pane root = new Pane(
                        createAngleLabel(angleObservable),
                        earthOrbitIndicator,
                        jupiterOrbitIndicator,
                        sun,
                        earth,
                        jupiter,
                        earthJupiterVector,
                        xAxisIndicator,
                        angleIndicator);
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        primaryStage.setTitle("Earth-Jupiter Vector Angle");
        primaryStage.setResizable(false);
        primaryStage.show();
        animateOrbit(Duration.seconds(7), earth, earthOrbitIndicator.getRadius());
        animateOrbit(Duration.seconds(16), jupiter, jupiterOrbitIndicator.getRadius());
    }
    private Label createAngleLabel(ObservableDoubleValue angleObservable) {
        Label label = new Label();
        label.setPadding(new Insets(10));
        label.setUnderline(true);
        label.setFont(Font.font("Monospaced", FontWeight.BOLD, 18));
        label
                .textProperty()
                .bind(
                        Bindings.createStringBinding(
                                () -> String.format("Angle: %06.2f", angleObservable.get()), angleObservable));
        return label;
    }
    private Circle createCelestialBody(double radius, Color fill) {
        Circle body = new Circle(radius, fill);
        body.setCenterX(SCENE_WIDTH / 2);
        body.setCenterY(SCENE_HEIGHT / 2);
        return body;
    }
    private Circle createOrbitIndicator(double radius) {
        Circle indicator = new Circle(radius, Color.TRANSPARENT);
        indicator.setStroke(Color.DARKGRAY);
        indicator.getStrokeDashArray().add(5.0);
        indicator.setCenterX(SCENE_WIDTH / 2);
        indicator.setCenterY(SCENE_HEIGHT / 2);
        return indicator;
    }
    private void animateOrbit(Duration duration, Circle celestialBody, double orbitRadius) {
        Circle path = new Circle(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, orbitRadius);
        PathTransition animation = new PathTransition(duration, path, celestialBody);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.playFromStart();
    }
    private Line createBodyToBodyVector(Circle firstBody, Circle secondBody) {
        Line vectorLine = new Line();
        vectorLine.setStroke(Color.BLACK);
        vectorLine.setStrokeWidth(2);
        vectorLine.getStrokeDashArray().add(5.0);
        vectorLine.startXProperty().bind(centerXInParentOf(firstBody));
        vectorLine.startYProperty().bind(centerYInParentOf(firstBody));
        vectorLine.endXProperty().bind(centerXInParentOf(secondBody));
        vectorLine.endYProperty().bind(centerYInParentOf(secondBody));
        return vectorLine;
    }
    private Line createXAxisIndicator(Circle anchor) {
        Line xAxisIndicator = new Line();
        xAxisIndicator.setStroke(Color.GREEN);
        xAxisIndicator.setStrokeWidth(2);
        xAxisIndicator.startXProperty().bind(centerXInParentOf(anchor));
        xAxisIndicator.startYProperty().bind(centerYInParentOf(anchor));
        xAxisIndicator.endXProperty().bind(xAxisIndicator.startXProperty().add(75));
        xAxisIndicator.endYProperty().bind(xAxisIndicator.startYProperty());
        return xAxisIndicator;
    }
    private Arc createAngleIndicator(Circle anchor, ObservableDoubleValue angleObservable) {
        Arc arc = new Arc();
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.RED);
        arc.setStrokeWidth(2);
        arc.getStrokeDashArray().add(5.0);
        arc.centerXProperty().bind(centerXInParentOf(anchor));
        arc.centerYProperty().bind(centerYInParentOf(anchor));
        arc.setRadiusX(50);
        arc.setRadiusY(50);
        arc.setStartAngle(0);
        arc.lengthProperty().bind(angleObservable);
        return arc;
    }
    // NOTE: getCenterX() and getCenterY() were added in JavaFX 11. The calculations
    //       are simple, however. It's just (minX + maxX) / 2 and similar for y.
    private DoubleBinding centerXInParentOf(Node node) {
        return Bindings.createDoubleBinding(
                () -> node.getBoundsInParent().getCenterX(), node.boundsInParentProperty());
    }
    private DoubleBinding centerYInParentOf(Node node) {
        return Bindings.createDoubleBinding(
                () -> node.getBoundsInParent().getCenterY(), node.boundsInParentProperty());
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
}