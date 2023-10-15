package com.nhnacademy.message;

public class HttpRequestMessage extends Message{
    String senderId;
    String httpRequest;

    public HttpRequestMessage(String senderId,String httpRequest){
        super();
        this.senderId = senderId;
        this.httpRequest = httpRequest;
    }

    public String getSenderId() {
        return senderId;
    }
    public String getHttpRequest() {
        return httpRequest;
    }
}
