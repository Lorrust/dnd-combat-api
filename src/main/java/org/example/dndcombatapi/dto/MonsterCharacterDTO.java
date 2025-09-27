package org.example.dndcombatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonsterCharacterDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("strength")
    private Integer strength;

    @JsonProperty("dexterity")
    private Integer dexterity;

    @JsonProperty("hit_points")
    private Integer hitPoints;

    @JsonProperty("armor_class")
    private Integer armorClass;
}
