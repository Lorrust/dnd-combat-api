package org.example.dndcombatapi.service;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.dto.UserCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;

public interface CharacterService {

    CharacterModel transformMonsterToCharacter(MonsterCharacterDTO monsterData);

    CharacterModel transformUserToCharacter(UserCharacterDTO userCharacterDTO);

    CharacterModel exampleCharacter();
}
