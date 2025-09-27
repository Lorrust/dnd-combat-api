package org.example.dndcombatapi.dto;

import java.util.List;

public record BattleResultDTO(
        String winner,
        Integer rounds,
        String finalMessage,
        List<String> battleLog
) {}
