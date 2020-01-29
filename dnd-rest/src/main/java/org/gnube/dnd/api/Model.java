package org.gnube.dnd.api;

import org.gnube.dnd.api.rolls.Roll;
import org.gnube.dnd.api.rolls.Roller;
import org.gnube.dnd.api.rolls.RollerContext;
import org.gnube.dnd.api.rolls.dice.Dice;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public interface Model {

    public class Creature implements Roller {
        List<Attack> attacks;

        public List<Attack> getAttacks() {
            return attacks;
        }

        public void setAttacks(List<Attack> attacks) {
            this.attacks = attacks;
        }
    }

    public class Attribute {
        int value;
        int modifier;

        public int getValue() {
            return value;
        }

        public int getModifier() {
            return modifier;
        }
    }

    public class Actor extends Creature {
        int level;
        String name;
        Attribute strength;
        Attribute dexterity;
        Attribute constitution;
        Attribute intelligence;
        Attribute wisdom;
        Attribute charisma;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Attribute getStrength() {
            return strength;
        }

        public void setStrength(Attribute strength) {
            this.strength = strength;
        }

        public Attribute getDexterity() {
            return dexterity;
        }

        public void setDexterity(Attribute dexterity) {
            this.dexterity = dexterity;
        }

        public Attribute getConstitution() {
            return constitution;
        }

        public void setConstitution(Attribute constitution) {
            this.constitution = constitution;
        }

        public Attribute getIntelligence() {
            return intelligence;
        }

        public void setIntelligence(Attribute intelligence) {
            this.intelligence = intelligence;
        }

        public Attribute getWisdom() {
            return wisdom;
        }

        public void setWisdom(Attribute wisdom) {
            this.wisdom = wisdom;
        }

        public Attribute getCharisma() {
            return charisma;
        }

        public void setCharisma(Attribute charisma) {
            this.charisma = charisma;
        }
    }

    public class PlayerCharacter extends Actor {
        int experience;
        int proficiency;

        public int getExperience() {
            return experience;
        }

        public void setExperience(int experience) {
            this.experience = experience;
        }

        public int getProficiency() {
            return proficiency;
        }

        public void setProficiency(int proficiency) {
            this.proficiency = proficiency;
        }
    }

    public class NPC extends Actor {

    }

    public class Attack {
        String name;
        String description;
        AttackRoller attackRoller;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public AttackRoller getAttackRoller() {
            return attackRoller;
        }

        public void setAttackRoller(AttackRoller attackRoller) {
            this.attackRoller = attackRoller;
        }
    }

    public class WeaponAttack extends Attack {

    }

    public class SpellAttack extends Attack {
        boolean pumpable;
        int minLevelCast;
        int maxLevelCast;

        public boolean isPumpable() {
            return pumpable;
        }

        public void setPumpable(boolean pumpable) {
            this.pumpable = pumpable;
        }

        public int getMinLevelCast() {
            return minLevelCast;
        }

        public void setMinLevelCast(int minLevelCast) {
            this.minLevelCast = minLevelCast;
        }

        public int getMaxLevelCast() {
            return maxLevelCast;
        }

        public void setMaxLevelCast(int maxLevelCast) {
            this.maxLevelCast = maxLevelCast;
        }
    }

    interface ActionBuilder {
        void applyActions(Actor character);
    }


    public class Weapon {
        String name;
        List<Attack> attacks;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Spell {
        String name;
        boolean pumpable;
        int minLevelCast;
        int maxLevelCast;
        AttackRoller attack;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPumpable() {
            return pumpable;
        }

        public void setPumpable(boolean pumpable) {
            this.pumpable = pumpable;
        }

        public int getMinLevelCast() {
            return minLevelCast;
        }

        public void setMinLevelCast(int minLevelCast) {
            this.minLevelCast = minLevelCast;
        }

        public int getMaxLevelCast() {
            return maxLevelCast;
        }

        public void setMaxLevelCast(int maxLevelCast) {
            this.maxLevelCast = maxLevelCast;
        }

        public AttackRoller getAttack() {
            return attack;
        }

        public void setAttack(AttackRoller attack) {
            this.attack = attack;
        }
    }

    public class AttackRoller {
        Roll attack;
        List<DamageRoller> damage;

        public Roll getAttack() {
            return attack;
        }

        public void setAttack(Roll attack) {
            this.attack = attack;
        }

        public List<DamageRoller> getDamage() {
            return damage;
        }

        public void setDamage(List<DamageRoller> damage) {
            this.damage = damage;
        }
    }

    public class DamageRoller {
        String type;
        Roll damage;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Roll getDamage() {
            return damage;
        }

        public void setDamage(Roll damage) {
            this.damage = damage;
        }
    }


}
