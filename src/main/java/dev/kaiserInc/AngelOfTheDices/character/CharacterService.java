package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.dto.*;
import dev.kaiserInc.AngelOfTheDices.exception.types.BusinessRuleException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import dev.kaiserInc.AngelOfTheDices.storage.FileStorageService;
import dev.kaiserInc.AngelOfTheDices.user.User;
import dev.kaiserInc.AngelOfTheDices.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {

    private final CharactersRepository charactersRepository;
    private final UsersRepository usersRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public CharacterService(CharactersRepository charactersRepository, UsersRepository usersRepository, FileStorageService fileStorageService) {
        this.charactersRepository = charactersRepository;
        this.usersRepository = usersRepository;
        this.fileStorageService = fileStorageService;
    }

    public Character createCharacter(CharacterCreateRequestDTO dto, UUID userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user nor found"));

        if (dto.characterClass() == CharacterClass.SURVIVOR && dto.nex() != 0) {
            throw new BusinessRuleException("A classe Sobrevivente só pode ser criada com NEX 0%.");
        }

        if (dto.path().getCharacterClass() != dto.characterClass()) {
            throw new BusinessRuleException("The path '" +dto.path().getDisplayName() + "' is not valid for class '" + dto.characterClass().name() + "'.");
        }

        Character newCharacter = CharacterMapper.toEntity(dto);
        newCharacter.setUser(user);
        int nex = dto.nex();
        int level = Math.round(((float) nex / 5));

        if (level < 1) {
            level = 1;
        }

        CharacterClass selectedClass = dto.characterClass();

        int maxHp = selectedClass.getBaseHitPoints() + ((level - 1) * selectedClass.getHpPerLevel()) + (level * dto.vigor());
        int maxEp = selectedClass.getBaseEffortPoints() + ((level - 1) * selectedClass.getEpPerLevel()) + (level * dto.presence());
        int maxSan = selectedClass.getBaseSanity() + ((level - 1) * selectedClass.getSanPerLevel());


        newCharacter.setMaxHitPoints(maxHp);
        newCharacter.setCurrentHitPoints(maxHp);

        newCharacter.setMaxEffortPoints(maxEp);
        newCharacter.setCurrentEffortPoints(maxEp);

        newCharacter.setMaxSanity(maxSan);
        newCharacter.setCurrentSanity(maxSan);

        return charactersRepository.save(newCharacter);
    }

    public Page<Character> findAllByUser(UUID userId, Pageable pageable) {
        return charactersRepository.findByUserId(userId, pageable);
    }

    public Character findCharacterByIdAndUser(UUID id, UUID userId) {
        Character character = charactersRepository.findByIdWithAllDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Character not found"));

        if (!character.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException("Permission denied");
        }

        return character;
    }

    public Character updateCharacter(UUID characterId, UUID userId, CharacterUpdateRequestDTO dto) {
        Character characterToUpdate = this.findCharacterByIdAndUser(characterId, userId);

        if (dto.characterClass() == CharacterClass.SURVIVOR && dto.nex() != 0) {
            throw new BusinessRuleException("A classe Sobrevivente só pode ter NEX 0%.");
        }

        CharacterMapper.updateEntityFromDTO(dto, characterToUpdate);

        if (characterToUpdate.getCurrentHitPoints() > characterToUpdate.getMaxHitPoints()) {
            characterToUpdate.setCurrentHitPoints(characterToUpdate.getMaxHitPoints());
        }
        if (characterToUpdate.getCurrentEffortPoints() > characterToUpdate.getMaxEffortPoints()) {
            characterToUpdate.setCurrentEffortPoints(characterToUpdate.getMaxEffortPoints());
        }
        if (characterToUpdate.getCurrentSanity() > characterToUpdate.getMaxSanity()) {
            characterToUpdate.setCurrentSanity(characterToUpdate.getMaxSanity());
        }

        return charactersRepository.save(characterToUpdate);
    }

    public Character patchCharacterStatus(UUID characterId, UUID userId, CharacterStatusUpdateDTO dto) {
        Character characterToUpdate = this.findCharacterByIdAndUser(characterId, userId);

        if (dto.currentHitPoints() != null) {
            if (dto.currentHitPoints() < 0 || dto.currentHitPoints() > characterToUpdate.getMaxHitPoints()) {
                throw new BusinessRuleException("Invalid Hit Points value.");
            }
            characterToUpdate.setCurrentHitPoints(dto.currentHitPoints());
        }

        if (dto.currentEffortPoints() != null) {
            if (dto.currentEffortPoints() < 0 || dto.currentEffortPoints() > characterToUpdate.getMaxEffortPoints()) {
                throw new BusinessRuleException("Invalid Effort Points value.");
            }
            characterToUpdate.setCurrentEffortPoints(dto.currentEffortPoints());
        }


        if (dto.currentSanity() != null) {
            if (dto.currentSanity() < 0 || dto.currentSanity() > characterToUpdate.getMaxSanity()) {
                throw new BusinessRuleException("Invalid Sanity value.");
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
