package dev.kaiserInc.AngelOfTheDices.character.expertise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/expertises")
public class ExpertiseController {

    @GetMapping
    public ResponseEntity<List<ExpertiseName>> getAllExpertises() {
        List<ExpertiseName> expertises = Arrays.asList(ExpertiseName.values());
        return ResponseEntity.ok(expertises);
    }

}
