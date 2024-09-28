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
        return  "Your character seems good to go." + "\n\n" +
                "Name: " + name + '\n' +
                "Strength: " + strength + "\n" +
                "Dexterity: " + dexterity + "\n" +
                "HP: " + hitPoints + "\n" +
                "AC: " + armorClass;
    }
}
