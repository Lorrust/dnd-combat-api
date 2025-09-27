package org.example.dndcombatapi.controller;

import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;
import org.example.dndcombatapi.service.CharacterService;
import org.example.dndcombatapi.service.CombatService;
import org.example.dndcombatapi.service.DndApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class CombatController {

    @Autowired
    private CombatService combatService;

    @Autowired
    private DndApiService dndApiService;

    @Autowired
    private CharacterService characterService;

    @GetMapping("/example")
    public CharacterModel exampleCharacter() {
        return characterService.exampleCharacter();
    }

    @GetMapping("/monsters/names/{page}")
    public List<String> getMonsterNames(@PathVariable Integer page) {
        return dndApiService.getMonsterNames(page);
    }

    @GetMapping("/monsters/{monsterName}")
    public CharacterModel monsterStats(@PathVariable String monsterName) {
        Map<String, Object> monsterData = dndApiService.getMonster(monsterName);
        return characterService.transformMonsterToCharacter(monsterData);
    }

    @PostMapping("/check")
    public String test(@RequestBody CharacterModel characterModel) {
        return characterService.checkStats(characterModel);
    }

    @PostMapping("/battle/{monsterName}")
    public ResultModel battle(@RequestBody CharacterModel character, @PathVariable String monsterName) {
        Map<String, Object> monsterData = dndApiService.getMonster(monsterName);
        return combatService.battle(character, characterService.transformMonsterToCharacter(monsterData));
}

}
