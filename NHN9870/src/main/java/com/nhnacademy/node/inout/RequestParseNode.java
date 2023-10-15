package com.nhnacademy.node.inout;

import com.nhnacademy.message.HttpMessage;
import com.nhnacademy.message.TCPRequestMessage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RequestParseNode extends InOutNode{

    
    public RequestParseNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    protected void main() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {
            log.info("RequestParseNode start");
            TCPRequestMessage request = (TCPRequestMessage) getInputWire(0).get();

            String payload = new String(request.getPayload());
            String requestLine = payload.split(System.lineSeparator())[0];
            
            String httpMethod = requestLine.split(" ")[0];
            String path = requestLine.split(" ")[1];
            
            HttpMessage httpMessage = new HttpMessage(request.getSenderId(), httpMethod, path );
            output(httpMessage);
            log.info("RequestParseNode end");
        }
    }
    
}
