package com.fxsczxcj.game2048;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private Label[] text = new Label[size];
    private int nodeNum = 0;
    List<Node> nodeList = new ArrayList<>();
    List<Integer> nodeNullInt = new ArrayList<>();
    List<Integer> nodeFallInt = new ArrayList<>();
    private double v;
    private double threshold = 0.6;
    //    BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#ffa502"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8));
//    private Border border = new Border(borderStroke);

    @FXML
    private void initialize() {
        vbox.setOnKeyPressed(keyEvent -> System.out.printf("Key:%s", keyEvent.getCode()));
//        checkerboard.setGridLinesVisible(true);
        int size_x = (int) Math.sqrt(size);
        System.out.println(size_x);


        for (int i = 0; i < text.length; i++) {
            nodeList.add(new Node());
            nodeNullInt.add(i);
            text[i] = new Label();
            GridPane.setHalignment(text[i], HPos.CENTER);
            GridPane.setValignment(text[i], VPos.CENTER);
            text[i].setId(getID(nodeList.get(i).value));
            checkerboard.add(text[i], i % size_x, i / size_x);
        }
    }

    String getID(int i) {
        return "Num" + i;
    }

    void setNum(int i, int v) {
        nodeList.get(i).value = v;
        text[i].setId(getID(v));
        text[i].setText(String.valueOf(v));
        nodeFallInt.add(i);
    }

    Random random = new Random();

    void addRandomNodes() {

        int max = nodeNullInt.size();
        System.out.println("有" + max + "个空位;");
        if (max > 0) {
            int randomInt = random.nextInt(max);
            setNum(nodeNullInt.remove(randomInt), random.nextDouble() < threshold ? 2 : 4);
        } else {
            System.out.println("满了,不添加.");
        }
    }

}