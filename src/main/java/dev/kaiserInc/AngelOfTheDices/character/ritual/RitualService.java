package dev.kaiserInc.AngelOfTheDices.character.ritual;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.CharactersRepository;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualMapper;
import dev.kaiserInc.AngelOfTheDices.character.ritual.dto.RitualRequestDTO;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RitualService {

    private final RitualsRepository ritualsRepository;
    private final CharacterService characterService;
    private final CharactersRepository characterRepository;

    @Autowired
    public RitualService(RitualsRepository ritualsRepository, CharacterService characterService, CharactersRepository characterRepository) {
        this.ritualsRepository = ritualsRepository;
        this.characterService = characterService;
        this.characterRepository = characterRepository;
    }

    public Ritual createRitualForCharacter(UUID characterId, UUID userId, RitualRequestDTO ritualDto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Ritual newRitual = RitualMapper.toEntity(ritualDto);
        newRitual.setCharacter(character);

        return ritualsRepository.save(newRitual);
    }

    public Set<Ritual> findAllRitualsByCharacter(UUID characterId, UUID userId) {
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
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);

        Ritual ritualToDelete = character.getRituals().stream()
                .filter(ritual -> ritual.getId().equals(ritualId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Ritual not found for this character."));

        character.getRituals().remove(ritualToDelete);

        characterRepository.save(character);
    }
}
