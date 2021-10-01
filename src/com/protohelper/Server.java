package com.protohelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static int PORT = 4041;
    public static void main(String[] args) throws Exception{
        new Server();

    }

    public Server() throws Exception{

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running on this PORT: " + PORT);
        while(true){
            Socket socket = serverSocket.accept(); // Слушаем файлы
            Runnable connectionHandler = new ClientHandler(socket);
            new Thread(connectionHandler).start();
        }
        }
    }

