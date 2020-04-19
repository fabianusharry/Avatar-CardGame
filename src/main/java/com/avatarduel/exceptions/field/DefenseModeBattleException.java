/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.exceptions.field;

/**
 *
 * Exception saat memilih kartu dengan posisi bertahan
 * pada battle phase
 */
public class DefenseModeBattleException extends Exception{
    public DefenseModeBattleException(){
        super("Card is in defense mode");
    }
}
