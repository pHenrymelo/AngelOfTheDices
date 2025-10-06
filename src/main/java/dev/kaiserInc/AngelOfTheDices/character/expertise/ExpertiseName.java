package dev.kaiserInc.AngelOfTheDices.character.expertise;

public enum ExpertiseName {

    ACROBATICS("Acrobacia", Attribute.AGI, true, false, false),
    DRESSAGE("Adestramento", Attribute.PRE, false, true, false),
    ART("Artes", Attribute.PRE, false, true, false),
    ATHLETICS("Atletismo", Attribute.FOR, false, false, false),
    ACTUALITY("Atualidades", Attribute.INT, false, false, false),
    SCIENCE("Ciências", Attribute.INT, false, true, false),
    CRIME("Crime", Attribute.AGI, true, true, true),
    DIPLOMACY("Diplomacia", Attribute.PRE, false, false, false),
    DECEIT("Enganação", Attribute.PRE, false, false, true),
    STAMINA("Fortitude", Attribute.VIG, false, false, false),
    STEALTH("Furtividade", Attribute.AGI, true, false, false),
    INITIATIVE("Iniciativa", Attribute.PRE, false, false, false),
    INTIMIDATION("Intimidação", Attribute.PRE, false, false, false),
    INTUITION("Intuição", Attribute.INT, false, false, false),
    INVESTIGATION("Investigação", Attribute.INT, false, false, false),
    FIGHT("Luta", Attribute.FOR, false, false, false),
    MEDICINE("Medicina", Attribute.INT, false, false, true),
    OCCULTISM("Ocultismo", Attribute.INT, false, true, false),
    PERCEPTION("Percepção", Attribute.PRE, false, false, false),
    NAVIGATE("Pilotagem", Attribute.AGI, false, true, false),
    PRECISION("Pontaria", Attribute.AGI, false, false, false),
    PROFESSION("Profissão", Attribute.INT, false, true, false),
    REACTION("Reflexos", Attribute.AGI, false, false, false),
    FAITH("Religião", Attribute.PRE, false, true, false),
    SURVIVALISM("Sobrevivência", Attribute.INT, false, false, false),
    TACTIC("Tática", Attribute.INT, false, true, false),
    TECHNOLOGY("Tecnologia", Attribute.INT, false, true, true),
    WILL("Vontade", Attribute.PRE, false, false, false);

    private final String displayName;
    private final Attribute baseAttribute;
    private final boolean suffersLoadPenalty;
    private final boolean requiresTraining;
    private final boolean kitApplicable;

    ExpertiseName(String displayName, Attribute baseAttribute, boolean suffersLoadPenalty, boolean requiresTraining, boolean kitApplicable) {
        this.displayName = displayName;
        this.baseAttribute = baseAttribute;
        this.suffersLoadPenalty = suffersLoadPenalty;
        this.requiresTraining = requiresTraining;
        this.kitApplicable = kitApplicable;
    }

    public String getName() {
        return this.name();
    }

}
