package com.protohelper;

import java.io.Serializable;
// Отправка ответа на клиент о полученности данных и проверенном CRC16
public class Responce implements Serializable {
    boolean isAccepted;
    public Responce(boolean isAccepted){
        this.isAccepted = isAccepted;
    }
}
