package org.gnube.dnd.test;

import org.gnube.dnd.api.Attack;
import org.gnube.dnd.api.AttackDamage;
import org.gnube.dnd.api.Cast;
import org.gnube.dnd.api.CastAction;
import org.gnube.dnd.api.PlayerCharacter;
import org.gnube.dnd.api.Spell;
import org.gnube.dnd.api.SpellAttackAction;
import org.gnube.dnd.api.SpellDamageAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class SpellActionTest {

    static PlayerCharacter pc;
    static PlayerCharacter badAssPc;
    static Spell fireball;
    static Spell firebolt;
    static Spell chromatic_orb;
    static Spell sleep;

    @BeforeAll
    public static void initialize() {
        sleep = loadSpell("sleep.yaml");
        fireball = loadSpell("fireball.yaml");
        firebolt = loadSpell("firebolt.yaml");
        chromatic_orb = loadSpell("chromatic_orb.yaml");

        pc = new PlayerCharacter() {
            @Override
            public boolean isCrit(int value) {
                return value > 10;
            }
        };
        pc.setIntelligence(15);
        pc.setName("Minion");
        pc.setLevel(1);
        fireball.learn(pc);
        firebolt.learn(pc);
        sleep.learn(pc);
        chromatic_orb.learn(pc);

        badAssPc = new PlayerCharacter() {
            @Override
            public boolean isCrit(int value) {
                return value > 10;
            }
        };
        badAssPc.setName("Kickass");
        badAssPc.setIntelligence(18);
        badAssPc.setDexterity(12);
        badAssPc.setProficiency(2);
        badAssPc.setLevel(20);
        fireball.learn(badAssPc);
        firebolt.learn(badAssPc);
        sleep.learn(badAssPc);
        chromatic_orb.learn(badAssPc);
    }

    @Test
    public void test() {
        {
            SpellAttackAction orb = (SpellAttackAction) pc.getActions().get("Chromatic Orb");
            performAttack(orb, 1);
            performAttack(orb, 3);
            SpellAttackAction firebolt = (SpellAttackAction) pc.getActions().get("Firebolt");
            performAttack(firebolt);

            SpellDamageAction fireball = (SpellDamageAction) pc.getActions().get("Fireball");
            performSpellDamage(fireball, 3);
            performSpellDamage(fireball, 6);

            CastAction sleep = (CastAction) pc.getActions().get("Sleep");
            performCast(sleep, 3);
        }
        {
            SpellAttackAction firebolt = (SpellAttackAction) badAssPc.getActions().get("Firebolt");
            performAttack(firebolt);


        }


    }

    void performAttack(SpellAttackAction action) {
        performAttack(action, -1);
    }

    void performAttack(SpellAttackAction action, int slot) {
        Attack attack = action.attack();
        System.out.println(action.getPc().getName() + " " + action.getName() + " attack[" + attack.getTotal().getTotal() + "]: " + attack.getDice() + "=" + attack.getTotal().getDescription());
        AttackDamage damage = null;
        if (slot > -1) damage = action.spellDamage(attack.isCrit(), slot);
        else damage = action.spellDamage(attack.isCrit());
        System.out.println("damage: " + (damage.isCrit() ? "*" :"") + damage.getTotal());
        for (AttackDamage.Damage dmg : damage.getDamage()) {
            System.out.println("\t" + (dmg.getName() == null ? "" : dmg.getName()) + " " + dmg.getType() + "[" + dmg.getTotal() + "]: " + dmg.getExpression() + " = " + dmg.getDescription());

        }
    }

    void performSpellDamage(SpellDamageAction action, int slot) {
        AttackDamage damage = action.spellDamage(slot);
        System.out.println(action.getPc().getName() + " " + action.getName() + " damage: " + damage.getTotal());
        for (AttackDamage.Damage dmg : damage.getDamage()) {
            System.out.println("\t" + (dmg.getName() == null ? "" : dmg.getName()) + " " + dmg.getType() + "[" + dmg.getTotal() + "]: " + dmg.getExpression() + " = " + dmg.getDescription());

        }
    }

    void performCast(CastAction action, int slot) {
        Cast cast = action.cast(slot);
        System.out.println(action.getPc().getName() + " " + action.getName() + " cast[" + cast.getTotal() + "]: " + cast.getExpression() + " = " + cast.getDescription());
    }

    public static Spell loadSpell(String file) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        Yaml yaml = new Yaml(new Constructor(Spell.class));
        return yaml.load(is);
    }

}
