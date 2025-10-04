package dev.kaiserInc.AngelOfTheDices.character.origin;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kaiserInc.AngelOfTheDices.character.Sourcebook;
import dev.kaiserInc.AngelOfTheDices.character.expertise.ExpertiseName;
import lombok.Getter;

import java.util.List;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Origin {

    // base origins

    IT("T.I", "Motor de Busca", List.of(ExpertiseName.INVESTIGATION, ExpertiseName.TECHNOLOGY), Sourcebook.CORE_RULEBOOK),

    // supplement origins
    ENTHUSIAST_NERD("Nerd Entusiasta", "O Inteligentão", List.of(ExpertiseName.SCIENCE, ExpertiseName.TECHNOLOGY), Sourcebook.SUPPLEMENT);

    private final String displayName;
    private final String powerDescription;
    private final List<ExpertiseName> skillOptions;
    private final Sourcebook source;

    Origin(String displayName, String powerDescription, List<ExpertiseName> skillOptions, Sourcebook source) {
        this.displayName = displayName;
        this.powerDescription = powerDescription;
        this.skillOptions = skillOptions;
        this.source = source;
    }

    public String getName() {
        return this.name();
    }
}
