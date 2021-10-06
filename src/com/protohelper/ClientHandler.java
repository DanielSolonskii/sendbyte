package com.protohelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

// Обработать входящие подключения
public class ClientHandler implements Runnable {
    public Socket socket;
    public ObjectInputStream inStream;
    public ObjectOutputStream outStream;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    public void run() {
        long hashCRC;

            try {
                outStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }



            while(true) {
                Packet recvPacket = null;
                try {
                    recvPacket = (Packet) inStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                /*
                Вывод полученных значений
                System.out.println("Message =" + recvPacket.message);
                System.out.println("Lon =" + recvPacket.lon);
                System.out.println("Lat =" + recvPacket.lat);
                System.out.println("crccod =" + recvPacket.crccod);
                System.out.println("id =" + recvPacket.id);
                System.out.println("idDevice =" + recvPacket.idDevice);
                 */

                hashCRC = CRCcheck.getCRC(recvPacket.message.getBytes(StandardCharsets.UTF_8), recvPacket.message.getBytes(StandardCharsets.UTF_8).length);
                // Верефикация CRC и отправка пакета о принятии
                if (hashCRC == recvPacket.crccod) {
                    //System.out.println("CRC HASH VERIFIED! CRC IS: " + hashCRC);
                    System.out.println(" ID Packet Accepted: " + recvPacket.id + "\n ID Device Accepted: " + recvPacket.idDevice );

                    Responce respPacket = new Responce(true);
                    try {
                        outStream.writeObject(respPacket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                  System.err.println("CRC FAIL");
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
