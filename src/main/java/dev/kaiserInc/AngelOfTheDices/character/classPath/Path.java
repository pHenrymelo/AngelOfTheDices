package dev.kaiserInc.AngelOfTheDices.character.classPath;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kaiserInc.AngelOfTheDices.character.Sourcebook;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Path {

    // Combatant
    ANNIHILATOR("Aniquilador", CharacterClass.COMBATANT, Sourcebook.CORE_RULEBOOK),
    FIELD_COMMANDER("Comandante de Campo", CharacterClass.COMBATANT, Sourcebook.CORE_RULEBOOK),
    WARRIOR("Guerreiro", CharacterClass.COMBATANT, Sourcebook.CORE_RULEBOOK),
    SPY_OPS("Operações Especiais", CharacterClass.COMBATANT, Sourcebook.CORE_RULEBOOK),
    SHOCK_TROOPER("Tropa de Choque", CharacterClass.COMBATANT, Sourcebook.CORE_RULEBOOK),

    // Specialist
    SNIPER("Atirador de Elite", CharacterClass.SPECIALIST, Sourcebook.CORE_RULEBOOK),
    INFILTRATOR("Infiltrador", CharacterClass.SPECIALIST, Sourcebook.CORE_RULEBOOK),
    FIELD_MEDIC("Medico de Campo", CharacterClass.SPECIALIST, Sourcebook.CORE_RULEBOOK),
    NEGOTIATOR("Negociador", CharacterClass.SPECIALIST, Sourcebook.CORE_RULEBOOK),
    TECHNICIAN("Técnico", CharacterClass.SPECIALIST, Sourcebook.CORE_RULEBOOK),

    // Occultist
    CONDUIT("Conduíte", CharacterClass.OCCULTIST, Sourcebook.CORE_RULEBOOK),
    FLAGELLANT("Flagelador", CharacterClass.OCCULTIST, Sourcebook.CORE_RULEBOOK),
    GRADUATE("Graduado", CharacterClass.OCCULTIST, Sourcebook.CORE_RULEBOOK),
    INTUITIVE("Intuitivo", CharacterClass.OCCULTIST, Sourcebook.CORE_RULEBOOK),
    PARANORMAL_BLADE("Lamina Paranormal", CharacterClass.OCCULTIST, Sourcebook.CORE_RULEBOOK),

    // Supplement paths

    // Combatant
    SECRET_AGENT("Agente Secreto", CharacterClass.COMBATANT, Sourcebook.SUPPLEMENT),
    HUNTER("Caçador", CharacterClass.COMBATANT, Sourcebook.SUPPLEMENT),
    MONSTROUS("Monstruoso", CharacterClass.COMBATANT, Sourcebook.SUPPLEMENT),

    // Specialist
    LIBRARIAN("Bibliotecatio", CharacterClass.SPECIALIST, Sourcebook.SUPPLEMENT),
    TENACIOUS("Perseverante", CharacterClass.SPECIALIST, Sourcebook.SUPPLEMENT),
    STREET_TRADER("Muambeiro", CharacterClass.SPECIALIST, Sourcebook.SUPPLEMENT),

    // Occultist
    EXORCIST("Exorcista", CharacterClass.OCCULTIST, Sourcebook.SUPPLEMENT),
    POSSESSED("Possuído", CharacterClass.OCCULTIST, Sourcebook.SUPPLEMENT),
    PARAPSYCHOLOGIST("Parapsicologo", CharacterClass.OCCULTIST, Sourcebook.SUPPLEMENT),

    // SURVIVOR
    TUFF("Durão", CharacterClass.SURVIVOR, Sourcebook.SUPPLEMENT),
    SMART("Esperto", CharacterClass.SURVIVOR, Sourcebook.SUPPLEMENT),
    ESOTERIC("Esotérico", CharacterClass.SURVIVOR, Sourcebook.SUPPLEMENT);

    private final String displayName;
    private final CharacterClass characterClass;
    private final Sourcebook source;

    Path(String displayName, CharacterClass characterClass, Sourcebook source) {
        this.displayName = displayName;
        this.characterClass = characterClass;
        this.source = source;
    }

    public String getName() {
        return this.name();
    }
}
