package com.protohelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class NewThread extends Thread {
    int i = 0;
    public NewThread(int i){
        this.i = i;
    }
    public void run() {
        long startTime = System.currentTimeMillis();

        System.out.println(this.getName() + ": New Thread is running... ID: " + i + "Start Time: " + startTime);
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 4041);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectOutputStream outStream = null;
        try {
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectInputStream inStream = null;
        try {
            inStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // цикл отправки пакетов


        for (int a = 0; a < 255; a++) {
            Packet packet = new Packet("Hi!", 5.4395498, 10.43894347, 4387483, a, i);

            // CRC
            packet.crccod = CRCcheck.getCRC(packet.message.getBytes(StandardCharsets.UTF_8), packet.message.getBytes(StandardCharsets.UTF_8).length);
            try {
                outStream.writeObject(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Responce recvPacket = null;
            try {
                recvPacket = (Responce) inStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("PACKET SENT id: " + packet.id );
            System.out.println("Packet is Recieved: " + recvPacket.isAccepted);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
