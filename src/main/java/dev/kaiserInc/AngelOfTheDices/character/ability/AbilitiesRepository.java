package dev.kaiserInc.AngelOfTheDices.character.ability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AbilitiesRepository extends JpaRepository<Ability, UUID> {}
