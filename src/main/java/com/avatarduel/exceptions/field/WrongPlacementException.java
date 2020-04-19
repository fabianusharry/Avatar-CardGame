/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.exceptions.field;

/**
 *
 * Exception saat mencoba meletakkan kartu ditempat yang salah
 */
public class WrongPlacementException extends Exception{
    public WrongPlacementException(String exception){
        super(exception);
    }
    
}
