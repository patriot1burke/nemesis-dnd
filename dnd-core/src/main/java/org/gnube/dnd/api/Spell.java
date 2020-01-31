package org.gnube.dnd.api;

import java.util.List;

public class Spell {
    String name;
    int level;
    boolean cantrip;
    String dice;
    String dicePerSlot;
    String damageType;
    String damage;
    String damagePerSlot;
    String save; // DEX, WIS
    String dc; // if not spell dc
    List<LevelDamage> atLevelDamage;

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

    public void learn(PlayerCharacter pc) {

    }
}
