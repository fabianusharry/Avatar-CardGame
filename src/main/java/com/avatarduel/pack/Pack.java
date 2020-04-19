package com.avatarduel.pack;

/**
 * Pack.java
 * A Class to build card pack
 */
public class Pack {
    // CardPack attribute
    private LandPack lands;
    private CharacterPack characters;
    private SkillPack skills;

    /**
     * Create a new list to contain all cards
     */
    public Pack() {
        lands = new LandPack();
        characters = new CharacterPack();
        skills = new SkillPack();
    }

    /**
     * Shuffle all cards
     */
    public void shuffle() {
        lands.shuffle();
        characters.shuffle();
        skills.shuffle();
    }

    /**
     * Get land card packs
     * @return LandPack
     */
    public LandPack getLands() {
        return lands;
    }

    /**
     * Get character card packs
     * @return CharacterPack
     */
    public CharacterPack getCharacters() {
        return characters;
    }

    /**
     * Get skill card packs
     * @return SkillPack
     */
    public SkillPack getSkills() {
        return skills;
    }

    /**
     * Set LandPack in this class to land in parameter
     * @param lands LandPack
     */
    public void setLands(LandPack lands) {
        this.lands = lands;
    }

    /**
     * Set CharacterPack in this class to characters in parameter
     * @param characters CharacterPack
     */
    public void setCharacters(CharacterPack characters) {
        this.characters = characters;
    }

    /**
     * Set SkillPack in this class to skills in parameter
     * @param skills SkillPack
     */
    public void setSkills(SkillPack skills) {
        this.skills = skills;
    }
}
