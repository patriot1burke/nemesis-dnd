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

package org.gnube.dice.parser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.gnube.dice.generated.DiceNotationLexer;
import org.gnube.dice.generated.DiceNotationParser;
import org.gnube.dice.notation.DiceExpression;

public class DiceParser {

    /**
     * Error listener for the parser and lexer.
     */
    private final ANTLRErrorListener    errorListener = new ErrorListener();
    private NotationTransformer listener;

    public final DiceExpression parse(final String expression) {
        final DiceNotationParser parser;   // ANTLR parser
        final DiceExpression root; // Root expression
        // Creates the ANTLR parser
        parser = buildDiceNotationParser(expression);

        // Parses the root rule
        parser.notation();

        root = listener.getExpressionRoot();

        // Returns the tree root node
        return root;
    }

   private final DiceNotationParser
            buildDiceNotationParser(final String expression) {
        final CharStream stream;
        final DiceNotationLexer lexer;
        final TokenStream tokens;
        final DiceNotationParser parser;

        stream = CharStreams.fromString(expression);

        lexer = new DiceNotationLexer(stream);
        lexer.addErrorListener(errorListener);

        tokens = new CommonTokenStream(lexer);

        parser = new DiceNotationParser(tokens);
        parser.addErrorListener(errorListener);
        listener = new NotationTransformer();
        parser.addParseListener(listener);

        return parser;
    }

}
