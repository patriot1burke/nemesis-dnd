package org.gnube.dice.test;

import org.gnube.dice.visitor.DiceRoller;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DiceRollerTest {

    class MaxRoller extends DiceRoller {
        @Override
        protected int rollDie(int sides) {
            return sides;
        }

        @Override
        protected int getVariableValue(String variable) {
            if ("dex".equalsIgnoreCase(variable)) {
                return 4;
            } else if ("pro".equalsIgnoreCase(variable)) {
                return 2;
            } else if ("level".equalsIgnoreCase(variable)) {
                return 1;
            }
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void testOne() {
        DiceRoller.Total total = new DiceRoller().roll("1d20-5+2d6");
        System.out.println("total: " + total.getTotal());
        System.out.println("exp: " + total.getDescription());

    }

    @Test
    public void testMixed() {
        test("1d20-5+2d6", 27, "(20)-5+(6+6)");
        test("1d6+2d12", 30, "(6)+(12+12)");
        test("1d6+1", 7, "(6)+1");
        test("-1d6+1", -5, "-(6)+1");
        test("-1+1d6", 5, "-1+(6)");
        test("1+1d6", 7, "1+(6)");
        test("1+-2", -1, "1+-2");
        test("1+2", 3, "1+2");
        test("1d20+2d6-3d12", 20 + 12 - 36, "(20)+(6+6)-(12+12+12)");
        test("2-8/2", 2-(8/2), "2-8/2");
        test("2-8*2", 2-(8*2), "2-8*2");
        test("1+2-3", 1+2-3, "1+2-3");
        test("1D6", 6, "(6)");
        test("20d100", 20*100, "(100+100+100+100+100+100+100+100+100+100+100+100+100+100+100+100+100+100+100+100)");
        test("1d6/2d12", 6/24, "(6)/(12+12)");
        test("1d6/1", 6, "(6)/1");
        test("1/1d6", 1/6, "1/(6)");
        test("4/2", 4/2, "4/2");
        test("1d6*2d12", 6*24, "(6)*(12+12)");
        test("1d6*1", (6)*1, "(6)*1");
        test("1*1d6", 1*(6), "1*(6)");
        test("1*2", 2, "1*2");
        test("-1d6", -6, "-(6)");
        test("-1", -1, "-1");
        test("1d6+2d12", 6 + 24, "(6)+(12+12)");
        test("12", 12, "12");
        test("001200", 1200, "1200");
        test("(1+2)*3", 9, "(1+2)*3");
        test("1", 1, "1");
        test("1d6", 6, "(6)");
        test("1d6-2d12", 6-(12+12), "(6)-(12+12)");
        test("1d6-1", 5, "(6)-1");
        test("-1d6-1", -6-1, "-(6)-1");
        test("-1-1d6", -1-6, "-1-(6)");
        test("1-1d6", 1-6, "1-(6)");
        test("1--2", 3, "1--2");
        test("1-2", -1, "1-2");
        test("2d10+level+pro+1" , 20+1+2+1, "(10+10)+1+2+1");
        test("dex", 4, "4");
        test("1d20- 5  +  2d6", 20-5+12,"(20)-5+(6+6)");
        test("   1d20-5+2d6 ", 20-5+12,"(20)-5+(6+6)");
    }

    private void test(String expression, int total, String description) {
        DiceRoller.Total diceTotal = new MaxRoller().roll(expression);
        //System.out.println(expression + " = " + diceTotal.getTotal() + " = " + diceTotal.getDescription());
        Assert.assertEquals(total, diceTotal.getTotal());
        Assert.assertEquals(description, diceTotal.getDescription());
    }
}
