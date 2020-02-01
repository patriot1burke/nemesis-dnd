package org.gnube.dnd.api;

public class BaseAction implements Action {
    String name;
    PlayerCharacter pc;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public PlayerCharacter getPc() {
        return pc;
    }

    public void setPc(PlayerCharacter pc) {
        this.pc = pc;
    }
}
