package org.gnube.dnd.api;

import org.gnube.dice.visitor.DiceRoller;

public class PlayerCharacterRoller extends DiceRoller {
    PlayerCharacter pc;

    public PlayerCharacterRoller(PlayerCharacter pc) {
        this.pc = pc;
    }

    @Override
    protected int getVariableValue(String variable) {
        if (variable.toLowerCase().startsWith("p")) {
            return pc.getProficiency();
        }
        if (variable.toLowerCase().startsWith("str")) {
            return Attribute.modifier(pc.getStrength());
        }
        if (variable.toLowerCase().startsWith("dex")) {
            return Attribute.modifier(pc.getDexterity());
        }
        if (variable.toLowerCase().startsWith("con")) {
            return Attribute.modifier(pc.getConstitution());
        }
        if (variable.toLowerCase().startsWith("int")) {
            return Attribute.modifier(pc.getIntelligence());
        }
        if (variable.toLowerCase().startsWith("wis")) {
            return Attribute.modifier(pc.getWisdom());
        }
        if (variable.toLowerCase().startsWith("cha")) {
            return Attribute.modifier(pc.getCharisma());
        }
        throw new IllegalArgumentException("Unknown variable: " + variable);
    }
}
