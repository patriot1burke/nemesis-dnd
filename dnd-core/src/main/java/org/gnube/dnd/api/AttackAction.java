package org.gnube.dnd.api;

import java.util.LinkedList;
import java.util.List;

public class AttackAction implements Action {
    String name;
    ActionSource source;
    String attackDice;
    boolean saveable;
    int dc;
    List<DamageApplication> damage = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActionSource getSource() {
        return source;
    }

    public void setSource(ActionSource source) {
        this.source = source;
    }

    public String getAttackDice() {
        return attackDice;
    }

    public void setAttackDice(String attackDice) {
        this.attackDice = attackDice;
    }

    public List<DamageApplication> getDamage() {
        return damage;
    }

    public void setDamage(List<DamageApplication> damage) {
        this.damage = damage;
    }

    public boolean isSaveable() {
        return saveable;
    }

    public void setSaveable(boolean saveable) {
        this.saveable = saveable;
    }

    public int getDc() {
        return dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }
}
