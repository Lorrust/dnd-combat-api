package org.example.dndcombatapi.service;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.dto.UserCharacterDTO;
import org.example.dndcombatapi.model.CharacterModel;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Override
    public CharacterModel transformUserToCharacter(UserCharacterDTO dto) {
        return new CharacterModel(
                dto.getName(),
                dto.getStrength(),
                dto.getDexterity(),
                dto.getHitPoints(),
                dto.getArmorClass()
        );
    }

    @Override
    public CharacterModel transformMonsterToCharacter(MonsterCharacterDTO dto) {
        return new CharacterModel(
                dto.getName(),
                dto.getStrength(),
                dto.getDexterity(),
                dto.getHitPoints(),
                dto.getArmorClass()
        );
    }

    @Override
    public CharacterModel exampleCharacter() {
        return new CharacterModel("Kaya", 10, 7, 11, 12);
    }
}
