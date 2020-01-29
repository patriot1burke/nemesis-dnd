package org.gnube.dice.visitor;

import org.gnube.dice.notation.DiceExpression;
import org.gnube.dice.notation.operand.Constant;
import org.gnube.dice.notation.operand.Dice;
import org.gnube.dice.notation.operand.Variable;
import org.gnube.dice.notation.operation.BinaryOperation;
import org.gnube.dice.notation.operation.ExpressionOperation;
import org.gnube.dice.notation.operation.Negate;

public class DiceInterpreter {
    boolean depthFirst;

    private DiceInterpreter(boolean depthFirst) {
        this.depthFirst = depthFirst;
    }

    public DiceInterpreter() {
    }

    public static void depth(DiceExpression expression, NotationVisitor visitor) {
        new DiceInterpreter(true).search(expression, visitor);
    }

    public static void bredth(DiceExpression expression, NotationVisitor visitor) {
        new DiceInterpreter(false).search(expression, visitor);
    }

    public static DiceInterpreter manual() {
        return new DiceInterpreter();
    }

    public void visit(DiceExpression expression, NotationVisitor visitor) {
        if (expression instanceof ExpressionOperation) {
            ExpressionOperation op = (ExpressionOperation)expression;
            visitor.expression(op);
        } else if (expression instanceof BinaryOperation) {
            BinaryOperation op = (BinaryOperation)expression;
            visitor.operation(op);
        } else if (expression instanceof Negate) {
            Negate op = (Negate)expression;
            visitor.negate(op);
        } else if (expression instanceof Variable) {
            Variable op = (Variable)expression;
            visitor.variable(op);
        } else if (expression instanceof Constant) {
            Constant op = (Constant)expression;
            visitor.constant(op);
        } else if (expression instanceof Dice) {
            Dice op = (Dice)expression;
            visitor.dice(op);
        }
    }

    private void search(DiceExpression expression, NotationVisitor visitor) {
        if (expression instanceof ExpressionOperation) {
            ExpressionOperation op = (ExpressionOperation)expression;
            if (depthFirst) {
                search(op.getEnclosed(), visitor);
                visitor.expression(op);
            }
            else {
                visitor.expression(op);
                search(op.getEnclosed(), visitor);
            }
        } else if (expression instanceof BinaryOperation) {
            BinaryOperation op = (BinaryOperation)expression;
            if (depthFirst) {
                search(op.getLeft(), visitor);
                search(op.getRight(), visitor);
                visitor.operation(op);
            }
            else {
                visitor.operation(op);
                search(op.getLeft(), visitor);
                search(op.getRight(), visitor);
            }
        } else if (expression instanceof Negate) {
            Negate op = (Negate)expression;
            if (depthFirst) {
                search(op.getValue(), visitor);
                visitor.negate(op);
            }
            else {
                visitor.negate(op);
                search(op.getValue(), visitor);
            }
        } else if (expression instanceof Variable) {
            Variable op = (Variable)expression;
            visitor.variable(op);
        } else if (expression instanceof Constant) {
            Constant op = (Constant)expression;
            visitor.constant(op);
        } else if (expression instanceof Dice) {
            Dice op = (Dice)expression;
            visitor.dice(op);
        }
    }
}
