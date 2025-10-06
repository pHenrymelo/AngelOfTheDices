package dev.kaiserInc.AngelOfTheDices.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemsRepository extends JpaRepository<Item, UUID> {}
