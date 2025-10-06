package dev.kaiserInc.AngelOfTheDices.character.ability;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kaiserInc.AngelOfTheDices.character.Character;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "abilities")
@Getter
@Setter
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AbilityType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Character character;
}
