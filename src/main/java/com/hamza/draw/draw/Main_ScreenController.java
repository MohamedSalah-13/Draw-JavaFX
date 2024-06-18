package com.hamza.draw.draw;

import com.hamza.draw.model.Size_model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.hamza.extension.fx.alert.AllAlerts;
import org.hamza.extension.fx.table.Column;
import org.hamza.extension.fx.table.Table_Setting;

import java.net.URL;
import java.util.*;

import static org.hamza.extension.fx.Utils.whenEnterPressed;
import static org.hamza.extension.text.Calc.getRound;

public class Main_ScreenController implements Initializable {

    private final double INCH = 0.394;
    private final double CM = 2.54;
    @FXML
    private Pane pane;
    @FXML
    private Button btn_insert, btn_show, all, btn_clear;
    @FXML
    private TextField text_height, text_sum, text_width, text_w, text_h;
    @FXML
    private Text text3;
    @FXML
    private Label label1, label2, label3, label4, label5;
    @FXML
    private TableView<Size_model> tableView;
    @FXML
    private StackPane stackPane;
    private Text tex_w, tex_h;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name_setting();
        other_setting();
        get_table();

        text_width.setText("150");
        text_height.setText("100");
        text_sum.setText("5");

        btn_insert.fire();
        all.fire();
    }

    private void name_setting() {
        text_width.setPromptText("العرض");
        text_height.setPromptText("الطول");
        text_sum.setText("1");
        text3.setText("إضافة مقاس");

        label1.setText(text_width.getPromptText());
        label2.setText(text_height.getPromptText());
        label3.setText("عدد");

        label4.setText(text_width.getPromptText());
        label5.setText(text_height.getPromptText());
        btn_show.setText("show shape");
        btn_insert.setText("add");
        all.setText("عرض الشكل");
        btn_clear.setText("حذف");

    }

    private void other_setting() {
        stackPane.setAlignment(Pos.TOP_LEFT);
        pane.setPrefSize(580, 330);
        tex_w = new Text_Size(-20, pane.getPrefWidth(), 0, "");
        tex_h = new Text_Size(-35, pane.getPrefHeight(), 270, "height");
        stackPane.getChildren().addAll(tex_h, tex_w);


        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        whenEnterPressed(text_width, text_height, text_sum, btn_insert);

        text_w.setText(String.valueOf(pane.getPrefWidth()));
        text_h.setText(String.valueOf(pane.getPrefHeight()));

        btn_insert.setOnAction(actionEvent -> add_row_all());
        all.setOnAction(event -> draw_shape());
        btn_show.setOnAction(event -> {
            double w = Double.parseDouble(text_w.getText());
            double h = Double.parseDouble(text_h.getText());
            pane.setPrefSize(w, h);
            tex_w.setText(w + " سم ");
            tex_h.setText(h + " سم ");

            if (tableView.getItems().size() > 0) all.fire();

        });

        btn_clear.setOnAction(event -> {
            resetAll();
            pane.getChildren().clear();
            tableView.getItems().clear();
//            pane.getChildren().addAll(tex_h, tex_w);
        });
    }

    private double[][] get_size() {
        int count = 0;
        for (int i = 0; i < tableView.getItems().size(); i++) {
            count += tableView.getItems().get(i).getCount();
        }
        double[][] size = new double[count][2];
        int i = 0;
        for (int j = 0; j < tableView.getItems().size(); j++) {
            if (tableView.getItems().get(j).getCount() > 1) {
                for (int k = 0; k < tableView.getItems().get(j).getCount(); k++) {
                    size[i][0] = getRound(tableView.getItems().get(j).getWidth());
                    size[i][1] = getRound(tableView.getItems().get(j).getHeight());
                    i++;
                }
            } else {
                size[i][0] = getRound(tableView.getItems().get(j).getWidth());
                size[i][1] = getRound(tableView.getItems().get(j).getHeight());
                i++;
            }
        }
        return size;
    }

    private void resetAll() {
        text_height.clear();
        text_width.clear();
        text_sum.setText("1");
        text_width.requestFocus();
    }

    private void get_table() {
        List<Size_model> list_items = FXCollections.observableArrayList();
        List<Column<?>> columns = new ArrayList<>(Arrays.asList(
                new Column<>(Integer.class, "num", "#"),
                new Column<>(Double.class, "width", "العرض"),
                new Column<>(Double.class, "height", "الطول"),
                new Column<>(Integer.class, "count", "العدد")
        ));

        tableView.getColumns().clear();
        Table_Setting.createTable(tableView, columns, list_items);
    }

    private void add_row_all() {
        double pr = Double.parseDouble(text_width.getText());
        if (text_width.getText().isEmpty() || pr <= 0.0) {
            AllAlerts.alertError("من فضلك ادخل بيانات صحيحة");
            text_width.requestFocus();
            return;
        }
        if (text_height.getText().isEmpty() || Double.parseDouble(text_height.getText()) <= 0) {
            AllAlerts.alertError("من فضلك ادخل بيانات صحيحة");
            text_height.requestFocus();
            return;
        }
        if (text_sum.getText().isEmpty()) {
            AllAlerts.alertError("من فضلك ادخل نوع الوحدة");
            text_sum.requestFocus();
            return;
        }

        Size_model model = new Size_model();
        model.setWidth(pr);
        model.setHeight(Double.parseDouble(text_height.getText()));
        model.setCount(Integer.parseInt(text_sum.getText()));
        model.setNum(tableView.getItems().size() + 1);
        tableView.getItems().add(model);
        resetAll();
    }

    private void draw_shape() {
        pane.getChildren().clear();

        double[][] size = get_size();
        double x = 5, y = 5, sum_height = 0;

        Arrays.sort(size, (a, b) -> Double.compare(b[0], a[0])); //decreasing order

//        Arrays.sort(size, Comparator.comparingDouble(a -> a[0]));
//        System.out.println(Arrays.deepToString(size));

        for (int i = 0; i < size.length; i++) {
            sum_height += size[i][1];

            if (i != 0) {
                if (sum_height > pane.getPrefHeight()) {
                    x += (size[i - 1][0] + 5);
                    y = 5;
                } else {
                    x = 5;
                    y += (size[i - 1][1] + 5);
                }
            }

//            stackPane.getChildren().add(new Label_Show().draw_label(x,y,size[i][0], size[i][1]));
//            new Rectangle_show(stackPane).draw_Rectangle(x, y, size[i][0], size[i][1]);
            pane.getChildren().addAll(new StackPane_draw(x, y, size[i][0], size[i][1]));

        }

        StringProperty password = new SimpleStringProperty("java2s.com");
        password.set("example.com");
        System.out.println("Modified StringProperty " + password.get());
//        BoundingBox boundingBox = new BoundingBox();
//        print_rect_id();

        System.out.println(getScreenDimensions());
    }


    private void print_rect_id() {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            double x = pane.getChildren().get(i).getTranslateX();
            double y = pane.getChildren().get(i).getTranslateY();
            StackPane size = (StackPane) pane.getChildren().get(i);
            double w = size.getPrefWidth();
            double h = size.getPrefHeight();

            System.out.println("(x : " + x + " , y : " + y + ")(size : " + w + " * " + h + ")(id :" + size.getId() + " )");
        }
    }

    private void draw2() {
        double[][] size = get_size();
        double x = 5, y = 5, sum_height = 0, sum_width = 0;
        double w = new Other_size().get_sum_Array(0, size); // sum width
        double h = new Other_size().get_sum_Array(1, size); // sum height

        double modulo = (h % pane.getPrefHeight());
        double div = Math.incrementExact((int) (h / pane.getPrefHeight()));

        HBox hBox = new HBox();
        for (int i = 0; i < div; i++) {
            VBox vBox = new VBox();
            for (int j = 0; j < size.length; j++) {
                sum_width += size[j][0];
                sum_height += size[j][1];
                if (sum_height > pane.getPrefHeight()) {
                    vBox.getChildren().addAll(new StackPane_draw(size[j][0], size[j][1]));
                }
            }

            vBox.setPrefSize(150, pane.getPrefHeight());
            vBox.setStyle("-fx-border-color: red");
            hBox.getChildren().addAll(vBox);
        }
        pane.getChildren().addAll(hBox);
    }

    public static HashMap<String, Double> getScreenDimensions() {

        HashMap<String, Double> screenDimensions = new HashMap<>();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        screenDimensions.put("width", screenBounds.getWidth());
        screenDimensions.put("height", screenBounds.getHeight());

        return screenDimensions;
    }/* w w  w.  j a va2 s.com*/
}
