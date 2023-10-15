package com.nhnacademy.node.http;

import com.nhnacademy.message.HttpMessage;
import com.nhnacademy.message.HttpRequestMessage;
import com.nhnacademy.message.StringMessage;
import com.nhnacademy.message.TCPResponseMessage;
import com.nhnacademy.node.inout.InOutNode;

public class ResponseMsgGeneratorNode extends InOutNode{
    private String CRLF = "\r\n";

    public ResponseMsgGeneratorNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    protected void main(){
            if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {
            StringBuilder responseBuilder = new StringBuilder();
            System.out.println("====ResponseMsgGeneratorNode====");

            StringMessage inMessage = (StringMessage) getInputWire(0).get();
            String responseBody =  inMessage.getPayload();
            int contentLength = responseBody.length();
            
            responseBuilder.append("HTTP/1.1 200 OK"+CRLF);
            responseBuilder.append("Content-Length: " + contentLength+CRLF);
            responseBuilder.append(CRLF);
            responseBuilder.append(responseBody);
            
            System.out.println(responseBuilder.toString());
            output(new TCPResponseMessage(inMessage.getSenderId(), responseBuilder.toString()));
        }
    }
    
}
