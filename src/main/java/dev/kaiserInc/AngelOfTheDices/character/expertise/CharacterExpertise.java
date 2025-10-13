package dev.kaiserInc.AngelOfTheDices.character.expertise;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class CharacterExpertise {

    @Enumerated(EnumType.STRING)
    private ExpertiseName expertiseName;

    @Enumerated(EnumType.STRING)
    private TrainingRank trainingRank;

    private boolean hasKit;

    private Integer otherBonus;
}
