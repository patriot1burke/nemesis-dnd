package org.gnube.dnd.api;

public class SpellAttackAction extends AttackAction {
    Spell spell;

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public AttackDamage spellDamage(boolean crit) {
        return new AttackDamage(pc, crit, spell.damage(pc));
    }

    public AttackDamage spellDamage(boolean crit, int slot) {
        return new AttackDamage(pc, crit, spell.damageSlot(slot));
    }
}
