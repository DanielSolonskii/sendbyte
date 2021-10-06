package com.protohelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;

public class Server {
    public ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
    public static int PORT = 4041;
    public static void main(String[] args) throws Exception{
        new Server();

    }

    public Server() throws Exception{
        TimerHandler timerhandler = new TimerHandler(clients);
        timerhandler.start();
        ServerSocket serverSocket = new ServerSocket(PORT); // Создаём серверный сокет
        System.out.println("Server is running on this PORT: " + PORT);
        while(true){

            Socket socket = serverSocket.accept(); // Слушаем файлы
            // Runnable connectionHandler = new ClientHandler(socket);
            ClientHandler client = new ClientHandler(socket);
            clients.add(client);
            new Thread(client).start(); // Запускаем поток
            System.out.println("Clients connected:" + clients.size());
        }

        }
    }

