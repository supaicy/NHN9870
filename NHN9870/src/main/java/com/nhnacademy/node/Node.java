package com.nhnacademy.node;

public class Node {
    String id;
    static int count;

    Node(String id) {
        this.id = id;
    }

    Node() {
        this(String.valueOf(System.currentTimeMillis()) + (++count));
    }

    public int getCount() {
        return count;
    }

}
