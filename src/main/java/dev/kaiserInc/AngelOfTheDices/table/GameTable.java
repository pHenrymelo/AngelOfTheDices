package dev.kaiserInc.AngelOfTheDices.table;

import dev.kaiserInc.AngelOfTheDices.character.Character;
import dev.kaiserInc.AngelOfTheDices.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "game_tables")
@Getter
@Setter
public class GameTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gm_user_id", nullable = false)
    private User gm;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "table_players",
            joinColumns = @JoinColumn(name = "table_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> players = new HashSet<>();

    @OneToMany(mappedBy = "gameTable", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Character> characters = new HashSet<>();
}
