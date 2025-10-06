package dev.kaiserInc.AngelOfTheDices.attack.dto;

import dev.kaiserInc.AngelOfTheDices.attack.AttackType;
import dev.kaiserInc.AngelOfTheDices.attack.DiceType;
import dev.kaiserInc.AngelOfTheDices.attack.RangeType;
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
