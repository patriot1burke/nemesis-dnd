package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

import java.util.LinkedList;
import java.util.List;

public class AttackDamage {
    int total;
    boolean crit;
    List<Damage> damage = new LinkedList<>();

    public int getTotal() {
        return total;
    }

    public boolean isCrit() {
        return crit;
    }

    public List<Damage> getDamage() {
        return damage;
    }

    public AttackDamage(PlayerCharacter pc, boolean crit, DamageEffect effect) {
        this.crit = crit;
        calculateDamage(pc, effect);
    }

    public AttackDamage(PlayerCharacter pc, boolean crit, List<DamageEffect> effects) {
        this.crit = crit;
        effects.forEach(effect -> calculateDamage(pc, effect));
    }

    public void calculateDamage(PlayerCharacter pc, DamageEffect effect) {
        DiceRoller.Total damageDice = new PlayerCharacterRoller(pc).roll(effect.getDice());

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
        subDmg.setType(effect.getDamageType());
        subDmg.setName(effect.getName());
        damage.add(subDmg);
        total += subDmg.total;

    }

    public static class Damage {
        String name;
        String type;
        int total;
        String expression;
        String description;
        int dc;
        String save;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDc() {
            return dc;
        }

        public void setDc(int dc) {
            this.dc = dc;
        }

        public String getSave() {
            return save;
        }

        public void setSave(String save) {
            this.save = save;
        }
    }
}
