package com.nhnacademy.message;

public class StringMessage extends Message {
    String payload;
    String senderId;

    public StringMessage(String payload) {
        super();
        this.payload = payload;
    }
    public StringMessage(String senderId, String payload){
        this.senderId = senderId;
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
    public String getSenderId() {
        return senderId;
    }
}
