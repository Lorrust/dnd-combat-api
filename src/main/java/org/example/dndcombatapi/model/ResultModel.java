package org.example.dndcombatapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultModel {
    private String winner;
    private List<String> battleLog = new ArrayList<>();
    private String finalMessage;
    private Integer rounds;

    public void addBattleLog(String log) {
        battleLog.add(log);
    }
}
