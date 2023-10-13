package com.nhnacademy.message;

public class BooleanMessage extends Message {
    boolean payload;

    public BooleanMessage(boolean payload) {
        super();
        this.payload = payload;
    }

    public boolean getPayload() {
        return payload;
    }
}