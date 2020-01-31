package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayerCharacter {
    Map<String, Action> actions = new HashMap<>();
    List<InventoryItem> inventory = new LinkedList<>();
    int strength;
    int dexterity;
    int constitution;
    int intelligence;
    int wisdom;
    int charisma;
    int proficiency;
    Set<String> proficiencies = new HashSet<>();

    public Set<String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(Set<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public Map<String, Action> actions() {
        return actions;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    public void equip(InventoryItem item) {
        item.equip(this);
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    protected boolean isCrit(int value) {
        return value == 20;
    }

    public Attack attack(AttackAction action) {
        String attackDice = "1d20";
        if (action.getAttackDice() != null) attackDice += action.getAttackDice();
        DiceRoller.Total roll = new PlayerCharacterRoller(this).roll(attackDice);
        return new Attack(attackDice, roll, action, isCrit(roll.getTotal()));
    }

    public AttackDamage damage(Attack attack) {
        AttackDamage damage = new AttackDamage();
        damage.crit = attack.isCrit();
        for (DamageApplication app : attack.getAction().getDamage()) {
            DiceRoller.Total damageDice = new PlayerCharacterRoller(this).roll(app.damageDice);

            AttackDamage.Damage subDmg = new AttackDamage.Damage();
            subDmg.total = damageDice.getTotal();
            subDmg.expression = damageDice.getExpression();
            subDmg.description = damageDice.getDescription();

            if (attack.isCrit() && !damageDice.getDiceRolls().isEmpty()) {
                subDmg.expression += "+Crit(";
                subDmg.description += "+Crit(";
                List<DiceRoller.DiceRoll> critRolls = new LinkedList<>();
                boolean firstRoll = true;
                for (DiceRoller.DiceRoll r : damageDice.getDiceRolls()) {
                    DiceRoller.DiceRoll newRoll = r.newRoll();
                    subDmg.total += newRoll.getTotal();
                    critRolls.add(newRoll);
                    if (firstRoll) firstRoll = false;
                    else {
                        subDmg.expression += "+";
                        subDmg.description += "+";
                    }
                    subDmg.expression += newRoll.getDice().getExpression();
                    subDmg.description += newRoll.getDescription();
                }
                subDmg.expression += ")";
                subDmg.description += ")";
            }
            subDmg.setType(app.getDamageType());
            subDmg.setName(app.getName());
            damage.damage.add(subDmg);
            damage.total += subDmg.total;
        }
        return damage;
    }

}
