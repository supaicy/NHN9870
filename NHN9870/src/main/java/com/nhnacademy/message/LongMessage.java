package com.nhnacademy.message;

public class LongMessage extends Message {
    long payload;

    public LongMessage (long payload) {
        super();
        this.payload = payload;
    }

    public long getMessage() {
        return payload;
    }
}
