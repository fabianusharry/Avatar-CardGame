package com.avatarduel.model;

public class SkillAura extends Skill {
    private int attack;
    private  int defense;

    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    @Override
    public String effect() { return "Aura"; } //masih bingung
}
