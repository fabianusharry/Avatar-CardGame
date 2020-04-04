package com.avatarduel.model;

public class SkillPowerUp extends Skill {
    public SkillPowerUp(String name, String description, Element element) {
        super(name, description, element);
    }
    @Override
    public String effect() { return "PowerUp"; } //masih bingung

    @Override
    public int activate(String attr) {

        return -1;
    }
}
