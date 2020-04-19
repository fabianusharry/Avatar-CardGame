package com.avatarduel.exceptions;

public class EndGameException extends Exception {
    public EndGameException(String message) {
        super("Permainan selesai\n"+message);
    }
}
