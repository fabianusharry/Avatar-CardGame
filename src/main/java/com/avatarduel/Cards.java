package com.avatarduel;

import com.avatarduel.model.*;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.lang.Character;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Cards { //implement Singleton Design Pattern
    private static Cards instance = null; //Singleton attribute

    // Cards attribute
    private List<Card> listLand;
    private List<Card> listCharacter;
    private List<Card> listSkill;

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "";
    private static final String SKILL_POWER_UP_CSV_FILE_PATH = "";

    private Cards() throws IOException, URISyntaxException {
        listLand = new ArrayList<>();
        listCharacter = new ArrayList<>();
        listSkill = new ArrayList<>();
        loadAllCards();
    }

    private void loadAllCards() throws IOException, URISyntaxException {
        loadCards(listLand, LAND_CSV_FILE_PATH);
        loadCards(listCharacter, CHARACTER_CSV_FILE_PATH);
        loadCards(listSkill, SKILL_AURA_CSV_FILE_PATH);
    }

    private void loadCards(List<Card> listCards, String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            listCards.add(new Land(row[1], row[3], Element.valueOf(row[2])));
        }
    }

    private void shuffleAll() {
        shuffle(listLand);
        shuffle(listCharacter);
        shuffle(listSkill);
    }

    private void shuffle(List<Card> listCards) {
        Collections.shuffle(listCards);
    }

    public static Cards getInstance() throws IOException, URISyntaxException {
        if (instance == null) {
            instance = new Cards();
        }
        return instance;
    }

    public List<Card> makeDeck() {
//        int nDeck = new Random().nextInt(21) + 40; //random nDeck 40-60
        int nDeck = new Random().nextInt(30) + 10; //!!SEMENTARA!! (JUMLAH KARTU KURANG)
        int nCharacter, nLand, nSkill; // land : character : skill = 2 : 2 : 1
        nLand = nCharacter = 2*nDeck/5;
        nSkill = nDeck - (nLand + nCharacter);
        shuffleAll();
        List<Card> result = new ArrayList<Card>(listLand.subList(0, nLand));
        result.addAll(listCharacter.subList(0, nCharacter));
        result.addAll(listSkill.subList(0, nSkill));
        System.out.println("Check nDeck (class Cards) "+ nDeck + " " + nLand + " " + nCharacter + " " + nSkill + " " + result.size());
        return result;
    }
}
