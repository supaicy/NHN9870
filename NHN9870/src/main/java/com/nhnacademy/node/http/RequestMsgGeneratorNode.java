package com.nhnacademy.node.http;

import com.nhnacademy.message.HttpMessage;
import com.nhnacademy.message.HttpRequestMessage;
import com.nhnacademy.node.inout.InOutNode;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RequestMsgGeneratorNode extends InOutNode{

    private String path = "/dev";
    private String host = "ems.nhnacademy.com:1880";
    private String CRLF = "\r\n";

    public RequestMsgGeneratorNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    protected void main() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {

            log.info("RequestMsgGeneratorNode start");

            HttpMessage request = (HttpMessage) getInputWire(0).get();
            
            StringBuilder httpRequestBuilder = new StringBuilder();
            httpRequestBuilder.append("GET ").append(path).append(" HTTP/1.1").append(CRLF);
            httpRequestBuilder.append("Host: ").append(host).append(CRLF);
            httpRequestBuilder.append(CRLF);
            String httpRequestString = httpRequestBuilder.toString();

            output(new HttpRequestMessage(request.getSenderId(), httpRequestString));
            log.info("RequestMsgGeneratorNode end");
        }
    }
}
