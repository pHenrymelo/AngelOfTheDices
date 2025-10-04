package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import dev.kaiserInc.AngelOfTheDices.character.expertise.CharacterExpertise;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private Integer nex;

    private Integer strength;
    private Integer agility;
    private Integer intellect;
    private Integer presence;
    private Integer vigor;

    private Integer MaxHitPoints;
    private Integer CurrentHitPoints;

    private Integer MaxEffortPoints;
    private Integer CurrentEffortPoints;

    private Integer MaxSanity;
    private Integer CurrentSanity;

    private String rank;
    private Integer prestigePoints;

    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Enumerated(EnumType.STRING)
    private Path path;

    @Enumerated(EnumType.STRING)
    private Affinity affinity;

    @Enumerated(EnumType.STRING)
    private Origin origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "character_expertises", joinColumns = @JoinColumn(name = "character_id"))
    private Set<CharacterExpertise> expertises = new HashSet<>();


}
