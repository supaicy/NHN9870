package com.nhnacademy.node.inout;

import lombok.Getter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.BiConsumer;


public class Server extends InOutNode{
    static class Handler implements Runnable {
        @Getter
        String id;
        Thread thread;
        Socket socket;
        byte[] buffer;
        BufferedInputStream in;
        BufferedOutputStream out;
        BiConsumer<byte[],Integer> callback;
        Server server;

        public Handler(Socket socket, Server server) {
            id = String.valueOf(socket.getLocalPort()+System.currentTimeMillis());
            thread = new Thread(this);
            buffer = new byte[100000];
            this.server = server;
            this.socket = socket;
        }

        public void setCallback(BiConsumer<byte[], Integer> callback) {
            this.callback = callback;
        }

        public void start() {
            thread.start();
        }

        public void stop() {
            thread.interrupt();
        }

        public void write(byte[] data) {
            try {
                out.write(data);
                out.flush();
            } catch (IOException e) {}
        }

        @Override
        public void run() {
            try {
                in = new BufferedInputStream(socket.getInputStream());
                out = new BufferedOutputStream(socket.getOutputStream());

                while (!Thread.currentThread().isInterrupted()) {
                    int length = in.read(buffer);
                    callback.accept(buffer,length);
                }
                in.close();
                out.close();
            }  catch (IOException e) {

            } finally {
                in = null;
                out = null;
            }
        }
    }


}
