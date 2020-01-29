package org.gnube.dnd.api.rolls.dice;

import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.RollerContext;

import java.util.Collections;

public class TopDice implements Roll {
    int top;
    Dice dice;

    public TopDice(int num, int die, int top) {
        this.top = top;
        dice = new Dice(num, die);
    }

    @Override
    public Result roll(RollerContext roller) {
        Result result = new Result();
        Result diceResult = dice.roll(roller);
        Collections.sort(diceResult.getSubResults(), ROLL_RESULT_COMPARATOR);
        StringBuffer desc = new StringBuffer("Rolling ").append(dice.num).append("d").append(dice.die).append("(");
        for (int i = 0; i < dice.num; i++) {
            Result sub = diceResult.getSubResults().get(i);
            if (i < top) {
                result.setValue(result.getValue() + sub.getValue());
                if (i > 0) desc.append("+");
                desc.append(sub.getValue());
            } else if (i == top) {
                desc.append(" Takeaway[").append(sub.getValue());
            } else {
                desc.append(" ").append(sub.getValue());
            }
        }
        if (top < dice.num) desc.append("] ");
        desc.append(")");
        result.setDescription(desc.toString());
        return result;
    }
}
