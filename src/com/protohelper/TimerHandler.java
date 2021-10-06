package com.protohelper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerHandler extends Thread  {
    Timer timer;
    ArrayList<ClientHandler> _clients;
    public TimerHandler(ArrayList<ClientHandler> clients) {
        _clients = clients;
        timer = new Timer();
        timer.schedule(new RemindTask(),
                0,        //initial delay
                1*3000);  //subsequent rate
    }

    class RemindTask extends TimerTask {
        public void run() {
                System.out.println(new Date().getDate() + ": Clients connected: " + _clients.size() );
    }
    }}
