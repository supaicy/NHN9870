package com.nhnacademy.node.inout;

import com.nhnacademy.message.Message;
import com.nhnacademy.message.TCPRequestMessage;
import com.nhnacademy.message.TCPResponseMessage;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Log4j2
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

    int port = 1880;
    ServerSocket serverSocket;
    Map<String, Handler> handlerMap;
    Thread receiverThread;

    public Server(String id) {
        super(id, 1, 1);
        handlerMap = new HashMap<>();
    }

    Handler getHandler(String id) {
        return handlerMap.get(id);
    }

    void preprocess() {
        try {
            serverSocket = new ServerSocket(port);
            receiverThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        for (int i = 0; i < getInputWireCount(); i++) {
                            if((getInputWire(i) != null) && getInputWire(i).hasMessage()) {
                                Message message = getInputWire(i).get();
                                if (message instanceof TCPResponseMessage) {
                                    TCPResponseMessage responseMessage = (TCPResponseMessage) message;
                                    Handler handler = getHandler(responseMessage.getSenderId());

                                    handler.write(responseMessage.getPayload());
                                }
                            }
                        }
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            receiverThread.start();
        } catch (IOException e) {
            log.error(e.getMessage());
            stop();
        }
    }

    void process() {
        try {
            Socket socket = serverSocket.accept();
            Handler handler = new Handler(socket, this);
            handler.setCallback((data, length) -> {
                output(new TCPRequestMessage(handler.getId(), data, length));
            });
            handler.start();
            handlerMap.put(handler.getId(), handler);

        } catch (IOException e) {
            log.error(e.getMessage());
            stop();
        }
    }
}
