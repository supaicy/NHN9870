package com.nhnacademy.message;

public class StringMessage extends Message {
    String payload;

    public StringMessage(String payload) {
        super();
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
