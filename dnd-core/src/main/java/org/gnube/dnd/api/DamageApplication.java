package org.gnube.dnd.api;

public class DamageApplication {
    String name;
    String damageType;
    String damageDice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }
}
