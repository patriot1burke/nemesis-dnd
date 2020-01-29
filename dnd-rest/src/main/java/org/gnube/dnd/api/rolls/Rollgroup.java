package org.gnube.dnd.api.rolls;

import java.util.List;

public class Rollgroup implements Roll {
    List<Roll> rollers;

    @Override
    public Result roll(RollerContext roller) {
        boolean first = true;
        Result result = new Result();
        StringBuffer desc = new StringBuffer();
        for (Roll roll : rollers) {
            Result subResult = roll.roll(roller);
            if (first) first = false;
            else {
                desc.append(subResult.value >= 0 ? "+" : "-");
            }
            desc.append(subResult.description);
            result.value += subResult.value;
            result.subResults.add(subResult);
        }
        result.description = desc.toString();
        return result;
    }
}
