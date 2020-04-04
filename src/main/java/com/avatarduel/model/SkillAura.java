package com.avatarduel.model;

public class SkillAura extends Skill {
    private int attack;
    private  int defense;
    private int power;

    public SkillAura(String name, String description, Element element, int attack, int defense, int power) {
        super(name, description, element);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getPower() { return power; }

    @Override
    public String effect() { return "Aura"; } //masih bingung

    @Override
    public int activate(String attr) {

        return -1;
    }
}
