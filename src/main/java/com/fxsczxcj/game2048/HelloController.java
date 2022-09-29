package com.fxsczxcj.game2048;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * The type Hello controller.
 *
 * @author FXSCZXCJ@163.com
 */
public class HelloController {

    @FXML
    public GridPane checkerboard;
    @FXML
    public Label welcomeText;

    final public int size = 4 * 4;
    public VBox vbox;
    public Node[] nodeList = new Node[size];
    private Label[] text = new Label[size];
    //    BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#ffa502"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8));
//    private Border border = new Border(borderStroke);

    @FXML
    private void initialize() {
        vbox.setOnKeyPressed(keyEvent -> System.out.printf("Key:%s", keyEvent.getCode()));
        checkerboard.setGridLinesVisible(true);
        int size_x = (int) Math.sqrt(size);
        System.out.println(size_x);


        for (int i = 0; i < text.length; i++) {
            nodeList[i] = new Node("",0);
            text[i] = new Label();
            GridPane.setHalignment(text[i], HPos.CENTER);
            GridPane.setValignment(text[i], VPos.CENTER);
            text[i].setId(getID(nodeList[i].value));
            checkerboard.add(text[i], i % size_x, i / size_x);
        }
    }

    String getID(int i) {
        return "Num" + i;
    }

    void setNum(int i, int v) {
        text[i].setId(getID(v));
    }

}