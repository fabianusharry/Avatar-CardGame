package com.avatarduel.pack;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.card.Element;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CharacterPack.java
 * A Class to build Pack that contains Character cards
 */
public class CharacterPack {
    private List<Character> characters;
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

    /**
     * Create a new list to contain character cards
     */
    public CharacterPack() {
        this.characters = new ArrayList<>();
    }

    /**
     * Get list character cards
     * @return list character cards
     */
    public List<Character> getCards() {
        return characters;
    }

    /**
     * Load cards from given CSV in AvatarDuel resources
     * @return CharacterPack
     * @throws IOException exception when initiating another class attributes
     * @throws URISyntaxException exception when the URI syntax is incorrect
     */
    public CharacterPack loadCards() throws IOException, URISyntaxException {
        File csvFile = new File(AvatarDuel.class.getResource(CHARACTER_CSV_FILE_PATH).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.characters.add(new Character(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), row[4]));
        }
        return this;
    }

    /**
     * Shuffle cards
     */
    public void shuffle() {
        Collections.shuffle(characters);
    }
}
