package dev.kaiserInc.AngelOfTheDices.rules;

import dev.kaiserInc.AngelOfTheDices.character.affinity.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.character.rank.Rank;
import dev.kaiserInc.AngelOfTheDices.character.ritual.Circle;
import dev.kaiserInc.AngelOfTheDices.character.ritual.ExecutionType;
import dev.kaiserInc.AngelOfTheDices.character.ritual.RangeType;
import dev.kaiserInc.AngelOfTheDices.rules.dto.GameRulesDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameRulesService {

    public GameRulesDTO getAllGameRules() {
        return new GameRulesDTO(
            Arrays.asList(Origin.values()),
            Arrays.asList(CharacterClass.values()),
            Arrays.asList(Path.values()),
            Arrays.asList(Affinity.values()),
            Arrays.asList(Rank.values()),
            Arrays.asList(Circle.values()),
            Arrays.asList(ExecutionType.values()),
            Arrays.asList(RangeType.values())
        );
    }
}
