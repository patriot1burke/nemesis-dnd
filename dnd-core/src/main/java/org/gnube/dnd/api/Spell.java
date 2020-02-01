package org.gnube.dnd.api;

import java.util.List;

public class Spell {
    String name;
    int slot;
    boolean spellAttack;
    String dice;
    String dicePerSlot;
    String damageType;
    String damage;
    String save; // DEX, WIS
    String dc = "dc"; // if not spell dc
    List<LevelDamage> damageAtLevel;

    public static class LevelDamage {
        int level;
        String dice;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDice() {
            return dice;
        }

        public void setDice(String dice) {
            this.dice = dice;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean isSpellAttack() {
        return spellAttack;
    }

    public void setSpellAttack(boolean spellAttack) {
        this.spellAttack = spellAttack;
    }

    public String getDice() {
        return dice;
    }

    public void setDice(String dice) {
        this.dice = dice;
    }

    public String getDicePerSlot() {
        return dicePerSlot;
    }

    public void setDicePerSlot(String dicePerSlot) {
        this.dicePerSlot = dicePerSlot;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public List<LevelDamage> getDamageAtLevel() {
        return damageAtLevel;
    }

    public void setDamageAtLevel(List<LevelDamage> damageAtLevel) {
        this.damageAtLevel = damageAtLevel;
    }

    public void learn(PlayerCharacter pc) {
        if (spellAttack) {
            SpellAttackAction action = new SpellAttackAction();
            action.setSpell(this);
            action.setName(name);
            action.setPc(pc);
            pc.getActions().put(name, action);
        } else if (dice != null) {
            CastAction action = new CastAction();
            action.setSpell(this);
            action.setName(name);
            action.setPc(pc);
            pc.getActions().put(name, action);

        } else if (damage != null) {
            SpellDamageAction action = new SpellDamageAction();
            action.setSpell(this);
            action.setName(name);
            action.setPc(pc);
            pc.getActions().put(name, action);
        }
    }

    public boolean withHigherSlot() {
        return dicePerSlot != null;
    }

    public CastEffect cast(PlayerCharacter pc) {
        CastEffect effect = new CastEffect();
        effect.dice = dice;
        effect.name = name;

        if (save != null) {
            effect.save = save;
            effect.dc = dc;
        }
        return effect;

    }

    public CastEffect castSlot(int castSlot) {
        CastEffect effect = new CastEffect();
        effect.dice = dice;
        effect.name = name;
        addSlotDice(castSlot, effect);
        return effect;

    }

    public DamageEffect damage(PlayerCharacter pc) {
        int level = pc.getLevel();
        DamageEffect effect = new DamageEffect();
        if (damageAtLevel != null) {
            LevelDamage levelDamage = null;
            for (LevelDamage ld : damageAtLevel) {
                if (level >= ld.level) {
                   if (levelDamage == null || ld.level > levelDamage.level) levelDamage = ld;
                 }
            }
            if (levelDamage == null) throw new IllegalArgumentException("Not high enough level.");
            effect.dice = levelDamage.dice;
        } else {
            effect.dice = damage;
        }
        effect.damageType = damageType;
        if (save != null) {
            effect.save = save;
            effect.dc = dc;
        }
        return effect;
    }

    public DamageEffect damageSlot(int castSlot) {
        DamageEffect effect = new DamageEffect();
        effect.damageType = damageType;
        effect.dice = damage;
        addSlotDice(castSlot, effect);
        return effect;
    }

    private void addSlotDice(int castSlot, CastEffect effect) {
        if (dicePerSlot != null) {
            for (int i = this.slot; i < castSlot; i++) {
                effect.dice += "+" + dicePerSlot;
            }
        }
        if (save != null) {
            effect.save = save;
            effect.dc = dc;
        }
    }
}
