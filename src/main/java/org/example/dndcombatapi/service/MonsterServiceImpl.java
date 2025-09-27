package org.example.dndcombatapi.service;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterServiceImpl implements MonsterService {

    private final DndApiService dndApiService;
    private final CharacterService characterService;

    public MonsterServiceImpl(DndApiService dndApiService, CharacterService characterService) {
        this.dndApiService = dndApiService;
        this.characterService = characterService;
    }

    @Override
    public List<String> getMonsterNames(Integer page) {
        return dndApiService.getMonsterNames(page);
    }

    @Override
    public CharacterModel getMonsterAsCharacter(String monsterName) {
        MonsterCharacterDTO monsterData = dndApiService.getMonster(monsterName);
        return characterService.transformMonsterToCharacter(monsterData);
    }

    @Override
    public MonsterCharacterDTO getMonsterRaw(String monsterName) {
        return dndApiService.getMonster(monsterName);
    }
}
