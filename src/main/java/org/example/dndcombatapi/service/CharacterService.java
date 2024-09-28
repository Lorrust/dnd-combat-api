package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;

import java.util.Map;

public interface CharacterService {

    CharacterModel transformMonsterToCharacter(Map<String, Object> monsterData);

    String checkStats(CharacterModel characterModel);

    CharacterModel exampleCharacter();
}
