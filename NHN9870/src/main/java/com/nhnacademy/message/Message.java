package com.nhnacademy.message;

public class Message {
    static int count = 0;
    final String id;
    long creationTime;

    Message() {
        creationTime = System.currentTimeMillis();
        id = String.valueOf(creationTime) + (++count);
    }

    public String getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public static int getCount() {
        return count;
    }
}
