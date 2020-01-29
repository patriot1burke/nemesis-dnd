package org.gnube.dnd.api.rolls.dice;

import org.gnube.dnd.api.Model;
import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.RollerContext;

public class Dice implements Roll {
    int num;
    int die;  //d8, d10, d20, etc.

    public Dice(int num, int die) {
        this.num = num;
        this.die = die;
    }

    @Override
    public Result roll(RollerContext roller) {
        Result result = new Result();

        StringBuffer desc = new StringBuffer("Rolling ").append(num).append("d").append(die).append("(");
        for (int i = 0; i < num; i++) {
            Result sub = new Result();
            sub.setValue(Roll.random.nextInt(die) + 1);
            sub.setDescription(Integer.toString(sub.getValue()));
            result.setValue(result.getValue() + sub.getValue());
            if (i > 0) desc.append("+");
            desc.append(sub.getValue());
            result.getSubResults().add(sub);
        }
        desc.append(")");
        result.setDescription(desc.toString());
        return result;
    }
}
