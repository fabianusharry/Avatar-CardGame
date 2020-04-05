package com.avatarduel;

import com.avatarduel.model.Card.Card;
import com.avatarduel.model.Card.Character;
import com.avatarduel.model.Card.Land;
import com.avatarduel.model.Card.Effect.*;
import com.avatarduel.model.Element;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;
// import java.util.Random;

public class CardPack { //implement Singleton Design Pattern
    private static CardPack instance = null; //Singleton attribute

    // Cards attribute
    private List<Card> cards;
    // private List<Land> listLand;
    // private List<Character> listCharacter;
    // private List<Aura> listAura;
    // private List<Destroy> listDestroy;
    // private List<PowerUp> listPowerUp;

    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    // private static final String SKILL_DESTROY_CSV_FILE_PATH = "";
    // private static final String SKILL_POWER_UP_CSV_FILE_PATH = "";

    private CardPack() throws IOException, URISyntaxException {
        
        // listLand = new ArrayList<>();
        // listCharacter = new ArrayList<>();
        // listAura = new ArrayList<>();
        // listDestroy = new ArrayList<>();
        // listPowerUp = new ArrayList<>();
        this.cards = new ArrayList<>();
        loadAllCards();
    }

    private void loadAllCards() throws IOException, URISyntaxException {
        loadLandCards(LAND_CSV_FILE_PATH);
        loadCharacterCards(CHARACTER_CSV_FILE_PATH);
        loadAuraCards(SKILL_AURA_CSV_FILE_PATH);
//        loadDestroyCards(SKILL_AURA_CSV_FILE_PATH);
//        loadPowerUpCards(SKILL_AURA_CSV_FILE_PATH);
    }

    private void loadLandCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.cards.add(new Land(row[1], row[3], Element.valueOf(row[2])));
        }
    }

    private void loadCharacterCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.cards.add(new Character(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }
    }

    private void loadAuraCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(getClass().getResource(path).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.cards.add(new Aura(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[5])));
        }
    }

    // private void shuffleAll() {
        // Collections.shuffle(listLand);
        // Collections.shuffle(listCharacter);
        // Collections.shuffle(listAura);
//        Collections.shuffle(listDestroy);
//        Collections.shuffle(listPowerUp);
    // }

    public List<Card> cards()
    {
        return this.cards;
    }

    public static CardPack getInstance() throws IOException, URISyntaxException {
        if (instance == null) {
            instance = new CardPack();
        }
        return instance;
    }

//     public List<Card> makeDeck() {
// //        int nDeck = new Random().nextInt(21) + 40; //random nDeck 40-60
//         int nDeck = new Random().nextInt(30) + 10; //SEMENTARA (JUMLAH KARTU KURANG)
//         int nCharacter, nLand, nSkill; // land : character : skill = 2 : 2 : 1
//         nLand = nCharacter = 2*nDeck/5;
//         nSkill = nDeck - (nLand + nCharacter); // skill ada 3 (baginya gimana)
//         shuffleAll();
//         List<Card> result = new ArrayList<Card>(listLand.subList(0, nLand));
//         result.addAll(listCharacter.subList(0, nCharacter));
//         result.addAll(listAura.subList(0, nSkill));
//         Collections.shuffle(result);
// //        System.out.println("Check nDeck (class Cards) "+ nDeck + " " + nLand + " " + nCharacter + " " + nSkill + " " + result.size());
//         return result;
//     }
}
