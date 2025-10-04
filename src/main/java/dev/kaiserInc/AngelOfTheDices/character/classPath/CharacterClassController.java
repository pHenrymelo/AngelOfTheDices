package dev.kaiserInc.AngelOfTheDices.character.classPath;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class CharacterClassController {

    @GetMapping
    public ResponseEntity<List<CharacterClass>> getAllCharacterClasses() {
        return ResponseEntity.ok(Arrays.asList(CharacterClass.values()));
    }
}
