package com.fxsczxcj.game2048;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class HelloController {

    @FXML
    public GridPane checkerboard;
    @FXML
    public Label welcomeText;
    private HelloApplication mainApp;
    private Border border;

    {
        BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#ffa502"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8));
        border = new Border(borderStroke);
    }


    @FXML
    private void initialize() {
        checkerboard.setGridLinesVisible(true);

        Label[] text = new Label[4 * 4];

        for (int i = 0; i < text.length; i++) {
            text[i] = new Label("2");
//            text[i].setBorder(border);

            GridPane.setHalignment(text[i], HPos.CENTER);
            GridPane.setValignment(text[i], VPos.CENTER);

            checkerboard.add(text[i], i / 4, i % 4);
        }
    }


}