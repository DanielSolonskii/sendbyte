package com.protohelper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws Exception {
        for(int i = 0; i<15 ; i++){
            NewThread t = new NewThread(i);
            t.start();
        }

    }
    public Client() throws Exception {
        //for (int i = 0; i < 255; i++) {
       // Socket socket = new Socket("127.0.0.1", 4041);
      //  ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
       // ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        // цикл отправки пакетов
    } }


// Создать 50 потоков и внутри потока отправлять 255 пакетов