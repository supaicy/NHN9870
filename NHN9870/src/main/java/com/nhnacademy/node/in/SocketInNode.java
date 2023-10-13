package com.nhnacademy.node.in;

import com.nhnacademy.message.TCPRequestMessage;
import java.io.BufferedInputStream;
import java.io.IOException;

public class SocketInNode extends InputNode {

    BufferedInputStream inputStream;
    byte[] buffer;

    public SocketInNode(int count, BufferedInputStream inputStream){
        super(count);
        this.inputStream = inputStream;
    }

    @Override
    protected void preprocess(){
        buffer = new byte[100000];
    }

    @Override
    protected void main(){
        int length = 0;
        while(!Thread.currentThread().isInterrupted()){
            try {
                length = inputStream.read(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        TCPRequestMessage message = new TCPRequestMessage(this.getId(),buffer,length);
        output(message);
    }

    @Override
    protected void postprocess(){
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
