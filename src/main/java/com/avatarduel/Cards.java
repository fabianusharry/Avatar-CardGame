package com.avatarduel;

import com.avatarduel.model.*;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Cards { //implement Singleton Design Pattern
    private static Cards instance = null; //Singleton attribute

    // Cards attribute
    private List<Card> listCards;
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "";
    private static final String SKILL_POWER_UP_CSV_FILE_PATH = "";

    private Cards() {
         listCards = new ArrayList<Card>();
    }

    public static Cards getInstance() {
        if (instance == null) {
            instance = new Cards();
        }
        return instance;
    }

    public void loadAllCards() throws IOException, URISyntaxException {
        loadCards(LAND_CSV_FILE_PATH);
        loadCards(CHARACTER_CSV_FILE_PATH);
        loadCards(SKILL_AURA_CSV_FILE_PATH);

    }

    public void loadCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            listCards.add(new Land(row[1], row[3], Element.valueOf(row[2])));
//            System.out.println(row[1]);
        }
    }
}
