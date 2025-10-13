package dev.kaiserInc.AngelOfTheDices.character.ritual;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kaiserInc.AngelOfTheDices.character.Character;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "rituals")
@Getter
@Setter
public class Ritual {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Element element;

    @Column(nullable = false)
    private Circle circle;

    @Column(nullable = false)
    private ExecutionType execution;

    @Column(nullable = false)
    private RangeType range;

    private String target;
    private String duration;
    private String resistance;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id", nullable = false)
    @JsonIgnore
    private Character character;
}
