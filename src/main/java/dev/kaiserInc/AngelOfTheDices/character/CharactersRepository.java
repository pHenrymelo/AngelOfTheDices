package dev.kaiserInc.AngelOfTheDices.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharactersRepository extends JpaRepository<Character, UUID> {
    List<Character> findByUserId(UUID userId);

    @Query("SELECT c FROM Character c " +
            "LEFT JOIN FETCH c.user u " +
            "LEFT JOIN FETCH c.expertises " +
            "LEFT JOIN FETCH c.inventory " +
            "LEFT JOIN FETCH c.attacks " +
            "LEFT JOIN FETCH c.abilities " +
            "LEFT JOIN FETCH c.rituals " +
            "LEFT JOIN FETCH c.notes " +
            "WHERE c.id = :id")
    Optional<Character> findByIdWithAllDetails(@Param("id") UUID id);
}
