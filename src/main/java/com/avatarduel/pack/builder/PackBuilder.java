package com.avatarduel.pack.builder;

import com.avatarduel.pack.CharacterPack;
import com.avatarduel.pack.LandPack;
import com.avatarduel.pack.Pack;
import com.avatarduel.pack.SkillPack;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * PackBuilder.java
 * A class to build Card Pack in AvatarDuel game
 * ATTRIBUTES :
 * cardPack                 : Card Pack all cards
 * SkillPack                : Card Pack for Skill cards
 */
public class PackBuilder {
    private Pack cardPack;
    private SkillPack skillPack;

    /**
     * Creates a new PackBuilder
     */
    public PackBuilder() {
        this.cardPack = new Pack();
        this.skillPack = new SkillPack();
    }

    /**
     * Add land cards to PackBuilder
     * @return PackBuilder
     * @throws IOException exception when initiating LandPack
     * @throws URISyntaxException exception when the URI syntax is incorrect in LandPack
     */
    public PackBuilder addLands() throws IOException, URISyntaxException {
        cardPack.setLands(new LandPack().loadCards());
        return this;
    }

    /**
     * Add character cards to PackBuilder
     * @return PackBuilder
     * @throws IOException exception when initiating CharacterPack
     * @throws URISyntaxException exception when the URI syntax is incorrect in CharacterPack
     */
    public PackBuilder addCharacters() throws IOException, URISyntaxException {
        cardPack.setCharacters(new CharacterPack().loadCards());
        return this;
    }

    /**
     * Add aura skill cards to PackBuilder
     * @return PackBuilder
     * @throws IOException exception when initiating SkillAuraPacks
     * @throws URISyntaxException exception when the URI syntax is incorrect in SkillAuraPacks
     */
    public PackBuilder addAuraSkills() throws IOException, URISyntaxException {
        this.skillPack.loadSkillAuraCards();
        return this;
    }

    /**
     * Add destroy skill cards to PackBuilder
     * @return PackBuilder
     * @throws IOException exception when initiating SkillDestroyPacks
     * @throws URISyntaxException exception when the URI syntax is incorrect in SkillDestroyPacks
     */
    public PackBuilder addDestroySkills() throws IOException, URISyntaxException {
        this.skillPack.loadSkillDestroyCards();
        return this;
    }

    /**
     * Add power up skill cards to PackBuilder
     * @return PackBuilder
     * @throws IOException exception when initiating SkillPowerUpPacks
     * @throws URISyntaxException exception when the URI syntax is incorrect in SkillPowerUpPacks
     */
    public PackBuilder addPowerUpSkills() throws IOException, URISyntaxException {
        this.skillPack.loadSkillPowerUpCards();
        return this;
    }

    /**
     * Add all cards to PackBuilder
     * @return PackBuilder
     * @throws IOException exception when adding some pack to PackBuilder
     * @throws URISyntaxException exception when the URI syntax is incorrect when adding some pack to PackBuilder
     */
    public PackBuilder addAllCards() throws IOException, URISyntaxException {
        return this.addLands().addCharacters().addAuraSkills().addDestroySkills().addPowerUpSkills();
    }

    /**
     * Build card with this PackBuilder
     * @return Pack
     */
    public Pack build() {
        this.cardPack.setSkills(this.skillPack);
        return this.cardPack;
    }
}
