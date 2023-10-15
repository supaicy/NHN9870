package com.nhnacademy.node.http;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.nhnacademy.message.HttpMessage;
import com.nhnacademy.message.HttpRequestMessage;
import com.nhnacademy.message.StringMessage;
import com.nhnacademy.message.TCPResponseMessage;
import com.nhnacademy.node.inout.InOutNode;

public class ResponseParseNode extends InOutNode {
    JSONParser jsonParser;

    public ResponseParseNode(int inCount, int outCount) {
        super(inCount, outCount);
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void main() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {

            System.out.println("====ResponseParseNode====");

            TCPResponseMessage inMessage = (TCPResponseMessage) getInputWire(0).get();

            String payLoad = inMessage.getPayload();

            String[] parts = payLoad.split("\r\n\r\n");

            if (parts.length == 2) {
                String headers = parts[0];
                String body = parts[1];

                // JSON 파싱
                // JSONArray jsonArray;
                // try {
                //     jsonArray = (JSONArray) jsonParser.parse(body);
                //     // 첫 번째 객체의 id추출
                //     JSONObject firstDevice = (JSONObject) jsonArray.get(0);
                //     String firstId = (String) firstDevice.get("id");

                //     // "dev/{id}" 형식의 새로운 요청 문자열 생성
                //     String newRequest = "dev/" + firstId;

                //     // 새로운 요청을 사용하여 다른 작업 수행
                //     System.out.println("새 Path: " + newRequest);
                // } catch (ParseException e) {

                //     e.printStackTrace();
                // }
                output(new StringMessage(inMessage.getSenderId(), body));
            }

            
        }
    }
}
