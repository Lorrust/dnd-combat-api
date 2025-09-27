package org.example.dndcombatapi.controller;

import jakarta.validation.Valid;
import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.dto.UserCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;
import org.example.dndcombatapi.service.CharacterService;
import org.example.dndcombatapi.service.CombatService;
import org.example.dndcombatapi.service.DndApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CombatController {

    private final CombatService combatService;
    private final DndApiService dndApiService;
    private final CharacterService characterService;

    public CombatController(CombatService combatService,
                            DndApiService dndApiService,
                            CharacterService characterService) {
        this.combatService = combatService;
        this.dndApiService = dndApiService;
        this.characterService = characterService;
    }

    @GetMapping("/monsters/names/{page}")
    public ResponseEntity<List<String>> getMonsterNames(@PathVariable Integer page) {
        return ResponseEntity.ok(dndApiService.getMonsterNames(page));
    }

    @GetMapping("/monsters/{monsterName}")
    public ResponseEntity<CharacterModel> monsterStats(@PathVariable String monsterName) {
        MonsterCharacterDTO monsterData = dndApiService.getMonster(monsterName);
        CharacterModel monster = characterService.transformMonsterToCharacter(monsterData);
        return ResponseEntity.ok(monster);
    }

    @PostMapping("/battle/{monsterName}")
    public ResponseEntity<ResultModel> battle(@RequestBody @Valid UserCharacterDTO userCharacterDTO,
                                              @PathVariable String monsterName) {
        CharacterModel userCharacter = characterService.transformUserToCharacter(userCharacterDTO);

        MonsterCharacterDTO monsterData = dndApiService.getMonster(monsterName);
        CharacterModel monsterCharacter = characterService.transformMonsterToCharacter(monsterData);

        ResultModel battleResult = combatService.battle(userCharacter, monsterCharacter);

        return ResponseEntity.status(HttpStatus.OK).body(battleResult);
    }
}
