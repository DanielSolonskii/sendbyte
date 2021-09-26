package com.protohelper;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int PORT = 4040;

    public static void main(String[] args) throws Exception{
        new Server();
    }

    public Server() throws Exception{
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is running on this PORT: " + PORT);
        Socket socket = serverSocket.accept(); // Слушаем файлы

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        while (true) {
            Packet recvPacket = (Packet) inStream.readObject();

            System.out.println("Message =" +recvPacket.message);
            System.out.println("Lon =" +recvPacket.lon);
            System.out.println("Lat =" +recvPacket.lat);
            System.out.println("crccod =" +recvPacket.crccod);
            System.out.println("id =" +recvPacket.id);
            System.out.println("idDevice =" +recvPacket.idDevice);
            Responce respPacket = new Responce(true);
            outStream.writeObject(respPacket);

        }

       /* if(recvPacket.message.equals("Hello!")){
            Packet packet = new Packet("Hi from server",7.4395498, 3.43894347, 4387483,244);
            outStream.writeObject(packet);
        }*/
        // serverSocket.close();

    }
}
