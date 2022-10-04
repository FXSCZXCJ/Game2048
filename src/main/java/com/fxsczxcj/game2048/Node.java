package com.fxsczxcj.game2048;

public class Node {
    int index;
    int value = 0;
    boolean isUpdate = false;

    public Node(int index) {
        this.index = index;
    }

    public void multiplyValue() {
        this.isUpdate = true;
        this.value = value * 2;
    }

    public int getValue() {
        this.isUpdate = true;
        return value;
    }

    public void setValue(int value) {
        this.isUpdate = true;
        this.value = value;
    }

    public void addNode(Node downNode) {
        this.setValue(this.value * 2);
        downNode.setValue(0);
    }
    public void moveNode(Node downNode) {
        this.value = downNode.value;
        downNode.value = 0;
    }
}
