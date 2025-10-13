package dev.kaiserInc.AngelOfTheDices.character.expertise.dto;

import dev.kaiserInc.AngelOfTheDices.character.expertise.ExpertiseName;
import dev.kaiserInc.AngelOfTheDices.character.expertise.TrainingRank;
import jakarta.validation.constraints.NotNull;

public record SetExpertiseRequestDTO(
        @NotNull ExpertiseName expertiseName,
        @NotNull TrainingRank trainingRank,
        Boolean hasKit,
        Integer otherBonus
) {}
