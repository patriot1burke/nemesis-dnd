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

    public static class Damage {
        String name;
        String type;
        int total;
        String expression;
        String description;

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
    }
}
