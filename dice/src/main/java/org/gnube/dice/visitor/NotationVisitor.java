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

package org.gnube.dice.visitor;

import org.gnube.dice.notation.operand.Constant;
import org.gnube.dice.notation.operand.Dice;
import org.gnube.dice.notation.operand.Variable;
import org.gnube.dice.notation.operation.BinaryOperation;
import org.gnube.dice.notation.operation.ExpressionOperation;
import org.gnube.dice.notation.operation.Negate;

public interface NotationVisitor {

    void expression(ExpressionOperation exp);
    void operation(BinaryOperation exp);
    void negate(Negate exp);
    void constant(Constant exp);
    void dice(Dice exp);
    void variable(Variable exp);

}
