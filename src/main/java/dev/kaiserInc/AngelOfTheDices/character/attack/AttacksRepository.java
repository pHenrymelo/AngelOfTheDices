package dev.kaiserInc.AngelOfTheDices.character.attack;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttacksRepository extends JpaRepository<Attack, UUID> {}
