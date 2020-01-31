package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

import java.util.LinkedList;
import java.util.List;

public class AttackAction implements Action {
    String name;
    String attackDice;
    PlayerCharacter pc;

    List<DamageApplication> damage = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public PlayerCharacter getPc() {
        return pc;
    }

    public void setPc(PlayerCharacter pc) {
        this.pc = pc;
    }

    public Attack attack() {
        String attackDice = Attack.BASE_ATTACK_DICE;
        DiceRoller.Total roll = new PlayerCharacterRoller(pc).roll(attackDice);
        return new Attack(attackDice, roll, pc.isCrit(roll.getTotal()));
    }

    public AttackDamage damage(boolean crit) {
        AttackDamage damage = new AttackDamage();
        damage.crit = crit;
        for (DamageApplication app : getDamage()) {
            DiceRoller.Total damageDice = new PlayerCharacterRoller(pc).roll(app.getDamageDice());

            AttackDamage.Damage subDmg = new AttackDamage.Damage();
            subDmg.total = damageDice.getTotal();
            subDmg.expression = damageDice.getExpression();
            subDmg.description = damageDice.getDescription();

            if (crit && !damageDice.getDiceRolls().isEmpty()) {
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
