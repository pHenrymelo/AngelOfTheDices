package dev.kaiserInc.AngelOfTheDices.character.note;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.CharacterService;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteMapper;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteRequestDTO;
import dev.kaiserInc.AngelOfTheDices.exception.types.ForbiddenAccessException;
import dev.kaiserInc.AngelOfTheDices.exception.types.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class NoteService {

    private final NotesRepository notesRepository;
    private final CharacterService characterService;

    @Autowired
    public NoteService(NotesRepository notesRepository, CharacterService characterService) {
        this.notesRepository = notesRepository;
        this.characterService = characterService;
    }

    // CREATE
    public Note createNoteForCharacter(UUID characterId, UUID userId, NoteRequestDTO dto) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        Note newNote = NoteMapper.toEntity(dto);
        newNote.setCharacter(character);
        return notesRepository.save(newNote);
    }

    // READ (ALL)
    public Set<Note> findAllNotesByCharacter(UUID characterId, UUID userId) {
        Character character = characterService.findCharacterByIdAndUser(characterId, userId);
        return character.getNotes();
    }

    // READ (ONE)
    public Note findNoteById(UUID characterId, UUID noteId, UUID userId) {
        characterService.findCharacterByIdAndUser(characterId, userId);
        Note note = notesRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + noteId));
        if (!note.getCharacter().getId().equals(characterId)) {
            throw new ForbiddenAccessException("Note does not belong to the specified character.");
        }
        return note;
    }

    // UPDATE
    public Note updateNoteForCharacter(UUID characterId, UUID noteId, UUID userId, NoteRequestDTO dto) {
        Note noteToUpdate = this.findNoteById(characterId, noteId, userId);

        NoteMapper.updateEntityFromDto(dto, noteToUpdate);
        return notesRepository.save(noteToUpdate);
    }

    public void deleteNoteForCharacter(UUID characterId, UUID noteId, UUID userId) {
        Note noteToDelete = this.findNoteById(characterId, noteId, userId);
        notesRepository.delete(noteToDelete);
    }
}
