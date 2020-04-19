package com.avatarduel.pack;

import com.avatarduel.AvatarDuel;
import com.avatarduel.model.card.Element;
import com.avatarduel.model.card.Skill;
import com.avatarduel.model.card.effect.Aura;
import com.avatarduel.model.card.effect.Destroy;
import com.avatarduel.model.card.effect.PowerUp;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SkillPack.java
 * A Class to build Pack that contains Skill cards
 */
public class SkillPack {
    private List<Skill> skills;
    private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
    private static final String SKILL_DESTROY_CSV_FILE_PATH = "card/data/skill_destroy.csv";
    private static final String SKILL_POWER_UP_CSV_FILE_PATH = "card/data/skill_powerup.csv";

    /**
     * Create a new list to contain Skill cards
     */
    public SkillPack() {
        this.skills = new ArrayList<>();
    }

    /**
     * Get list skill cards
     * @return list skill cards
     */
    public List<Skill> getCards() {
        return skills;
    }

    /**
     * Load cards from given CSV in AvatarDuel resources
     * @return SkillPack
     * @throws IOException exception when initiating another class attributes
     * @throws URISyntaxException exception when the URI syntax is incorrect
     */
    public SkillPack loadSkillAuraCards() throws IOException, URISyntaxException {
        File csvFile = new File(AvatarDuel.class.getResource(SKILL_AURA_CSV_FILE_PATH).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.skills.add(new Aura(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Integer.parseInt(row[5]), row[4]));
        }
        return this;
    }

    /**
     * Load cards from given CSV in AvatarDuel resources
     * @return SkillPack
     * @throws IOException exception when initiating another class attributes
     * @throws URISyntaxException exception when the URI syntax is incorrect
     */
    public SkillPack loadSkillDestroyCards() throws IOException, URISyntaxException {
        File csvFile = new File(AvatarDuel.class.getResource(SKILL_DESTROY_CSV_FILE_PATH).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.skills.add(new Destroy(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), row[4]));
        }
        return this;
    }

    /**
     * Load cards from given CSV in AvatarDuel resources
     * @return SkillPack
     * @throws IOException exception when initiating another class attributes
     * @throws URISyntaxException exception when the URI syntax is incorrect
     */
    public SkillPack loadSkillPowerUpCards() throws IOException, URISyntaxException {
        File csvFile = new File(AvatarDuel.class.getResource(SKILL_POWER_UP_CSV_FILE_PATH).toURI());
        CSVReader reader = new CSVReader(csvFile, "\t");
        reader.setSkipHeader(true);
        List<String[]> rows = reader.read();
        for (String[] row : rows) {
            this.skills.add(new PowerUp(row[1], row[3], Element.valueOf(row[2]), Integer.parseInt(row[5]), row[4]));
        }
        return this;
    }

    /**
     * Shuffle cards
     */
    public void shuffle() {
        Collections.shuffle(skills);
    }
}
