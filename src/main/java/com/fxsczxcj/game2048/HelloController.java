package com.fxsczxcj.game2048;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * The type Hello controller.
 *
 * @author FXSCZXCJ@163.com
 */
public class HelloController {

    final private int size_w = 4;
    final private int size_h = 4;
    final public int sizeMax = size_w * size_h;
    final private Label[] text = new Label[sizeMax];
    @FXML
    public GridPane checkerboard;
    @FXML
    public Label titleText;
    public VBox vbox;
    ArrayList<Node> nodeList = new ArrayList<>();
    ArrayList<Integer> nullIndex = new ArrayList<>();
    Random random = new Random();


    @FXML
    private void initialize() {
        initNodeValue();
    }

    private void initNodeValue() {
        for (int index = 0; index < sizeMax; index++) {
            nodeList.add(new Node(index));
            nullIndex.add(index);
            text[index] = new Label();
            text[index].setId(getID(nodeList.get(index).value));
            checkerboard.add(text[index], index % size_w, index / size_w);
        }

        addRandomNodes();
        addRandomNodes();
    }

    String getID(int i) {
        return "Num" + i;
    }

    void setNodeValue(int index, int v) {
        nodeList.get(index).setValue(v);
    }

    void addRandomNodes() {
        int max = nullIndex.size();
        System.out.println("max = " + max);
        if (max > 0) {
            int randomInt = random.nextInt(max);
            int index = nullIndex.remove(randomInt);
            //    BorderStroke borderStroke = new BorderStroke(Paint.valueOf("#ffa502"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(8));
            //    private Border border = new Border(borderStroke);
            double threshold = 0.6;
            setNodeValue(index, random.nextDouble() < threshold ? 2 : 4);
            updateNode(index);
        } else {
            System.out.println("满了,不能添加.");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("游戏结束");
            alert.setHeaderText(null);
            alert.setContentText("是否重新开始?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ReInitNode();
            }

        }
    }


    public void ReInitNode() {
        nullIndex.clear();
        for (Node node : nodeList) {
            node.value = 0;
            nullIndex.add(node.index);
        }
        updateList();
    }

    public void updateList() {
        nullIndex.clear();
        for (Node node : nodeList) {
            if (node.value == 0) {
                nullIndex.add(node.index);
            }
            text[node.index].setId(getID(node.value));
            text[node.index].setText(String.valueOf(node.value));
        }
    }

    public void updateNode(int index) {
        nullIndex.remove((Object) index);
        Node node = nodeList.get(index);
        text[index].setId(getID(node.value));
        text[index].setText(String.valueOf(node.value));
    }

    public void upDate(Status key) {
        System.out.printf("Key:%s\t", key.name());
        switch (key) {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
                NodeUpDown(key);
                break;
            case SPACE:
                ReInitNode();
                return;
            default:
                return;
        }

        addRandomNodes();
    }

    private void NodeUpDown(Status key) {
        boolean qh = true, fx = true;
        if (key == Status.UP || key == Status.DOWN) {
            qh = key == Status.UP;
        }
        if (key == Status.LEFT || key == Status.RIGHT) {
            fx = false;
            qh = key == Status.LEFT;
        }
        int sw = fx ? size_w : size_h;
        int sh = !fx ? size_w : size_h;
        int[][] index_list = new int[sw][sh];
        ArrayList<Node>[] temp = new ArrayList[sw];

        int index_temp;

        for (int w = 0; w < sw; w++) {
            temp[w] = new ArrayList<>();
            for (int h = 0; h < sh; h++) {
                index_temp = fx ? (h * sw + w) : (h + w * sh);
                index_list[w][h] = index_temp;
                if (nodeList.get(index_temp).value != 0) {
                    if (qh) {
                        temp[w].add(nodeList.get(index_temp));//添加到队列末尾
                    } else {
                        temp[w].add(0, nodeList.get(index_temp));//添加到队列头部
                    }
                }
            }
        }
        for (int w = 0; w < size_w; w++) {
            ArrayList<Node> nodes = temp[w];
            for (int s = 0, h_max = nodes.size() - 1; s < h_max; s++) {
                Node upNode = nodes.get(s);
                Node downNode = nodes.get(s + 1);
                if (upNode.value == downNode.value) {
                    upNode.addNode(downNode);
                    nodes.remove(downNode);
                    h_max--;
                }
            }

            for (int s = 0, h_max = nodes.size(); s < h_max; s++) {
                Node oldNode = nodeList.get(index_list[w][qh ? s : sh - s - 1]);
                Node newNode = nodes.get(s);
                if (newNode.index != oldNode.index) {
                    oldNode.moveNode(newNode);
                }
            }
        }
        updateList();
    }
}