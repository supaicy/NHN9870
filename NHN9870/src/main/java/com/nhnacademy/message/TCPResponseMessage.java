package com.nhnacademy.message;

import java.util.Arrays;

public class TCPResponseMessage extends Message {
    String payload;
    String senderId;

    public TCPResponseMessage(String senderId, String payload) {
        this.payload = payload;
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return payload;
    }
}
