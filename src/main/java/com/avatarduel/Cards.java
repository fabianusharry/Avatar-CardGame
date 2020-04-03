package com.avatarduel;

import com.avatarduel.model.*;
import com.avatarduel.model.Character;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cards { //implement Singleton Design Pattern
    private static Cards instance = null; //Singleton attribute

    // Cards attribute
    private List<Land> listLand;
    private List<Character> listCharacter;
    private List<SkillAura> listSkillAura;
    private List<SkillDestroy> listSkillDestroy;
    private List<SkillPowerUp> listSkillPowerUp;

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "";
    private static final String SKILL_POWER_UP_CSV_FILE_PATH = "";

    private Cards() throws IOException, URISyntaxException {
        listLand = new ArrayList<>();
        listCharacter = new ArrayList<>();
        listSkillAura = new ArrayList<>();
        listSkillDestroy = new ArrayList<>();
        listSkillPowerUp = new ArrayList<>();
        loadAllCards();
    }

    private void loadAllCards() throws IOException, URISyntaxException {
        loadLandCards(LAND_CSV_FILE_PATH);
        loadCharacterCards(CHARACTER_CSV_FILE_PATH);
        loadSkillAuraCards(SKILL_AURA_CSV_FILE_PATH);
//        loadSkillDestroyCards(SKILL_AURA_CSV_FILE_PATH);
//        loadSkillPowerUpCards(SKILL_AURA_CSV_FILE_PATH);
    }

    private void loadLandCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            listLand.add(new Land(row[1], row[3], Element.valueOf(row[2])));
        }
    }

    private void loadCharacterCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            listCharacter.add(new Character(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }
    }

    private void loadSkillAuraCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            listSkillAura.add(new SkillAura(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[5])));
        }
    }

    private void shuffleAll() {
        Collections.shuffle(listLand);
        Collections.shuffle(listCharacter);
        Collections.shuffle(listSkillAura);
//        Collections.shuffle(listSkillDestroy);
//        Collections.shuffle(listSkillPowerUp);
    }

    public static Cards getInstance() throws IOException, URISyntaxException {
        if (instance == null) {
            instance = new Cards();
        }
        return instance;
    }

    public List<Card> makeDeck() {
//        int nDeck = new Random().nextInt(21) + 40; //random nDeck 40-60
        int nDeck = new Random().nextInt(30) + 10; //SEMENTARA (JUMLAH KARTU KURANG)
        int nCharacter, nLand, nSkill; // land : character : skill = 2 : 2 : 1
        nLand = nCharacter = 2*nDeck/5;
        nSkill = nDeck - (nLand + nCharacter); // skill ada 3 (baginya gimana)
        shuffleAll();
        List<Card> result = new ArrayList<Card>(listLand.subList(0, nLand));
        result.addAll(listCharacter.subList(0, nCharacter));
        result.addAll(listSkillAura.subList(0, nSkill));
        Collections.shuffle(result);
//        System.out.println("Check nDeck (class Cards) "+ nDeck + " " + nLand + " " + nCharacter + " " + nSkill + " " + result.size());
        return result;
    }
}
