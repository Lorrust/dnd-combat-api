package org.example.dndcombatapi.controller;

import jakarta.validation.Valid;
import org.example.dndcombatapi.dto.BattleResultDTO;
import org.example.dndcombatapi.dto.UserCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;
import org.example.dndcombatapi.service.CharacterService;
import org.example.dndcombatapi.service.BattleService;
import org.example.dndcombatapi.service.MonsterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battle")
public class BattleController {

    private final BattleService battleService;
    private final CharacterService characterService;
    private final MonsterService monsterService;

    public BattleController(BattleService battleService,
                            CharacterService characterService,
                            MonsterService monsterService) {
        this.battleService = battleService;
        this.characterService = characterService;
        this.monsterService = monsterService;
    }

    @PostMapping("/{monsterName}")
    public ResponseEntity<BattleResultDTO> battle(@PathVariable String monsterName,
                                                  @RequestBody @Valid UserCharacterDTO userCharacterDTO) {
        CharacterModel user = characterService.transformUserToCharacter(userCharacterDTO);
        CharacterModel monster = monsterService.getMonsterAsCharacter(monsterName);

        ResultModel result = battleService.battle(user, monster);

        BattleResultDTO battleResultDTO = new BattleResultDTO(
                result.getWinner(),
                result.getRounds(),
                result.getFinalMessage(),
                result.getBattleLog()
        );

        return ResponseEntity.ok(battleResultDTO);
    }
}
