package com.nhnacademy.node.http;

import com.nhnacademy.message.HttpMessage;
import com.nhnacademy.message.HttpRequestMessage;
import com.nhnacademy.node.inout.InOutNode;

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

            System.out.println("====RequestMsgGeneratorNode====");

            HttpMessage request = (HttpMessage) getInputWire(0).get();
            
            //path = request.getPath()
            StringBuilder httpRequestBuilder = new StringBuilder();
            httpRequestBuilder.append("GET ").append(path).append(" HTTP/1.1").append(CRLF);
            httpRequestBuilder.append("Host: ").append(host).append(CRLF);
            httpRequestBuilder.append(CRLF);
            String httpRequestString = httpRequestBuilder.toString();
            System.out.println(httpRequestString);
            System.out.println("=============================");

            output(new HttpRequestMessage(request.getSenderId(), httpRequestString));
        }
    }
}
