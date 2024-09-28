package org.example.dndcombatapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResultModel {
    private String winner;
    private List<String> battleLog;


    public ResultModel() {
        this.battleLog = new ArrayList<>();
    }

    public void addBattleLog(String logEntry) {
        this.battleLog.add(logEntry);
    }

}
