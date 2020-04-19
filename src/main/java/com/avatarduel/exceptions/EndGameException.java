package com.avatarduel.exceptions;

/**
 * Exception saat game berakhir
 */
public class EndGameException extends Exception {
    public EndGameException(String message) {
        super("Permainan selesai\n"+message);
    }
}
