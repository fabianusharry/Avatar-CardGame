package com.avatarduel.exceptions;

public class HandOperationException extends Exception {
    public HandOperationException(String message) {
        super("Kartu tidak dapat diambil:\n"+message);
    }
}
