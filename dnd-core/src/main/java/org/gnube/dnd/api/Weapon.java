package org.gnube.dnd.api;

public class Weapon implements InventoryItem {
    String name;
    String type;
    boolean martial;
    boolean simple;
    boolean reach;
    boolean finesse;
    boolean magical;
    boolean heavy;
    boolean light;
    String damageType;
    String oneHanded;
    String twoHanded;
    String range;

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

    public boolean isMartial() {
        return martial;
    }

    public void setMartial(boolean martial) {
        this.martial = martial;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    public boolean isReach() {
        return reach;
    }

    public void setReach(boolean reach) {
        this.reach = reach;
    }

    public boolean isFinesse() {
        return finesse;
    }

    public void setFinesse(boolean finesse) {
        this.finesse = finesse;
    }

    public boolean isMagical() {
        return magical;
    }

    public void setMagical(boolean magical) {
        this.magical = magical;
    }

    public boolean isHeavy() {
        return heavy;
    }

    public void setHeavy(boolean heavy) {
        this.heavy = heavy;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getOneHanded() {
        return oneHanded;
    }

    public void setOneHanded(String oneHanded) {
        this.oneHanded = oneHanded;
    }

    public String getTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(String twoHanded) {
        this.twoHanded = twoHanded;
    }

    boolean proficient(PlayerCharacter pc) {
        return (martial && pc.getProficiencies().contains(Proficiencies.MARTIAL_WEAPONS))
                || (simple && pc.getProficiencies().contains((Proficiencies.SIMPLE_WEAPONS)))
                || (pc.getProficiencies().contains(type));
    }

    @Override
    public void equip(PlayerCharacter pc) {
        String base = name == null ? type : name;
        if (oneHanded != null) {
            String source = twoHanded == null ? base : base + " (1h)";
            String dice = oneHanded;
            addMelee(pc, source, dice, true);
            addMelee(pc, base + " (Off)", dice, false);
        }
        if (twoHanded != null) {
            String source = oneHanded == null ? base : base + " (2h)";
            String dice = twoHanded;
            addMelee(pc, source, dice, true);
        }
        if (range != null) {
            addRange(pc, base, range);
        }
    }

    private void addMelee(PlayerCharacter pc, String source, String dmgDice, boolean mainhand) {
        AttackAction attackRoller = new AttackAction();
        attackRoller.setPc(pc);
        attackRoller.setName(source);
        String attackDice = "";
        if (proficient(pc)) attackDice += "+PROF";
        String attribute = "+STR";
        if (finesse) {
            attribute = pc.getStrength() > pc.getDexterity() ? "+STR" : "+DEX";
        }
        attackDice += attribute;
        if (mainhand) dmgDice += attribute;
        attackRoller.setAttackDice(attackDice);
        DamageApplication dmg = new DamageApplication();
        dmg.setDamageType(damageType);
        dmg.setDamageDice(dmgDice);
        attackRoller.getDamage().add(dmg);
        pc.actions.put(source, attackRoller);
    }
    private void addRange(PlayerCharacter pc, String source, String dmgDice) {
        AttackAction attackRoller = new AttackAction();
        attackRoller.setPc(pc);
        attackRoller.setName(source);
        String attackDice = "";
        if (proficient(pc)) attackDice += "+PROF";
        attackDice +="+DEX";
        dmgDice += "+DEX";
        attackRoller.setAttackDice(attackDice);
        DamageApplication dmg = new DamageApplication();
        dmg.setName(type);
        dmg.setDamageType(damageType);
        dmg.setDamageDice(dmgDice);
        attackRoller.getDamage().add(dmg);
        pc.actions.put(source, attackRoller);
    }
}
