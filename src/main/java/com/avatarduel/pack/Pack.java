package com.avatarduel.pack;

import com.avatarduel.model.card.*;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.effect.*;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

public class Pack {
    // CardPack attribute
    private LandPack lands;
    private CharacterPack characters;
    private SkillPack skills;

    public Pack() {
        lands = new LandPack();
        characters = new CharacterPack();
        skills = new SkillPack();
    }

    public void shuffle() {
        lands.shuffle();
        characters.shuffle();
        skills.shuffle();
    }

    public LandPack getLands() {
        return lands;
    }

    public CharacterPack getCharacters() {
        return characters;
    }

    public SkillPack getSkills() {
        return skills;
    }

    public void setLands(LandPack lands) {
        this.lands = lands;
    }

    public void setCharacters(CharacterPack characters) {
        this.characters = characters;
    }

    public void setSkills(SkillPack skills) {
        this.skills = skills;
    }
}
