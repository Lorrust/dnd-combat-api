package org.example.dndcombatapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCharacterDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Strength is required")
    @Min(value = 1, message = "Strength must be at least 1")
    private Integer strength;

    @NotNull(message = "Dexterity is required")
    @Min(value = 1, message = "Dexterity must be at least 1")
    private Integer dexterity;

    @NotNull(message = "Hit points are required")
    @Min(value = 1, message = "Hit points must be at least 1")
    private Integer hitPoints;

    @NotNull(message = "Armor class is required")
    @Min(value = 1, message = "Armor class must be at least 1")
    private Integer armorClass;
}

