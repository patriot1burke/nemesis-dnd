package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

public class AttackAction extends BaseAction {
    String attackDice;

    public String getAttackDice() {
        return attackDice;
    }

    public void setAttackDice(String attackDice) {
        this.attackDice = attackDice;
    }

    public Attack attack() {
        String attackDice = Attack.BASE_ATTACK_DICE;
        DiceRoller.Total roll = new PlayerCharacterRoller(pc).roll(attackDice);
        return new Attack(attackDice, roll, pc.isCrit(roll.getTotal()));
    }

}
