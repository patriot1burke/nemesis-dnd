package org.gnube.dnd.api.rolls.modifiers;

import org.gnube.dnd.api.Model;
import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.RollerContext;

public class ProficiencyModifier implements Roll {

    @Override
    public Result roll(RollerContext roller) {
        if (!(roller.roller() instanceof Model.PlayerCharacter)) throw new RuntimeException("Illegal actor for roll");
        Model.PlayerCharacter actor = (Model.PlayerCharacter)roller.roller();

        Result result = new Result();
        int proficiency = actor.getProficiency();
        result.setValue(proficiency);
        result.setDescription(actor.getProficiency() + "[P]");
        return result;
    }
}
