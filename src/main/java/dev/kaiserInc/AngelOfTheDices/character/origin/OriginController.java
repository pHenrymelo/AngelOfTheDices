package dev.kaiserInc.AngelOfTheDices.character.origin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/origins")
public class OriginController {

    @GetMapping
    public ResponseEntity<List<Origin>> getAllOrigins() {
        return ResponseEntity.ok(Arrays.asList(Origin.values()));
    }
}
