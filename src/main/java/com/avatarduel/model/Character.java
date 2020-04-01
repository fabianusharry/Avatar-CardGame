package com.avatarduel.model;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Character {
    private String name;
    private String description;
    private Element element;
    private int attack;
    private int defense;
    private int power;
//    private Image img;
    private String imgPath;

    public Character() {
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
        this.attack = this.defense = this.power = 0;
        imgPath = "card/image/no-image.png";
    }

    public Character(String name, String description, Element element, int attack, int defense, int power, String imgPath) {
        this.name = name;
        this.description = description;
        this.element = element;
        this.attack = attack;
        this.defense = defense;
        this.power = power;
//        System.out.println(getClass().getResource(imgPath).toString());
//        img = new Image(getClass().getResource(imgPath).toString());
        this.imgPath = imgPath;
//        img = new Image(getClass().getResource("card/image/character/Katara.png").toString());
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Element getElement() { return element; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getPower() { return power; }
    public String getImgPath() { return imgPath; }
}
