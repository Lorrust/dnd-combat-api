package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatServiceImpl implements CombatService {

    private static final int BASE_DAMAGE = 6;

    @Override
    public ResultModel battle(CharacterModel user, CharacterModel enemy) {
        ResultModel result = new ResultModel();
        CharacterModel starter = defineInitiative(user, enemy);
        CharacterModel second = (starter == user) ? enemy : user;

        while (starter.getHitPoints() > 0 && second.getHitPoints() > 0) {
            attack(starter, second, result);
            if (second.getHitPoints() <= 0) {
                break;
            }
            attack(second, starter, result);
        }

        result.setWinner(starter.getHitPoints() > 0 ? starter.getName() : second.getName());
        result.addBattleLog(starter.getHitPoints() > 0 ? starter.getName() + " defeated " + second.getName() + "." : second.getName() + " defeated " + starter.getName() + ".");

        return result;
    }

    public Integer rollDice(Integer faces) {
        Random random = new Random();
        return random.nextInt(faces) + 1;
    }

    public CharacterModel defineInitiative(CharacterModel user, CharacterModel enemy) {
        int userRoll = rollDice(20) + scoreModifier(user.getDexterity());
        int enemyRoll = rollDice(20) + scoreModifier(enemy.getDexterity());
        return (userRoll >= enemyRoll) ? user : enemy;
    }

    public void attack(CharacterModel attacker, CharacterModel defender, ResultModel result) {
        int attackRoll = rollDice(20) + scoreModifier(attacker.getStrength());
        if (attackRoll > defender.getArmorClass()) {
            int damage = rollDice(BASE_DAMAGE) + scoreModifier(attacker.getStrength());
            defender.setHitPoints(Math.max(defender.getHitPoints() - damage, 0));
            result.addBattleLog(attacker.getName() + " attacked " + defender.getName() + " for " + damage + " damage.");
        } else {
            result.addBattleLog(attacker.getName() + " missed.");
        }
    }

    public Integer scoreModifier(Integer score) {
        return (score - 10) / 2;
    }
}
