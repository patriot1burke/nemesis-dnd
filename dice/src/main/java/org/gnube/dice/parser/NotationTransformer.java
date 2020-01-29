package org.gnube.dice.parser;

import org.gnube.dice.generated.DiceNotationBaseListener;
import org.gnube.dice.generated.DiceNotationParser;
import org.gnube.dice.notation.DiceExpression;
import org.gnube.dice.notation.operand.Constant;
import org.gnube.dice.notation.operand.Dice;
import org.gnube.dice.notation.operand.Variable;
import org.gnube.dice.notation.operation.Addition;
import org.gnube.dice.notation.operation.Division;
import org.gnube.dice.notation.operation.ExpressionOperation;
import org.gnube.dice.notation.operation.Multiplication;
import org.gnube.dice.notation.operation.Negate;
import org.gnube.dice.notation.operation.Subtraction;

import java.util.Stack;

public class NotationTransformer extends DiceNotationBaseListener {

    public static DiceExpression transform(String expression) {
        expression = expression.replaceAll("\\s","");
        return new DiceParser().parse(expression);
    }

    private final Stack<DiceExpression> nodes = new Stack<>();
    private DiceExpression root;

    public DiceExpression getExpressionRoot() {
        return root;
    }
    @Override
    public void exitNotation(DiceNotationParser.NotationContext ctx) {
        root = nodes.pop();
    }


    @Override
    public void exitExpression(DiceNotationParser.ExpressionContext ctx) {
        if (ctx.left != null) {
            String operator = ctx.operator.getText();
            DiceExpression right = nodes.pop();
            DiceExpression left = nodes.pop();
            if ("*".equals(operator)) {
                nodes.push(new Multiplication(left, right));
            } else if ("/".equals(operator)) {
                nodes.push(new Division(left, right));
            } else if ("-".equals(operator)) {
                nodes.push(new Subtraction(left, right));
            } else if ("+".equals(operator)) {
                nodes.push(new Addition(left, right));
            }
        } else if (ctx.subExpression != null) {
            nodes.push(new ExpressionOperation(nodes.pop()));
        } else if (ctx.value != null) {
            if (ctx.uni != null) {
                String operator = ctx.uni.getText();
                DiceExpression operand = nodes.pop();
                if ("-".equals(operator)) {
                    nodes.push(new Negate(operand));
                } else {
                    nodes.push(operand);
                }
            }
        }
    }

    @Override
    public void exitDice(DiceNotationParser.DiceContext ctx) {
        int quantity = Integer.parseInt(ctx.quantity.getText());
        int sides = Integer.parseInt(ctx.sides.getText());
        nodes.push(new Dice(quantity, sides));
    }

    @Override
    public void exitNumber(DiceNotationParser.NumberContext ctx) {
        nodes.push(new Constant(Integer.parseInt(ctx.DIGIT().getText())));
    }

    @Override
    public void exitVariable(DiceNotationParser.VariableContext ctx) {
        nodes.push(new Variable(ctx.VAR().getText()));
    }
}