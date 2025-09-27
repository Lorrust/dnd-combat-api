package org.example.dndcombatapi.service;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;

import java.util.List;

public interface DndApiService {

    MonsterCharacterDTO getMonster(String monsterName);

    List<String> getMonsterNames(Integer page);
}
