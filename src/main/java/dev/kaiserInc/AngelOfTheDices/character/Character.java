package dev.kaiserInc.AngelOfTheDices.character;

import dev.kaiserInc.AngelOfTheDices.character.ability.Ability;
import dev.kaiserInc.AngelOfTheDices.character.affinity.Affinity;
import dev.kaiserInc.AngelOfTheDices.character.classPath.CharacterClass;
import dev.kaiserInc.AngelOfTheDices.character.classPath.Path;
import dev.kaiserInc.AngelOfTheDices.character.expertise.CharacterExpertise;
import dev.kaiserInc.AngelOfTheDices.character.note.Note;
import dev.kaiserInc.AngelOfTheDices.character.origin.Origin;
import dev.kaiserInc.AngelOfTheDices.character.item.Item;
import dev.kaiserInc.AngelOfTheDices.character.attack.Attack;
import dev.kaiserInc.AngelOfTheDices.character.rank.Rank;
import dev.kaiserInc.AngelOfTheDices.character.ritual.Ritual;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @UpdateTimestamp
    private Instant updatedAt;

    @Column(nullable = false)
    private String name;
    private Integer nex;
    private String portraitUrl;

    private Integer age;
    private String gender;

    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    @Enumerated(EnumType.STRING)
    private Path path;

    @Enumerated(EnumType.STRING)
    private Affinity affinity;

    @Enumerated(EnumType.STRING)
    private Origin origin;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    private Integer strength;
    private Integer agility;
    private Integer intellect;
    private Integer presence;
    private Integer vigor;

    private Integer maxHitPoints;
    private Integer currentHitPoints;

    private Integer maxEffortPoints;
    private Integer currentEffortPoints;

    private Integer maxSanity;
    private Integer currentSanity;

    private Integer armorDefenseBonus;
    private Integer otherDefenseBonus;

    private Integer prestigePoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "character_expertises", joinColumns = @JoinColumn(name = "character_id"))
    private Set<CharacterExpertise> expertises = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Item> inventory = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Attack> attacks = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ritual> rituals = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Ability> abilities = new HashSet<>();

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("isPinned DESC, createdAt DESC")
    private Set<Note> notes = new HashSet<>();

    public int getMaxLoad() {
        if (this.strength == null || this.strength <= 0) {
            return 2;
        }
        return this.strength * 5;
    }
}
