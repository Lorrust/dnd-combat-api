package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;

public interface CombatService {
    ResultModel battle(CharacterModel character, CharacterModel characterModel);
}
