package com.nhnacademy.message;

public class HttpMessage extends Message{

    private String senderId;
    private String httpMethod;
    private String path;

    public HttpMessage(String senderId, String httpMethod, String path){
        this.senderId = senderId;
        this.httpMethod = httpMethod;
        this.path = path;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }
}
