package com.nhnacademy.node.http;

import com.nhnacademy.message.StringMessage;
import com.nhnacademy.message.TCPResponseMessage;
import com.nhnacademy.node.inout.InOutNode;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ResponseMsgGeneratorNode extends InOutNode{
    private String CRLF = "\r\n";

    public ResponseMsgGeneratorNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    protected void main(){
            if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {
            StringBuilder responseBuilder = new StringBuilder();
            log.info("ResponseMsgGeneratorNode start");

            StringMessage inMessage = (StringMessage) getInputWire(0).get();
            String responseBody =  inMessage.getPayload();
            int contentLength = responseBody.length();
            
            responseBuilder.append("HTTP/1.1 200 OK"+CRLF);
            responseBuilder.append("Content-Length: " + contentLength+CRLF);
            responseBuilder.append(CRLF);
            responseBuilder.append(responseBody);
            
            output(new TCPResponseMessage(inMessage.getSenderId(), responseBuilder.toString()));
            log.info("ResponseMsgGeneratorNode end");
        }
    }
    
}
