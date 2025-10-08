package dev.kaiserInc.AngelOfTheDices.character.note;


import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteMapper;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteRequestDTO;
import dev.kaiserInc.AngelOfTheDices.character.note.dto.NoteResponseDTO;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/characters/{characterId}/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> addNoteToCharacter(@PathVariable UUID characterId, @Valid @RequestBody NoteRequestDTO requestDTO, Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        Note newNote = noteService.createNoteForCharacter(characterId, userPrincipal.getId(), requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(NoteMapper.toResponseDTO(newNote));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getNotes(@PathVariable UUID characterId, Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        Set<Note> notes = noteService.findAllNotesByCharacter(characterId, userPrincipal.getId());
        List<NoteResponseDTO> dtos = notes.stream().map(NoteMapper::toResponseDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable UUID characterId, @PathVariable UUID noteId, Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        Note note = noteService.findNoteById(characterId, noteId, userPrincipal.getId());
        return ResponseEntity.ok(NoteMapper.toResponseDTO(note));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable UUID characterId, @PathVariable UUID noteId, @Valid @RequestBody NoteRequestDTO requestDTO, Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        Note updatedNote = noteService.updateNoteForCharacter(characterId, noteId, userPrincipal.getId(), requestDTO);
        return ResponseEntity.ok(NoteMapper.toResponseDTO(updatedNote));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable UUID characterId, @PathVariable UUID noteId, Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        noteService.deleteNoteForCharacter(characterId, noteId, userPrincipal.getId());
        return ResponseEntity.noContent().build();
    }
}
