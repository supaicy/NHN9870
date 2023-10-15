package com.nhnacademy.node.out;

import java.io.IOException;
import com.nhnacademy.message.TCPResponseMessage;
import com.nhnacademy.node.in.SocketInNode;
import com.nhnacademy.node.in.SocketInNode.Handler;

public class SocketOut extends OutputNode{

    public SocketOut(int count) {
        super(count);
    }


    @Override
    protected void main() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {

        TCPResponseMessage inMessage = (TCPResponseMessage) getInputWire(0).get();
        Handler handler = SocketInNode.getHandler(inMessage.getSenderId());
        
        try {
            handler.getBufferedWriter().write(inMessage.getPayload());
            handler.getBufferedWriter().flush();
            handler.stop();
        } catch (IOException e) {

            e.printStackTrace();
        }
        }
    }

}
