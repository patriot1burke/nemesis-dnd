package org.gnube.dnd.api.rolls.modifiers;

import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.RollerContext;

public class RollModifier implements Roll {
    int value;
    String description = "";

    @Override
    public Result roll(RollerContext roller) {
        Result result = new Result();
        result.setValue(value);
        result.setDescription(value + description);
        return result;
    }
}
