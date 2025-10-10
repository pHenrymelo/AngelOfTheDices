package dev.kaiserInc.AngelOfTheDices.character.affinity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/affinities")
public class AffinityController {
    @GetMapping
    public ResponseEntity<List<Affinity>> getAllAffinities() {
        return ResponseEntity.ok(Arrays.asList(Affinity.values()));
    }
}