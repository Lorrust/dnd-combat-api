package org.example.dndcombatapi.service;

import org.example.dndcombatapi.model.CharacterModel;
import org.example.dndcombatapi.model.ResultModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatServiceImpl implements CombatService {

    private static final int BASE_DAMAGE = 6;
    private final Random random = new Random();

    @Override
    public ResultModel battle(CharacterModel user, CharacterModel enemy) {
        ResultModel result = new ResultModel();

        CharacterModel starter = defineInitiative(user, enemy);
        CharacterModel second = (starter == user) ? enemy : user;

        result.addBattleLog("Battle begins!");
        result.addBattleLog(user.getName() + " HP: " + user.getHitPoints() +
                " | " + enemy.getName() + " HP: " + enemy.getHitPoints());

        int rounds = 0;

        while (starter.getHitPoints() > 0 && second.getHitPoints() > 0) {
            rounds++;
            result.addBattleLog("Round " + rounds + " begins!");
            attack(starter, second, result);
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

        result.addBattleLog(
                attacker.getName() + " HP: " + attacker.getHitPoints() +
                        " | " + defender.getName() + " HP: " + defender.getHitPoints()
        );
    }

    private Integer scoreModifier(Integer score) {
        return (score - 10) / 2;
    }
}
