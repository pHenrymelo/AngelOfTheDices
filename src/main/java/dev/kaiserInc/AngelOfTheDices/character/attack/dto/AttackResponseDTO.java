package dev.kaiserInc.AngelOfTheDices.character.attack.dto;

import dev.kaiserInc.AngelOfTheDices.character.attack.AttackType;
import dev.kaiserInc.AngelOfTheDices.character.attack.DiceType;
import dev.kaiserInc.AngelOfTheDices.character.attack.RangeType;
import dev.kaiserInc.AngelOfTheDices.character.expertise.Attribute;
import dev.kaiserInc.AngelOfTheDices.character.expertise.ExpertiseName;

import java.util.UUID;

public record AttackResponseDTO(
        UUID id,
        String name,
        AttackType type,
        Attribute testAttribute,
        ExpertiseName testExpertise,
        int testBonus,
        int damageDiceQuantity,
        DiceType damageDiceType,
        int damageBonus,
        int criticalThreshold,
        int criticalMultiplier,
        RangeType range,
        String special
) {}
