package com.hamza.draw.test;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckerBoard extends Application {

    private static final int BOARD_SIZE = 8 ;
    private static final int SQUARE_SIZE= 40 ;
    private static final int NUM_PIECES = 12 ;

    @Override
    public void start(Stage primaryStage) {
        GridPane checkerBoard = new GridPane();

        configureBoardLayout(checkerBoard);

        addSquaresToBoard(checkerBoard);

        Circle[] whitePieces = new Circle[NUM_PIECES];
        Circle[] blackPieces = new Circle[NUM_PIECES];

        addPiecesToBoard(checkerBoard, whitePieces, blackPieces);

        BorderPane root = new BorderPane(checkerBoard);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    private void addPiecesToBoard(GridPane checkerBoard, Circle[] whitePieces,
                                  Circle[] blackPieces) {
        for (int i=0; i<NUM_PIECES; i++) {
            whitePieces[i] = new Circle(SQUARE_SIZE/2-4, Color.WHITE);
            whitePieces[i].setStroke(Color.BLACK);
            checkerBoard.add(whitePieces[i], i%(BOARD_SIZE/2) * 2 + (2*i/BOARD_SIZE)%2, BOARD_SIZE - 1 - (i*2)/BOARD_SIZE);

            blackPieces[i] = new Circle(SQUARE_SIZE/2 -4, Color.BLACK);
            blackPieces[i].setStroke(Color.WHITE);
            checkerBoard.add(blackPieces[i], i%(BOARD_SIZE/2) * 2 + (1 + 2*i/BOARD_SIZE)%2, (i*2)/BOARD_SIZE);
        }
    }

    private void addSquaresToBoard(GridPane board) {
        Color[] squareColors = new Color[] {Color.WHITE, Color.BLACK};
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board.add(new Rectangle(SQUARE_SIZE, SQUARE_SIZE, squareColors[(row+col)%2]), col, row);
            }
        }
    }

    private void configureBoardLayout(GridPane board) {
        for (int i=0; i<BOARD_SIZE; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(SQUARE_SIZE);
            rowConstraints.setPrefHeight(SQUARE_SIZE);
            rowConstraints.setMaxHeight(SQUARE_SIZE);
            rowConstraints.setValignment(VPos.CENTER);
            board.getRowConstraints().add(rowConstraints);

            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setMinWidth(SQUARE_SIZE);
            colConstraints.setMaxWidth(SQUARE_SIZE);
            colConstraints.setPrefWidth(SQUARE_SIZE);
            colConstraints.setHalignment(HPos.CENTER);
            board.getColumnConstraints().add(colConstraints);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}