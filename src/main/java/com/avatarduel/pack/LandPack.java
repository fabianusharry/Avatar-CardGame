package com.avatarduel.pack;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.card.Land;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LandPack.java
 * A Class to build Pack that contains Land cards
 */
public class LandPack {
    private List<Land> lands;
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

    /**
     * Create a new list to contain land cards
     */
    public LandPack() {
        this.lands = new ArrayList<>();
    }

    /**
     * Get list land cards
     * @return list land cards
     */
    public List<Land> getCards() {
        return lands;
    }

    /**
     * Load cards from given CSV in AvatarDuel resources
     * @return LandPack
     * @throws IOException exception when initiating another class attributes
     * @throws URISyntaxException exception when the URI syntax is incorrect
     */
    public LandPack loadCards() throws IOException, URISyntaxException {
        File csvFile = new File(AvatarDuel.class.getResource(LAND_CSV_FILE_PATH).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.lands.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
        }
        return this;
    }

    /**
     * Shuffle cards
     */
    public void shuffle() {
        Collections.shuffle(lands);
    }
}
