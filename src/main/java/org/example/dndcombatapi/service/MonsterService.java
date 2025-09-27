package org.example.dndcombatapi.service;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;

import java.util.List;

public interface MonsterService {
    List<String> getMonsterNames(Integer page);
    CharacterModel getMonsterAsCharacter(String monsterName);
    MonsterCharacterDTO getMonsterRaw(String monsterName);
}
