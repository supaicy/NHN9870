package com.nhnacademy.message;

import java.util.Arrays;

public class TCPRequestMessage extends Message {
    byte[] payload;
    String senderId;

    public TCPRequestMessage(String senderId, byte[] payload, int length) {
        this.payload = Arrays.copyOf(payload, length);
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
