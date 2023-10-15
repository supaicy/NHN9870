package com.nhnacademy.node.inout;

import com.nhnacademy.message.HttpMessage;
import com.nhnacademy.message.TCPRequestMessage;

public class RequestParseNode extends InOutNode{

    
    public RequestParseNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    protected void main() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {
            TCPRequestMessage request = (TCPRequestMessage) getInputWire(0).get();

            String payload = new String(request.getPayload());
            String requestLine = payload.split(System.lineSeparator())[0];
            
            String httpMethod = requestLine.split(" ")[0];
            String path = requestLine.split(" ")[1];

            System.out.println("=====RequestParseNode=====");
            System.out.println("HttpMethod =" + httpMethod);
            System.out.println("path = " + path);
            System.out.println("==========================");
            
            HttpMessage httpMessage = new HttpMessage(request.getSenderId(), httpMethod, path );
            output(httpMessage);
        }
    }
    
}
