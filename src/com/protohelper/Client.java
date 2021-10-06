package com.protohelper;

/*
 Запускаем определённое количество потоков NewThread
*/
public class Client {
    public static void main(String[] args) {

        int argsize = 5; // количество потоков
        Thread[] myThreads = new Thread[argsize];
        for(int i = 0; i<argsize ; i++){ // Запускаем в цикле потоки в определённом количестве(argsize)
            myThreads[i] = new NewThread(i);
            myThreads[i].start();
        }
    }
    public Client() {
         // Не нужно
    } }