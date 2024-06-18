package com.hamza.draw.test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawController implements Initializable {

    @FXML
    private Sphere sphere;
    @FXML
    private CheckBox button;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Group group;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sphere.translateXProperty().set(anchorPane.getWidth() / 2);
        sphere.translateYProperty().set(anchorPane.getHeight() / 2);

        button.selectedProperty().addListener((observableValue, aBoolean, t1) -> {


            Scene scene = sphere.getScene();
            Stage stage = (Stage) scene.getWindow();

            sphere.translateXProperty().set(scene.getWidth() / 2);
            sphere.translateYProperty().set(scene.getHeight() / 2);

            Camera camera = new PerspectiveCamera();
            scene.setCamera(camera);
            stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
                switch (keyEvent.getCode()) {
                    case UP -> sphere.translateZProperty().set(sphere.getTranslateZ() + 50);
                    case DOWN -> sphere.translateZProperty().set(sphere.getTranslateZ() - 50);
                }
            });
        });
    }
}
