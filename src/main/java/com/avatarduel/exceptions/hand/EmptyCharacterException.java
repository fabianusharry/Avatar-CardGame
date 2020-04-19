package com.avatarduel.exceptions.hand;

/**
 * Exception saat mencoba menggunakan kartu skill
 * namun tidak memiliki kartu karakter di field
 */
public class EmptyCharacterException extends HandOperationException {
    public EmptyCharacterException() {
        super("Tidak ada kartu karakter");
    }
}
