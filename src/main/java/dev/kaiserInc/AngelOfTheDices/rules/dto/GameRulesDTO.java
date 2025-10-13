package dev.kaiserInc.AngelOfTheDices.rules.dto;

import dev.kaiserInc.AngelOfTheDices.character.affinity.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.character.rank.Rank;
import dev.kaiserInc.AngelOfTheDices.character.ritual.Circle;
import dev.kaiserInc.AngelOfTheDices.character.ritual.ExecutionType;
import dev.kaiserInc.AngelOfTheDices.character.ritual.RangeType;

import java.util.List;

public record GameRulesDTO(
        List<Origin> origins,
        List<CharacterClass> classes,
        List<Path> paths,
        List<Affinity> affinities,
        List<Rank> ranks,
        List<Circle> circles,
        List<ExecutionType> executionTypes,
        List<RangeType> rangeTypes
) {}
