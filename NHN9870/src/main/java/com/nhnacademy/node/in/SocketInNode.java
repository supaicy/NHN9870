package com.nhnacademy.node.in;

import com.nhnacademy.message.TCPRequestMessage;
import lombok.extern.log4j.Log4j2;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Log4j2
public class SocketInNode extends InputNode {

    public static class Handler implements Runnable {
        static int count = 0;
        String id;
        Thread thread;
        Socket socket;
        BufferedInputStream inputStream;
        BufferedOutputStream outputStream;
        BufferedWriter bufferedWriter;
        byte[] buffer;
        BiConsumer<byte[], Integer> callback;
        SocketInNode socketInNode;

        public Handler(Socket socket, SocketInNode socketInNode){
            id = String.valueOf(System.currentTimeMillis()) + (++count);
            thread = new Thread(this);
            buffer = new byte[100000];
            this.socketInNode = socketInNode;
            this.socket = socket;
        }

        public String getId(){
            return id;
        }

        public void setCallback(BiConsumer<byte[], Integer> callback){
            this.callback = callback;
        }

        public void start(){
            thread.start();
        }

        public void stop(){
            try{
                inputStream.close();
            } catch (IOException ignore){

            } finally {
                thread.interrupt();
            }
        }

        public BufferedOutputStream getOutputStream() {
            return outputStream;
        }
        
        public BufferedWriter getBufferedWriter() {
            return bufferedWriter;
        }
        public void write(byte[] data){
            try{
                outputStream.write(data);
                outputStream.flush();
            } catch(IOException ignore){

            }
        }

        @Override
        public void run(){
            try{
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                inputStream = new BufferedInputStream(socket.getInputStream());
                outputStream = new BufferedOutputStream(socket.getOutputStream());
                

                while(!Thread.currentThread().isInterrupted()){
                    int length = inputStream.read(buffer);
                    callback.accept(buffer, length);
                }

                inputStream.close();
                outputStream.close();

            } catch (IOException e){

            } finally {
                inputStream = null;
                outputStream = null;
            }
        }
    }

    int port = 8080;
    ServerSocket serverSocket;
    protected static Map<String, Handler> handlerMap = new HashMap<>();

    public static Handler getHandler(String handlerId){
        return handlerMap.get(handlerId);
    }

    public SocketInNode(){
        super(1);
    }

    @Override
    protected void preprocess() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void main(){
        try{
            
            Socket socket = serverSocket.accept();
            log.info("socketAccept()");
            Handler handler = new Handler(socket, this);
            handler.setCallback((data, length) ->{
                output(new TCPRequestMessage(handler.getId(), data, length));
            });
            handler.start();
            handlerMap.put(handler.getId(), handler);
        } catch(IOException e){
            stop();
        }
    }

}
