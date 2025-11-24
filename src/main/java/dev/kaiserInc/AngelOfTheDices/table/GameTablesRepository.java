package dev.kaiserInc.AngelOfTheDices.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameTablesRepository extends JpaRepository<GameTable, UUID> {}
