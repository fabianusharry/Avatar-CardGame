package com.avatarduel.exceptions.hand;

/**
 * Exception yang berkaitan dengan hand card player
 */
public class HandOperationException extends Exception {
    public HandOperationException(String message) {
        super("Kartu tidak dapat diambil:\n"+message);
    }
}
