package org.gnube.dice.test;

import org.gnube.dice.notation.DiceExpression;
import org.gnube.dice.parser.NotationTransformer;
import org.gnube.dice.visitor.DiceRoller;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class NotationTextTest {

    @Test
    public void testMixed() {
        test("1d20-5+2d6");
        test("1d6+2d12");
        test("1d6+1");
        test("-1d6+1");
        test("-1+1d6");
        test("1+1d6");
        test("1+-2");
        test("1+2");
        test("1++2", "1+2");
        test("1d20+2d6-3d12");
        test("2-8/2");
        test("2-8*2");
        test("1d20-5+2d6");
        test("1+2-3");
        test("1D6", "1d6");
        test("30d100");
        test("1d6/2d12");
        test("1d6/1");
        test("1/1d6");
        test("4/2");
        test("1d6*2d12");
        test("1d6*1");
        test("1*1d6");
        test("1*2");
        test("-1d6");
        test("-1");
        test("1d6+2d12");
        test("0d6");
        test("12");
        test("001200", "1200");
        test("(1+2)*3");
        test("+1d6", "1d6");
        test("1");
        test("1d6");
        test("1d6-2d12");
        test("1d6-1");
        test("-1d6-1");
        test("-1-1d6");
        test("1-1d6");
        test("1--2");
        test("1-2");
        test("2d10+level+proficiency+1");
        test("DEXTERITY");
        test("1d20- 5  +  2d6", "1d20-5+2d6");
        test("   1d20-5+2d6 ", "1d20-5+2d6");
    }

    private void test(String expression) {
        DiceExpression dice = NotationTransformer.transform(expression);
        String expected = dice.getExpression();
        Assert.assertEquals(expression, expected);
    }

    private void test(String expression, String expected) {
        DiceExpression dice = NotationTransformer.transform(expression);
        String diceExpression = dice.getExpression();
        Assert.assertEquals(expected, diceExpression);
    }
}
