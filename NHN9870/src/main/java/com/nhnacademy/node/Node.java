package com.nhnacademy.node;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Node {
    String id;
    static int count;

    Node(String id) {
        this.id = id;
    }

    Node() {
        this(String.valueOf(System.currentTimeMillis()) + (++count));
        log.trace("create node : {}", id);
    }

    public String getId() {
        return id;
    }

    public int getCount() {
        return count;

    }

}
