package com.avatarduel.model;

public class SkillDestroy extends Skill {
    public SkillDestroy(String name, String description, Element element) {
        super(name, description, element);
    }
    @Override
    public String effect() { return "Destroy"; } //masih bingung
}
