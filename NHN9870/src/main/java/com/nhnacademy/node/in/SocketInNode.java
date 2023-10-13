package com.nhnacademy.node.in;

import com.nhnacademy.message.TCPRequestMessage;
import java.io.IOException;
import com.nhnacademy.node.Server.Handler;

public class SocketInNode extends InputNode {

    String handlerId;
    Handler handler;
    byte[] buffer;

    public SocketInNode(int count, String handlerId){
        super(count);
        this.handlerId = handlerId;
    }

    @Override
    protected void preprocess(){
        buffer = new byte[100000];
        this.handler = TCPServer.getHandler(handlerId);
    }

    @Override
    protected void main(){
        int length = 0;
        while(!Thread.currentThread().isInterrupted()) {
            try {
                length = handler.getInputStream().read(buffer);
                TCPRequestMessage message = new TCPRequestMessage(handlerId, buffer, length);
                output(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void postprocess(){
        try {
            handler.getInputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
