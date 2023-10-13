package com.nhnacademy.node.in;

import com.nhnacademy.message.StringMessage;

import java.io.BufferedReader;
import java.io.IOException;

public class SocketInNode extends InputNode {
    BufferedReader reader;

    public SocketInNode(int count, BufferedReader reader){
        super(count);
        this.reader = reader;
    }

    @Override
    protected void preprocess(){}

    @Override
    protected void main(){
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringMessage message = new StringMessage(line);
        output(message);
    }

}
