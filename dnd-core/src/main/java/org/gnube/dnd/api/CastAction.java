package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

import java.util.LinkedList;
import java.util.List;

public class CastAction extends BaseAction {
    Spell spell;

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Cast cast() {
        CastEffect effect = spell.cast(pc);
        return buildCast(effect);
    }

    public Cast cast(int slot) {
        CastEffect effect = spell.castSlot(slot);
        return buildCast(effect);
    }

    private Cast buildCast(CastEffect effect) {
        DiceRoller.Total total = new PlayerCharacterRoller(pc).roll(effect.getDice());
        Cast cast = new Cast();
        cast.total = total.getTotal();
        cast.expression = total.getExpression();
        cast.description = total.getDescription();
        cast.save = effect.save;
        cast.name = spell.name;
        if (effect.dc != null) {
            DiceRoller.Total dcTotal = new PlayerCharacterRoller(pc).roll(effect.getDc());
            cast.dc = dcTotal.getTotal();
        }
        return cast;
    }
}
