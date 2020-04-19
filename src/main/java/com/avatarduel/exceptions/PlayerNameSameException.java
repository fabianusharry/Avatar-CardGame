package com.avatarduel.exceptions;

/**
 * Exception saat masukan nama player1 dan player2 sama
 */
public class PlayerNameSameException extends Exception {
    public PlayerNameSameException() {
        super("Player name should not\n be same");
    }
}
