package dev.kaiserInc.AngelOfTheDices.character.dto;

public record DefenseDTO(
    int total,
    int base,
    int agilityBonus,
    int armorBonus,
    int otherBonus
) {}
