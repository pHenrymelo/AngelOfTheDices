package dev.kaiserInc.AngelOfTheDices.rules;

import dev.kaiserInc.AngelOfTheDices.rules.dto.GameRulesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rules")
public class GameRulesController {
    private final GameRulesService gameRulesService;

    @Autowired
    public GameRulesController(GameRulesService gameRulesService) {
        this.gameRulesService = gameRulesService;
    }

    @GetMapping
    public ResponseEntity<GameRulesDTO> getAllGameRules() {
        GameRulesDTO rules = gameRulesService.getAllGameRules();
        return ResponseEntity.ok(rules);
    }
}
