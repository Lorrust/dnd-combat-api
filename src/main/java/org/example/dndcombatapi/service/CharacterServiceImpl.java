package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class CharacterServiceImpl implements CharacterService {

    public CharacterModel transformMonsterToCharacter(Map<String, Object> monsterData) {
        CharacterModel character = new CharacterModel();

        character.setName((String) monsterData.get("name"));
        character.setStrength((Integer) monsterData.get("strength"));
        character.setDexterity((Integer) monsterData.get("dexterity"));
        character.setHitPoints((Integer) monsterData.get("hit_points"));
        character.setArmorClass((Integer) monsterData.get("armor_class"));

        return character;
    }

    @Override
    public String checkStats(CharacterModel characterModel) {
        return characterModel.toString();
    }

    @Override
    public CharacterModel exampleCharacter() {
        return new CharacterModel("Kaya", 10, 7, 11, 12);
    }
}
