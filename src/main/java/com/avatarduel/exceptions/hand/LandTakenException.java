package com.avatarduel.exceptions.hand;

public class LandTakenException extends HandOperationException {
    public LandTakenException() {
        super("Land hanya bisa diambil 1x");
    }
}
