/**
 * Copyright 2014-2019 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gnube.dice.notation.operation;

import org.gnube.dice.notation.DiceExpression;

import java.util.function.BiFunction;

public abstract class AbstractBinaryOperation implements BinaryOperation {

    private final DiceExpression left;
    private final BiFunction<Integer, Integer, Integer> operation;
    private final DiceExpression right;

    public AbstractBinaryOperation(DiceExpression left,
                                   DiceExpression right,
                                   BiFunction<Integer, Integer, Integer> operation) {
        this.left = left;
        this.operation = operation;
        this.right = right;
    }

    @Override
    public final DiceExpression getLeft() {
        return left;
    }

    @Override
    public final BiFunction<Integer, Integer, Integer> getOperation() {
        return operation;
    }

    @Override
    public final DiceExpression getRight() {
        return right;
    }

}
