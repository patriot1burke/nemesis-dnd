package org.gnube.dnd.api;

public class DamageEffect extends CastEffect {
    String damageType;

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

}
