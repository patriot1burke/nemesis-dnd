package org.gnube.dnd.api;

import java.util.LinkedList;
import java.util.List;

public class AttackModifier {
    String name;
    String attackModifier;
    String damageModifier;
    List<DamageApplication> additionalDamage = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(String attackModifier) {
        this.attackModifier = attackModifier;
    }

    public String getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(String damageModifier) {
        this.damageModifier = damageModifier;
    }

    public List<DamageApplication> getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(List<DamageApplication> additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public boolean isApplicable(Effect action) {
        return true;
    }
}
