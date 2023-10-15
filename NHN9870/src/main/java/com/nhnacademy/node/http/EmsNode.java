package com.nhnacademy.node.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.simple.parser.JSONParser;
import com.nhnacademy.message.HttpRequestMessage;
import com.nhnacademy.message.TCPResponseMessage;
import com.nhnacademy.node.inout.InOutNode;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EmsNode extends InOutNode {

    JSONParser jsonParser = new JSONParser();

    public EmsNode(int inCount, int outCount) {
        super(inCount, outCount);
    }
    

    @Override
    protected void main() {
        if ((getInputWire(0) != null) && getInputWire(0).hasMessage()) {

            log.info("EmsNode start");

            HttpRequestMessage message = (HttpRequestMessage) getInputWire(0).get();

            try (Socket socket = new Socket("ems.nhnacademy.com", 1880);
                    BufferedWriter writer =
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                writer.write(
                        message.getHttpRequest() + System.lineSeparator() + System.lineSeparator());
                writer.flush();

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {

                    response.append(line).append("\r\n");
                }

                String responseString = response.toString();
                TCPResponseMessage outMessage = new TCPResponseMessage(message.getSenderId(), responseString);
                output(outMessage);
                
                // String[] parts = responseString.split("\r\n\r\n");

                // if (parts.length == 2) {
                //     String headers = parts[0];
                //     String body = parts[1];

                //     // JSON 파싱
                //     JSONArray jsonArray = (JSONArray) jsonParser.parse(body);

                //     // 첫 번째 객체의 id추출
                //     JSONObject firstDevice = (JSONObject) jsonArray.get(0);
                //     String firstId = (String) firstDevice.get("id");

                //     // "dev/{id}" 형식의 새로운 요청 문자열 생성
                //     String newRequest = "dev/" + firstId;

                //     // 새로운 요청을 사용하여 다른 작업 수행
                //     System.out.println("새 Path: " + newRequest);
                // }
                log.info("EmsNode end");
            } catch (Exception e) {
                log.error(e);
            }
        }
    }
}
