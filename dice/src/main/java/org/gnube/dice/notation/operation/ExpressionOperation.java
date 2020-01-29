package org.gnube.dice.notation.operation;

import org.gnube.dice.notation.DiceExpression;

public class ExpressionOperation implements DiceExpression {
    DiceExpression enclosed;

    public ExpressionOperation(DiceExpression expression) {
        this.enclosed = expression;
    }

    public DiceExpression getEnclosed() {
        return enclosed;
    }

    @Override
    public String getExpression() {
        return "(" + enclosed.getExpression() + ")";
    }
}
