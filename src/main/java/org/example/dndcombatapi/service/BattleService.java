package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;

public interface BattleService {
    ResultModel battle(CharacterModel user, CharacterModel enemy);
}
