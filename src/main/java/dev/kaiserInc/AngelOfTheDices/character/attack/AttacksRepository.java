package dev.kaiserInc.AngelOfTheDices.character.attack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttacksRepository extends JpaRepository<Attack, UUID> {}
