package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;

import java.util.List;
import java.util.Map;

public interface DndApiService {

    Map<String, Object> getMonster(String monsterName);

    List<String> getMonsterNames(Integer page);
}
