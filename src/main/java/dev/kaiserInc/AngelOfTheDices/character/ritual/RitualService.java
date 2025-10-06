package dev.kaiserInc.AngelOfTheDices.character.ritual;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualMapper;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualRequestDTO;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RitualService {

    private final RitualsRepository ritualsRepository;
    private final CharacterService characterService;

    @Autowired
    public RitualService(RitualsRepository ritualsRepository, CharacterService characterService) {
        this.ritualsRepository = ritualsRepository;
        this.characterService = characterService;
    }

    public Ritual createRitualForCharacter(UUID characterId, UUID userId, RitualRequestDTO ritualDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Ritual newRitual = RitualMapper.toEntity(ritualDto);
        newRitual.setCharacter(character);

        return ritualsRepository.save(newRitual);
    }

    public List<Ritual> findAllRitualsByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getRituals();
    }

    public Ritual findRitualById(UUID characterId, UUID ritualId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        return ritualsRepository.findById(ritualId)
                .orElseThrow(() -> new ResourceNotFoundException("Ritual not found."));
    }

    public Ritual updateRitualForCharacter(UUID characterId, UUID ritualId, UUID userId, RitualRequestDTO ritualDto) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Ritual ritualToUpdate = ritualsRepository.findById(ritualId)
                .orElseThrow(() -> new ResourceNotFoundException("Ritual not found."));

        if (!ritualToUpdate.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Ritual does not belong to the specified character.");
        }

        RitualMapper.updateEntityFromDTO(ritualDto, ritualToUpdate);

        return ritualsRepository.save(ritualToUpdate);
    }

    public void deleteRitualForCharacter(UUID characterId, UUID ritualId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);

        Ritual ritualToDelete = ritualsRepository.findById(ritualId)
                .orElseThrow(() -> new ResourceNotFoundException("Ritual not found."));

        if (!ritualToDelete.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Ritual does not belong to the specified character.");
        }

        ritualsRepository.delete(ritualToDelete);

    }
}
