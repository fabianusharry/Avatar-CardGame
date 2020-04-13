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

public class LandPack {
    private List<Land> lands;
    private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

    public LandPack() {
        this.lands = new ArrayList<>();
    }

    public List<Land> getCards() {
        return lands;
    }

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

    public void shuffle() {
        Collections.shuffle(lands);
    }
}
