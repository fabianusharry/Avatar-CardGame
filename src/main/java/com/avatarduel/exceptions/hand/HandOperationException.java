package com.avatarduel.exceptions.hand;

public class HandOperationException extends Exception {
    public HandOperationException(String message) {
        super("Kartu tidak dapat diambil:\n"+message);
    }
}
