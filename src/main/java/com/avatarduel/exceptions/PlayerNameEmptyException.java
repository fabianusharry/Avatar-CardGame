package com.avatarduel.exceptions;

public class PlayerNameEmptyException extends Exception {
    public PlayerNameEmptyException() {
        super("Player name should not be empty!");
    }
}
