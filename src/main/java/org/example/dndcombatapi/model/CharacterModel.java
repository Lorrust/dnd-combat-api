package org.example.dndcombatapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterModel {
    private String name;
    private Integer strength;
    private Integer dexterity;
    private Integer hitPoints;
    private Integer armorClass;

    @Override
    public String toString() {
        return String.format(
                "Your character looks like this:\n\nName: %s\nStrength: %d\nDexterity: %d\nHit Points: %d\nArmor Class: %d",
                name, strength, dexterity, hitPoints, armorClass
        );
    }
}
