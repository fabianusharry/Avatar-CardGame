package com.avatarduel.exceptions.field;

/**
 * Exception saat memilih kartu yang salah untuk diserang
 */
public class InvalidAttackException extends Exception {
    public InvalidAttackException() {super("Attack value is not sufficient");}
}
