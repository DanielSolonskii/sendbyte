package com.protohelper;

import java.util.concurrent.TimeUnit;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        new Client();
    }

    public Client() throws Exception {
        Socket socket = new Socket("127.0.0.1", Server.PORT);

        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        for(int i = 0; i < 50; i++) {
            for(int a = 0; a< 255; a++){
                Packet packet = new Packet("Hi!", 5.4395498, 10.43894347, 4387483, a, i);
                TimeUnit.SECONDS.sleep(2);
                outStream.writeObject(packet);
            }

        }


        Packet recvPacket = (Packet)inStream.readObject();
        System.out.println("Message =" +recvPacket.message);
        System.out.println("Lon =" +recvPacket.lon);
        System.out.println("Lat =" +recvPacket.lat);
        System.out.println("crccod =" +recvPacket.crccod);
        outStream.close();
        socket.close();
    }
}
// Создать 50 потоков и внутри потока отправлять 255 пакетов