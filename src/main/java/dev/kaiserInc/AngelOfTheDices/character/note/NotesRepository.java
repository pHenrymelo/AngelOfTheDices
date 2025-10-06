package dev.kaiserInc.AngelOfTheDices.character.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotesRepository extends JpaRepository<Note, UUID> {}
