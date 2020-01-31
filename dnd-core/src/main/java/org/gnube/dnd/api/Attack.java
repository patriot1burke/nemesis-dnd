package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

public class Attack {
    public static final String BASE_ATTACK_DICE="1d20";

    String dice;
    DiceRoller.Total total;
    boolean crit;

    public Attack(String dice, DiceRoller.Total total, boolean crit) {
        this.dice = dice;
        this.total = total;
        this.crit = crit;
    }

    public String getDice() {
        return dice;
    }

    public DiceRoller.Total getTotal() {
        return total;
    }


    public boolean isCrit() {
        return crit;
    }
}
