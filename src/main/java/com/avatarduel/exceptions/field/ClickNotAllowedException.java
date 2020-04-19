/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.exceptions.field;

/**
 *
 * Exception saat me-clink object yang tidak diperbolehkan
 */
public class ClickNotAllowedException extends Exception{
    public ClickNotAllowedException(String exception){
        super(exception);
    }
}
