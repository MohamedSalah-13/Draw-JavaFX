package com.hamza.draw.test;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Chess extends Application {

    private final String[] COLORS = {"black","white"};
    private static int ROWS = 4, COLS = 4;

    @Override
    public void start(Stage stage) {

        Board board = new Board(COLS);

        int tileNum = 0;

        for(int row = 0; row < ROWS ; row++){
            tileNum = tileNum == 0 ? 1:0;
            for(int col = 0; col < COLS; col++){
                Tile tile = new Tile(COLORS[tileNum]);
                if(row==ROWS/2 && col == COLS/2) {//place an arbitrary piece
                    tile.setPiece(Pieces.KING.getImage());
                }
                board.addTile(tile.getTile());
                tileNum = tileNum == 0 ? 1:0;
            }
        }

        Parent root = new Group(board.getBoard());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Board {

    private final TilePane board;

    public Board(int columns) {
        board = new TilePane(Orientation.HORIZONTAL);
        board.setPrefColumns(columns);
        board.setTileAlignment(Pos.CENTER);
        board.setStyle("-fx-border-color:red;");
    }

    Pane getBoard(){
        return board;
    }

    void addTile(Node node){
        board.getChildren().add(node);
    }
}

class Tile {

    public static final int SIZE = 100;
    private final StackPane tile;

    Tile(String colorName) {
        this(colorName, null);
    }

    Tile(String colorName, Image piece) {
        Rectangle rect = new Rectangle(SIZE, SIZE, Color.valueOf(colorName));
        tile = new StackPane(rect);
        tile.setStyle("-fx-border-color:red;");
        if(piece != null) {
            setPiece(piece);
        }
    }

    void setPiece(Image piece){
        tile.getChildren().add(new ImageView(piece));
    }

    public Node getTile() {
        return tile;
    }
}

enum Pieces {

    KING ("https://cdn3.iconfinder.com/data/icons/softwaredemo/PNG/64x64/Circle_Blue.png"),
    QUEEN ("https://cdn3.iconfinder.com/data/icons/softwaredemo/PNG/64x64/Circle_Orange.png");

    private String image;
    private Pieces(String image) {
        this.image = image;
    }

    public Image getImage(){
        return new Image(image);
    }
}
