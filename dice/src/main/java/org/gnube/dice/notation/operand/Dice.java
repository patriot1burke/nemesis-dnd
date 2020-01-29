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

package org.gnube.dice.notation.operand;

import org.gnube.dice.notation.DiceExpression;

/**
 * Default implementation of the dice operand.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class Dice implements DiceExpression {

    int quantity;
    int sides;

    public Dice(int quantity, int sides) {
        this.quantity = quantity;
        this.sides = sides;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSides() {
        return sides;
    }

    @Override
    public final String getExpression() {
        return String.format("%dd%d", quantity, sides);
    }

    @Override
    public final String toString() {
        return getExpression();
    }

}
