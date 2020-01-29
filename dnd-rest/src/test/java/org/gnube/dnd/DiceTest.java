package org.gnube.dnd;

import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.dice.TopDice;
import org.junit.jupiter.api.Test;

public class DiceTest {

    @Test
    public void testTopDice() {
        TopDice top = new TopDice(4, 6, 3);
        Roll.Result result = top.roll(null);
        System.out.println("Total: " + result.getValue());
        System.out.println("Desc: " + result.getDescription());
    }
}
