package dev.kaiserInc.AngelOfTheDices.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kaiserInc.AngelOfTheDices.character.Character;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int category;
    private int spaces;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Character character;
}
