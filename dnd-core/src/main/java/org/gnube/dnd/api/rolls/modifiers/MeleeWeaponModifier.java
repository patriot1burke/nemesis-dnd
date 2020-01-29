package org.gnube.dnd.api.rolls.modifiers;

import org.gnube.dnd.api.Model;
import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.RollerContext;

public class MeleeWeaponModifier implements Roll {

    @Override
    public Result roll(RollerContext roller) {
        if (!(roller.roller() instanceof Model.Actor)) throw new RuntimeException("Illegal actor for roll");
        Model.Actor actor = (Model.Actor)roller.roller();

        Result result = new Result();
        int strength = actor.getStrength().getModifier();
        result.setValue(strength);
        result.setDescription(strength + "[STR]");
        return result;
    }
}
