package com.avatarduel.pack.builder;

import com.avatarduel.pack.CharacterPack;
import com.avatarduel.pack.LandPack;
import com.avatarduel.pack.Pack;
import com.avatarduel.pack.SkillPack;

import java.io.IOException;
import java.net.URISyntaxException;

public class PackBuilder {
    private Pack cardPack;
    private SkillPack skillPack;

    public PackBuilder() {
        this.cardPack = new Pack();
        this.skillPack = new SkillPack();
    }

    public PackBuilder addLands() throws IOException, URISyntaxException {
        cardPack.setLands(new LandPack().loadCards());
        return this;
    }

    public PackBuilder addCharacters() throws IOException, URISyntaxException {
        cardPack.setCharacters(new CharacterPack().loadCards());
        return this;
    }

    public PackBuilder addAuraSkills() throws IOException, URISyntaxException {
        this.skillPack.loadSkillAuraCards();
        return this;
    }

    public PackBuilder addDestroySkills() throws IOException, URISyntaxException {
        this.skillPack.loadSkillDestroyCards();
        return this;
    }

    public PackBuilder addPowerUpSkills() throws IOException, URISyntaxException {
        this.skillPack.loadSkillPowerUpCards();
        return this;
    }

    public PackBuilder addAllCards() throws IOException, URISyntaxException {
        return this.addLands().addCharacters().addAuraSkills().addDestroySkills().addPowerUpSkills();
    }

    public Pack build() {
        this.cardPack.setSkills(this.skillPack);
        return this.cardPack;
    }
}
