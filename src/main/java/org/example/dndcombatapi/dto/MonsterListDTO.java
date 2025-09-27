package org.example.dndcombatapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonsterListDTO {
    private List<MonsterSummaryDTO> results;
}
