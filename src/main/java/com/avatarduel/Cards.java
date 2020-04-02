package com.avatarduel;

import com.avatarduel.model.Character;
import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.model.Skill;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Character> characterCards = new ArrayList<Character>();
    private List<Land> landCards = new ArrayList<Land>();
    private List<Skill> skillCards = new ArrayList<Skill>();
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "";
    private static final String SKILL_POWER_UP_CSV_FILE_PATH = "";

    public void loadCards() throws IOException, URISyntaxException {
        File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader landReader = new CSVReader(landCSVFile, "\t");
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");

        landReader.setSkipHeader(true); characterReader.setSkipHeader(true);
        List<String[]> landRows = landReader.read();
        for (String[] row : landRows) {
            landCards.add(new Land(row[1], row[3], Element.valueOf(row[2])));
        }

        List<String[]> characterRows = characterReader.read();
        for (String[] row : characterRows) {
            characterCards.add(new Character(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7])));
        }

        //baca kartu skill
    }
}
