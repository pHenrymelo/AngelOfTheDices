package dev.kaiserInc.AngelOfTheDices.character.note.dto;

import dev.kaiserInc.AngelOfTheDices.character.note.Note;

public final class NoteMapper {
    private NoteMapper() {}

    public static Note toEntity(NoteRequestDTO dto) {
        Note note = new Note();
        note.setTitle(dto.title());
        note.setDescription(dto.description());
        return note;
    }

    public static void updateEntityFromDto(NoteRequestDTO dto, Note note) {
        note.setTitle(dto.title());
        note.setDescription(dto.description());
    }

    public static NoteResponseDTO toResponseDTO(Note note) {
        return new NoteResponseDTO(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.isPinned(),
                note.getCreatedAt());
    }
}
