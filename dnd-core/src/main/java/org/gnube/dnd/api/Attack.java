package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

public class Attack {
    String dice;
    DiceRoller.Total total;
    AttackAction action;
    boolean crit;

    public Attack(String dice, DiceRoller.Total total, AttackAction action, boolean crit) {
        this.dice = dice;
        this.total = total;
        this.action = action;
        this.crit = crit;
    }

    public String getDice() {
        return dice;
    }

    public DiceRoller.Total getTotal() {
        return total;
    }

    public AttackAction getAction() {
        return action;
    }

    public boolean isCrit() {
        return crit;
    }
}
