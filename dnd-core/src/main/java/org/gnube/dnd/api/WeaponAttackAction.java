package org.gnube.dnd.api;

import java.util.LinkedList;
import java.util.List;

public class WeaponAttackAction extends AttackAction {
    List<DamageEffect> damage = new LinkedList<>();

    public List<DamageEffect> getDamage() {
        return damage;
    }

    public void setDamage(List<DamageEffect> damage) {
        this.damage = damage;
    }

    public AttackDamage weaponDamage(boolean crit) {
        return new AttackDamage(pc, crit, damage);
    }
}
