package com.fxsczxcj.game2048;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    public GridPane checkerboard;
    @FXML
    public Label welcomeText;
//    BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#ffa502"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8));
//    private Border border = new Border(borderStroke);

    @FXML
    private void initialize() {
        checkerboard.setGridLinesVisible(true);

        Label[] text = new Label[4 * 4];

        for (int i = 0; i < text.length; i++) {
            text[i] = new Label("2");
            GridPane.setHalignment(text[i], HPos.CENTER);
            GridPane.setValignment(text[i], VPos.CENTER);
            text[i].setId("Num0");
            checkerboard.add(text[i], i / 4, i % 4);


        }
        System.out.println(Num.Num0.name());
    }

    enum Num {
        Num0,
        Num2


    }

}