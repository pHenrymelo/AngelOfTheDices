package dev.kaiserInc.AngelOfTheDices.attack.dto;

import dev.kaiserInc.AngelOfTheDices.attack.AttackType;
import dev.kaiserInc.AngelOfTheDices.attack.DiceType;
import dev.kaiserInc.AngelOfTheDices.attack.RangeType;
import dev.kaiserInc.AngelOfTheDices.character.expertise.Attribute;
import dev.kaiserInc.AngelOfTheDices.character.expertise.ExpertiseName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttackRequestDTO(
        @NotBlank String name,
        @NotNull AttackType type,
        @NotNull Attribute testAttribute,
        ExpertiseName testExpertise,
        Integer testBonus,
        @NotNull Integer damageDiceQuantity,
        @NotNull DiceType damageDiceType,
        Integer damageBonus,
        @NotNull Integer criticalThreshold,
        @NotNull Integer criticalMultiplier,
        @NotNull RangeType range,
        String special
) {}
