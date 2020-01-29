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
 * Operand for an integer constant value.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
public final class Constant implements DiceExpression {

    /**
     * Operand value.
     */
    private final int value;

    /**
     * Constructs an operand with the specified value.
     * 
     * @param constant
     *            the operand value
     */
    public Constant(final int constant) {
        super();
        value = constant;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }


        final Constant other;

        other = (Constant) obj;

        return value == other.value;
    }

    @Override
    public final String getExpression() {
        return Integer.toString(value);
    }

    public final int getValue() {
        return value;
    }

    @Override
    public final int hashCode() {
        return value;
    }

    @Override
    public final String toString() {
        return Integer.toString(value);
    }

}
