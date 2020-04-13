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

public class CharacterPack {
    private List<Character> characters;
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";

    public CharacterPack() {
        this.characters = new ArrayList<>();
    }

    public List<Character> getCards() {
        return characters;
    }

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

    public void shuffle() {
        Collections.shuffle(characters);
    }
}
