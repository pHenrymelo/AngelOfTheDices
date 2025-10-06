package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.dto.*;
import dev.kaiserInc.AngelOfTheDices.exception.types.BusinessRuleException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbidenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import dev.kaiserInc.AngelOfTheDices.storage.FileStorageService;
import dev.kaiserInc.AngelOfTheDices.user.User;
import dev.kaiserInc.AngelOfTheDices.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CharacterService {

    @Autowired
    private CharactersRepository charactersRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FileStorageService fileStorageService;


    public Character createCharacter(CharacterCreateRequestDTO dto, UUID userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Authenticated user nor found"));

        if (dto.path().getCharacterClass() != dto.characterClass()) {
            throw new BusinessRuleException("The path '" +dto.path().getDisplayName() + "' is not valid for class '" + dto.characterClass().name() + "'.");
        }

        Character newCharacter = new Character();

        newCharacter.setName(dto.name());
        newCharacter.setOrigin(dto.origin());
        newCharacter.setCharacterClass(dto.characterClass());
        newCharacter.setPath(dto.path());
        newCharacter.setAffinity(dto.affinity());
        newCharacter.setNex(dto.nex());
        newCharacter.setStrength(dto.strength());
        newCharacter.setAgility(dto.agility());
        newCharacter.setIntellect(dto.intellect());
        newCharacter.setPresence(dto.presence());
        newCharacter.setVigor(dto.vigor());
        newCharacter.setMaxHitPoints(dto.maxHitPoints());
        newCharacter.setMaxEffortPoints(dto.maxEffortPoints());
        newCharacter.setMaxSanity(dto.maxSanity());
        newCharacter.setRank(dto.rank());
        newCharacter.setPrestigePoints(dto.prestigePoints());

        newCharacter.setCurrentHitPoints(dto.maxHitPoints());
        newCharacter.setCurrentEffortPoints(dto.maxEffortPoints());
        newCharacter.setCurrentSanity(dto.maxSanity());

        newCharacter.setUser(user);

        return charactersRepository.save(newCharacter);
    }

    public List<Character> findAllByUser(UUID userId) {
        return charactersRepository.findByUserId(userId);
    }

    public Character findCharacterByIdAndUser(UUID id, UUID userId) {
        Character character = charactersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        if (!character.getUser().getId().equals(userId)) {
            throw new ForbidenAccessException("Permission denied");
        }

        return character;
    }

    public Character updateCharacter(UUID characterId, UUID userId, CharacterUpdateDTO characterData) {
        Character characterToUpdate = this.findCharacterByIdAndUser(characterId, userId);

        characterToUpdate.setName(characterData.name());
        characterToUpdate.setOrigin(characterData.origin());
        characterToUpdate.setCharacterClass(characterData.characterClass());
        characterToUpdate.setNex(characterData.nex());
        characterToUpdate.setStrength(characterData.strength());
        characterToUpdate.setAgility(characterData.agility());
        characterToUpdate.setIntellect(characterData.intellect());
        characterToUpdate.setPresence(characterData.presence());
        characterToUpdate.setVigor(characterData.vigor());
        characterToUpdate.setMaxHitPoints(characterData.maxHitPoints());
        characterToUpdate.setMaxEffortPoints(characterData.maxEffortPoints());
        characterToUpdate.setMaxSanity(characterData.maxSanity());
        characterToUpdate.setRank(characterData.rank());
        characterToUpdate.setPrestigePoints(characterData.prestigePoints());

        return charactersRepository.save(characterToUpdate);
    }

    public Character patchCharacterStatus(UUID characterId, UUID userId, CharacterStatusUpdateDTO dto) {
        Character characterToUpdate = this.findCharacterByIdAndUser(characterId, userId);

        if (dto.currentHitPoints() != null) {
            if (dto.currentHitPoints() < 0 || dto.currentHitPoints() > characterToUpdate.getMaxHitPoints()) {
                throw new IllegalArgumentException("Invalid Hit Points value.");
            }
            characterToUpdate.setCurrentHitPoints(dto.currentHitPoints());
        }

        if (dto.currentEffortPoints() != null) {
            if (dto.currentEffortPoints() < 0 || dto.currentEffortPoints() > characterToUpdate.getMaxEffortPoints()) {
                throw new IllegalArgumentException("Invalid Effort Points value.");
            }
            characterToUpdate.setCurrentEffortPoints(dto.currentEffortPoints());
        }


        if (dto.currentSanity() != null) {
            if (dto.currentSanity() < 0 || dto.currentSanity() > characterToUpdate.getMaxSanity()) {
                throw new IllegalArgumentException("Invalid Sanity value.");
            }
            characterToUpdate.setCurrentSanity(dto.currentSanity());
        }

        return charactersRepository.save(characterToUpdate);
    }

    public void deleteCharacter(UUID characterId, UUID userId) {
        Character characterToDelete = this.findCharacterByIdAndUser(characterId, userId);
        charactersRepository.delete(characterToDelete);
    }



    public Character setCharacterPortrait(UUID characterId, UUID userId, MultipartFile file) {
        Character character = findCharacterByIdAndUser(characterId, userId);

        if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
            throw new BusinessRuleException("Invalid file type. Only images are allowed.");
        }

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = characterId.toString() + "." + extension;

        fileStorageService.store(file, filename);

        String fileUrl = "/portraits/" + filename;

        character.setPortraitUrl(fileUrl);
        return charactersRepository.save(character);
    }
}
