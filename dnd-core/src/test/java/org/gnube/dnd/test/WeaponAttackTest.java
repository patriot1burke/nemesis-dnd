package org.gnube.dnd.test;

import org.gnube.dice.visitor.DiceRoller;
import org.gnube.dnd.api.Attack;
import org.gnube.dnd.api.AttackAction;
import org.gnube.dnd.api.AttackDamage;
import org.gnube.dnd.api.PlayerCharacter;
import org.gnube.dnd.api.Weapon;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class WeaponAttackTest {

    static PlayerCharacter pc;
    static Weapon longsword;
    static Weapon longbow;

    @BeforeAll
    public static void initialize() {
        longsword = loadWeapon("longsword.yaml");
        longbow = loadWeapon("longbow.yaml");

        pc = new PlayerCharacter() {
            @Override
            protected boolean isCrit(int value) {
                return value > 10;
            }
        };
        pc.setStrength(18);
        pc.setDexterity(12);
        pc.setProficiency(2);
        pc.equip(longbow);
        pc.equip(longsword);
    }

    @Test
    public void testAttack() {
        AttackAction longsword1h = (AttackAction) pc.actions().get("Longsword (1h)");
        performAttack(longsword1h);
        AttackAction longsword2h = (AttackAction)pc.actions().get("Longsword (2h)");
        performAttack(longsword2h);
        AttackAction longswordOff = (AttackAction)pc.actions().get("Longsword (Off)");
        performAttack(longswordOff);
        AttackAction bow = (AttackAction) pc.actions().get("Longbow");
        performAttack(bow);
    }

    void performAttack(AttackAction action) {
        Attack attack = pc.attack(action);
        System.out.println(action.getName() + " attack[" + attack.getTotal().getTotal() + "]: " + attack.getDice() + "=" + attack.getTotal().getDescription());
        AttackDamage damage = pc.damage(attack);
        System.out.println("damage: " + (damage.isCrit() ? "*" :"") + damage.getTotal());
        for (AttackDamage.Damage dmg : damage.getDamage()) {
            System.out.println("\t" + (dmg.getName() == null ? "" : dmg.getName()) + " " + dmg.getType() + "[" + dmg.getTotal() + "]: " + dmg.getExpression() + " = " + dmg.getDescription());

        }
    }

    public static Weapon loadWeapon(String file) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        Yaml yaml = new Yaml(new Constructor(Weapon.class));
        return yaml.load(is);
    }

}
