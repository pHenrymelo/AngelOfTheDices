package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.dto.CharacterCreateRequestDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import dev.kaiserInc.AngelOfTheDices.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Character createCharacter(CharacterCreateRequestDTO dto, UUID userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Authenticated user nor found"));

        Character newCharacter = new Character();

        newCharacter.setName(dto.name());
        newCharacter.setOrigin(dto.origin());
        newCharacter.setChar_class(dto.char_class());
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

        return characterRepository.save(newCharacter);
    }

    public List<Character> findAllByUser(UUID userId) {
        return characterRepository.findByUserId(userId);
    }

    public Character findByIdAndUser(UUID id, UUID userId) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        if (!character.getUser().getId().equals(userId)) {
            throw new RuntimeException("Permission denied");
        }

        return character;
    }
}
