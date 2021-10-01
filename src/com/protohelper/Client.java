package com.protohelper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws Exception {
        // Запускаем потоки в определённом количестве(i)
        for(int i = 0; i<15 ; i++){
            NewThread t = new NewThread(i);
            t.start();
        }
    }
    public Client() throws Exception {
         // Не нужно
    } }