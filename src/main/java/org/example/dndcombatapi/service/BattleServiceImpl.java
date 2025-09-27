package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BattleServiceImpl implements BattleService {

    private static final int BASE_DAMAGE = 6;
    private final Random random = new Random();

    public ResultModel battle(CharacterModel user, CharacterModel enemy) {
        ResultModel result = new ResultModel();

        final int userMaxHp = user.getHitPoints();
        final int enemyMaxHp = enemy.getHitPoints();

        CharacterModel starter = defineInitiative(user, enemy);
        CharacterModel second = (starter == user) ? enemy : user;

        int rounds = 0;

        result.addBattleLog("The battle begins!");

        result.addBattleLog(
                formatHpLog(user, userMaxHp, enemy, enemyMaxHp)
        );

        while (starter.getHitPoints() > 0 && second.getHitPoints() > 0) {
            rounds++;
            result.addBattleLog("Round " + rounds + "!");

            attack(starter, second, result);
            result.addBattleLog(
                    formatHpLog(user, userMaxHp, enemy, enemyMaxHp)
            );

            if (second.getHitPoints() <= 0) break;

            attack(second, starter, result);
        }

        result.setRounds(rounds);

        String winner = starter.getHitPoints() > 0 ? starter.getName() : second.getName();
        result.setWinner(winner);

        String finalMessage = winner + " won the battle in " + rounds + " rounds!";
        result.setFinalMessage(finalMessage);
        result.addBattleLog(finalMessage);

        return result;
    }

    private String formatHpLog(CharacterModel user, int userMaxHp,
                               CharacterModel enemy, int enemyMaxHp) {
        return user.getName() + " (" + user.getHitPoints() + "/" + userMaxHp + " HP) | " +
                enemy.getName() + " (" + enemy.getHitPoints() + "/" + enemyMaxHp + " HP)";
    }

    private Integer rollDice(Integer faces) {
        return random.nextInt(faces) + 1;
    }

    private CharacterModel defineInitiative(CharacterModel user, CharacterModel enemy) {
        int userRoll = rollDice(20) + scoreModifier(user.getDexterity());
        int enemyRoll = rollDice(20) + scoreModifier(enemy.getDexterity());
        return (userRoll >= enemyRoll) ? user : enemy;
    }

    private void attack(CharacterModel attacker, CharacterModel defender, ResultModel result) {
        int attackRoll = rollDice(20) + scoreModifier(attacker.getStrength());
        if (attackRoll > defender.getArmorClass()) {
            int damage = rollDice(BASE_DAMAGE) + scoreModifier(attacker.getStrength());
            defender.setHitPoints(Math.max(defender.getHitPoints() - damage, 0));
            result.addBattleLog(attacker.getName() + " attacked " + defender.getName() + " for " + damage + " damage.");
        } else {
            result.addBattleLog(attacker.getName() + " missed.");
        }
    }

    private Integer scoreModifier(Integer score) {
        return (score - 10) / 2;
    }
}
