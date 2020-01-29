package org.gnube.dice.notation.operation;

import org.gnube.dice.notation.DiceExpression;

public class Negate implements DiceExpression {
    DiceExpression value;

    public Negate(DiceExpression expression) {
        this.value = expression;
    }

    public DiceExpression getValue() {
        return value;
    }

    @Override
    public String getExpression() {
        return "-" + value.getExpression();
    }
}
