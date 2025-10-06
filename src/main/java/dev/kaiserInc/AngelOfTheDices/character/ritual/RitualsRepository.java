package dev.kaiserInc.AngelOfTheDices.character.ritual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RitualsRepository extends JpaRepository<Ritual, UUID> {}
