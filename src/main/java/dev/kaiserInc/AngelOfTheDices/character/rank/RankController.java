package dev.kaiserInc.AngelOfTheDices.character.rank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ranks")
public class RankController {
    @GetMapping
    public ResponseEntity<List<Rank>> getAllRanks() {
        return ResponseEntity.ok(Arrays.asList(Rank.values()));
    }
}
