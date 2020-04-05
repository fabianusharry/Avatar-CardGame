package com.avatarduel;

import com.avatarduel.model.Card.*;
import com.avatarduel.model.Card.Character;
import com.avatarduel.model.Card.Effect.*;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
 import java.util.Random;

public class CardPack { //implement Singleton Design Pattern
    private static CardPack instance = null; //Singleton attribute

    // CardPack attribute
    private List<Land> lands;
    private List<Character> characters;
    private List<Skill> skills;

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    // private static final String SKILL_DESTROY_CSV_FILE_PATH = "";
    // private static final String SKILL_POWER_UP_CSV_FILE_PATH = "";

    private CardPack() throws IOException, URISyntaxException {
        lands = new ArrayList<>();
        characters = new ArrayList<>();
        skills = new ArrayList<>();
        loadAllCards();
    }

    private void loadAllCards() throws IOException, URISyntaxException {
        loadLandCards(LAND_CSV_FILE_PATH);
        loadCharacterCards(CHARACTER_CSV_FILE_PATH);
        loadSkillAuraCards(SKILL_AURA_CSV_FILE_PATH);
//        loadSkillDestroyCards(SKILL_DESTROY_CSV_FILE_PATH);
//        loadSkillPowerUpCards(SKILL_POWER_UP_CSV_FILE_PATH);
    }

    private void loadLandCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.lands.add(new Land(row[1], row[3], Element.valueOf(row[2])));
        }
    }

    private void loadCharacterCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.characters.add(new Character(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }
    }

    private void loadSkillAuraCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.skills.add(new Aura(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[5])));
        }
    }

    public void shuffle() {
        Collections.shuffle(lands);
        Collections.shuffle(characters);
        Collections.shuffle(skills);
    }

    public static CardPack getInstance() throws IOException, URISyntaxException {
        if (instance == null) {
            instance = new CardPack();
        }
        return instance;
    }

    public List<Land> getLands() {
        return lands;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Skill> getSkills() {
        return skills;
    }
}
