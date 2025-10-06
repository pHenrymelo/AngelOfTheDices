package dev.kaiserInc.AngelOfTheDices.character.expertise;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharactersRepository;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.dto.SetExpertiseRequestDTO;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ExpertiseService {

    private final CharacterService characterService;
    private final CharactersRepository charactersRepository;

    @Autowired
    public ExpertiseService(CharacterService characterService, CharactersRepository charactersRepository) {
        this.characterService = characterService;
        this.charactersRepository = charactersRepository;
    }

    public dev.kaiserInc.AngelOfTheDices.character.Character setExpertise(UUID characterId, UUID userId, SetExpertiseRequestDTO dto) {
        dev.kaiserInc.AngelOfTheDices.character.Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        CharacterExpertise expertiseToUpdate = character.getExpertises().stream()
                .filter(exp -> exp.getExpertiseName().equals(dto.expertiseName()))
                .findFirst()
                .orElse(new CharacterExpertise());

        expertiseToUpdate.setExpertiseName(dto.expertiseName());
        expertiseToUpdate.setTrainingRank(dto.trainingRank());

        if (dto.hasKit() != null) {
            expertiseToUpdate.setHasKit(dto.hasKit());
        }

        character.getExpertises().remove(expertiseToUpdate);
        character.getExpertises().add(expertiseToUpdate);

        return charactersRepository.save(character);
    }

    public Set<CharacterExpertise> findAllExpertisesByCharacter(UUID characterId, UUID userId) {
        dev.kaiserInc.AngelOfTheDices.character.Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getExpertises();
    }

    public CharacterExpertise findExpertiseByName(UUID characterId, UUID userId, ExpertiseName expertiseName) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getExpertises().stream()
                .filter(exp -> exp.getExpertiseName().equals(expertiseName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Expertise not found"));
    }
}
