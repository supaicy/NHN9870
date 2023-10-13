package com.nhnacademy.node.in;

import com.nhnacademy.message.StringMessage;

import java.util.Scanner;

public class StdInNode extends InputNode {
    Scanner scanner;

    public StdInNode(int count){
        super(count);
    }

    @Override
    protected void preprocess(){
        scanner = new Scanner(System.in);
    }

    @Override
    protected void main(){
        String line = scanner.nextLine();
        StringMessage message = new StringMessage(line);
        output(message);
    }

    @Override
    protected void postprocess(){
        scanner = null;
    }

}
