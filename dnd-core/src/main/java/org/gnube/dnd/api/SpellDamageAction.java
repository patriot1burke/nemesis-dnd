package org.gnube.dnd.api;

public class SpellDamageAction extends BaseAction {
    Spell spell;

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public AttackDamage spellDamage(int slot) {
        return new AttackDamage(pc, false, spell.damageSlot(slot));
    }
}
