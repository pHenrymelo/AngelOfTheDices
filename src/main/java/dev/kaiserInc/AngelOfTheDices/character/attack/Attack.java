package dev.kaiserInc.AngelOfTheDices.character.attack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.character.expertise.Attribute;
import dev.kaiserInc.AngelOfTheDices.character.expertise.ExpertiseName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "attacks")
@Getter
@Setter
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AttackType type;

    @Enumerated(EnumType.STRING)
    private Attribute testAttribute;
    @Enumerated(EnumType.STRING)
    private ExpertiseName testExpertise;
    private int testBonus;

    @Enumerated(EnumType.STRING)
    private DiceType damageDiceType;
    private int damageDiceQuantity;
    private int damageBonus;

    private int criticalThreshold;
    private int criticalMultiplier;

    @Enumerated(EnumType.STRING)
    private RangeType range;

    private String special;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Character character;

}
