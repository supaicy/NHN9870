package com.nhnacademy.message;

import java.util.Arrays;

public class TCPResponseMessage extends Message {
    byte[] payload;
    String senderId;

    public TCPResponseMessage(String senderId, byte[] payload) {
        this.payload = Arrays.copyOf(payload, payload.length);
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public byte[] getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return Arrays.toString(payload);
    }
}
