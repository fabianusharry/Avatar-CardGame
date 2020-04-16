package com.avatarduel.exceptions;

public class PlayerNameSameException extends Exception {
    public PlayerNameSameException() {
        super("Player name should not be sama!");
    }
}
