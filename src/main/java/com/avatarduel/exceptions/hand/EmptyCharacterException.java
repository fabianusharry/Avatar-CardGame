package com.avatarduel.exceptions.hand;

public class EmptyCharacterException extends HandOperationException {
    public EmptyCharacterException() {
        super("Tidak ada kartu karakter");
    }
}
