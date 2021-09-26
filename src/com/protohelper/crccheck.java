package com.protohelper;

import java.util.zip.CRC32;

public class crccheck {
    public static int getCRC(byte[] buf, int len ) {
        int crc =  0xFFFF;
        int val = 0;

        for (int pos = 0; pos < len; pos++) {
            crc ^= (int)(0x00ff & buf[pos]);

            for (int i = 8; i != 0; i--) {
                if ((crc & 0x0001) != 0) {
                    crc >>= 1;
                    crc ^= 0xA001;
                }
                else
                    crc >>= 1;
            }
        }
        val =  (crc & 0xff) << 8;
        val =  val + ((crc >> 8) & 0xff);
        System.out.printf("Calculated a CRC of 0x%x, swapped: 0x%x\n", crc, val);
        return val;

    }   // end GetCRC
}
