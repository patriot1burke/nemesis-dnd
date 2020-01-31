package org.gnube.dice.visitor;

import org.gnube.dice.notation.DiceExpression;
import org.gnube.dice.notation.operand.Constant;
import org.gnube.dice.notation.operand.Dice;
import org.gnube.dice.notation.operand.Variable;
import org.gnube.dice.notation.operation.Addition;
import org.gnube.dice.notation.operation.BinaryOperation;
import org.gnube.dice.notation.operation.Division;
import org.gnube.dice.notation.operation.ExpressionOperation;
import org.gnube.dice.notation.operation.Multiplication;
import org.gnube.dice.notation.operation.Negate;
import org.gnube.dice.notation.operation.Subtraction;
import org.gnube.dice.parser.NotationTransformer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DiceRoller {
    public static Random random = new Random();
    List<DiceRoll> diceRoles = new LinkedList<>();
    Stack<Result> results = new Stack<>();

    public static class Total {
        String expression;
        int total;
        String description;
        List<DiceRoll> roles;

        public Total(String expression, int total, String description, List<DiceRoll> roles) {
            this.total = total;
            this.description = description;
            this.roles = roles;
            this.expression = expression;
        }

        public int getTotal() {
            return total;
        }

        public String getDescription() {
            return description;
        }

        public String getExpression() {
            return expression;
        }

        public List<DiceRoll> getDiceRolls() {
            return roles;
        }
    }

    public class DiceRoll {
        Dice dice;
        List<Integer> rolls = new LinkedList<>();
        int total;
        String description;

        public DiceRoll(Dice dice) {
            this.dice = dice;
            this.description = "(";
            for (int i = 0; i < dice.getQuantity(); i++) {
                int roll = rollDie(dice.getSides());
                rolls.add(roll);
                total += roll;
                if (i > 0) {
                    this.description += "+";
                }
                this.description += Integer.toString(roll);
            }
            this.description += ")";
        }

        public Dice getDice() {
            return dice;
        }

        public List<Integer> getRolls() {
            return rolls;
        }

        public int getTotal() {
            return total;
        }

        public String getDescription() {
            return description;
        }

        public DiceRoll newRoll() {
            return new DiceRoll(dice);
        }
    }

    public Total roll(String expression) {
        DiceExpression dice = NotationTransformer.transform(expression);
        DiceInterpreter.depth(dice, new Roller());
        Result top = results.pop();
        return new Total(expression, top.total, top.description, diceRoles);
    }

   protected int rollDie(int sides) {
        return random.nextInt(sides) + 1;
    }

    protected class Result {
        int total;
        String description;

        public Result(int total, String description) {
            this.total = total;
            this.description = description;
        }
    }

    protected int getVariableValue(String variable) {
        return 0;
    }

    protected class Roller implements NotationVisitor {
       @Override
        public void expression(ExpressionOperation exp) {
            Result result = results.pop();
            result.description = "(" + result.description + ")";
            results.push(result);
        }

        @Override
        public void operation(BinaryOperation exp) {
            Result right = results.pop();
            Result left = results.pop();
            if (exp instanceof Addition) {
                String result = left.description + "+" + right.description;
                results.push(new Result(left.total + right.total, result));
            } else if (exp instanceof Subtraction) {
                String result = left.description + "-" + right.description;
                results.push(new Result(left.total - right.total, result));

            } else if (exp instanceof Multiplication) {
                String result = left.description + "*" + right.description;
                results.push(new Result(left.total * right.total, result));

            } else if (exp instanceof Division) {
                String result = left.description + "/" + right.description;
                results.push(new Result(left.total / right.total, result));
            }
        }

        @Override
        public void negate(Negate exp) {
            Result prior = results.pop();
            results.push(new Result(-1 * prior.total, "-" + prior.description));

        }

        @Override
        public void constant(Constant exp) {
            results.push(new Result(exp.getValue(), Integer.toString(exp.getValue())));
        }

        @Override
        public void dice(Dice exp) {
            DiceRoll roll = new DiceRoll(exp);
            diceRoles.add(roll);
            results.push(new Result(roll.total, roll.getDescription()));
        }

        @Override
        public void variable(Variable exp) {
            int value = getVariableValue(exp.getName());
            results.push(new Result(value, Integer.toString(value)));
        }
    }

}
