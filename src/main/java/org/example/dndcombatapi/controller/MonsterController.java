package org.example.dndcombatapi.controller;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.service.MonsterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monsters")
public class MonsterController {

    private final MonsterService monsterService;

    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/names/{page}")
    public ResponseEntity<List<String>> getMonsterNames(@PathVariable Integer page) {
        List<String> monsters = monsterService.getMonsterNames(page);
        return ResponseEntity.ok(monsters);
    }

    @GetMapping("/{monsterName}")
    public ResponseEntity<MonsterCharacterDTO> getMonster(@PathVariable String monsterName) {
        MonsterCharacterDTO monster = monsterService.getMonsterRaw(monsterName);
        return ResponseEntity.ok(monster);
    }
}


