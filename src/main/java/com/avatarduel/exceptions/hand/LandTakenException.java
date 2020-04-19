package com.avatarduel.exceptions.hand;

/**
 * Exception saat mencoba memanggil land card lebih dari
 * satu kali di ronde yang sama
 */
public class LandTakenException extends HandOperationException {
    public LandTakenException() {
        super("Land hanya bisa diambil 1x");
    }
}
