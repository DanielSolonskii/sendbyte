package com.protohelper;

import java.io.Serializable;

public class Packet implements Serializable {
        String message;
        double lon;
        double lat;
        long crccod;
        int id;
        long idDevice;
        public Packet(String message, double lon, double lat, long crccod, int id,long idDevice ){
                this.message = message;
                this.lon = lon;
                this.lat = lat;
                this.crccod = crccod;
                this.id = id;
                this.idDevice = idDevice;
        }
}
