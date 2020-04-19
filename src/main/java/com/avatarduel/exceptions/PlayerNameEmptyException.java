package com.avatarduel.exceptions;

/**
 * Exception saat masukan nama player ada yang kosong
 */
public class PlayerNameEmptyException extends Exception {
    public PlayerNameEmptyException() {
        super("Player name should not\n be empty!");
    }
}
