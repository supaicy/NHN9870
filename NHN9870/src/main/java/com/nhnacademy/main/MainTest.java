package com.nhnacademy.main;

import com.nhnacademy.node.http.EmsNode;
import com.nhnacademy.node.http.RequestMsgGeneratorNode;
import com.nhnacademy.node.http.ResponseMsgGeneratorNode;
import com.nhnacademy.node.http.ResponseParseNode;
import com.nhnacademy.node.in.SocketInNode;
import com.nhnacademy.node.inout.RequestParseNode;
import com.nhnacademy.node.out.SocketOut;
import com.nhnacademy.wire.Wire;

public class MainTest {
    
    public static void main(String[] args) {


        SocketInNode in = new SocketInNode();
        RequestParseNode req = new RequestParseNode(1,1);
        RequestMsgGeneratorNode reqM = new RequestMsgGeneratorNode(1,1);
        EmsNode emsNode = new EmsNode(1,1);
        ResponseParseNode responseParseNode = new ResponseParseNode(1,1);
        ResponseMsgGeneratorNode responseMsgGeneratorNode = new ResponseMsgGeneratorNode(1,1);
        SocketOut socketOutNode = new SocketOut(1);

        Wire wire = new Wire();
        Wire parseNodeToReqGeneNode = new Wire();
        Wire toEmsWire = new Wire();
        Wire toResponseParse = new Wire();
        Wire toResGenNode = new Wire();
        Wire toSocketOut = new Wire();

        in.connectOutputWire(0, wire);
        req.connectInputWire(0, wire);

        req.connectOutputWire(0,parseNodeToReqGeneNode);
        reqM.connectInputWire(0, parseNodeToReqGeneNode);

        reqM.connectOutputWire(0, toEmsWire);
        emsNode.connectInputWire(0, toEmsWire);

        emsNode.connectOutputWire(0, toResponseParse);
        responseParseNode.connectInputWire(0, toResponseParse);

        responseParseNode.connectOutputWire(0, toResGenNode);
        responseMsgGeneratorNode.connectInputWire(0, toResGenNode);

        responseMsgGeneratorNode.connectOutputWire(0, toSocketOut);
        socketOutNode.connectInputWire(0, toSocketOut);
       
        in.start();
        req.start();
        reqM.start();
        emsNode.start();
        responseParseNode.start();
        responseMsgGeneratorNode.start();
        socketOutNode.start();
    }
}
