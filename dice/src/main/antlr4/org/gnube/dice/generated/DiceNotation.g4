/**
 * Copyright 2014-2019 the original author or authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/** 
 * Dice notation grammar.
 * 
 * This is the notation which RPGs and other tabletop games use to represent operations with dice.
 */
grammar DiceNotation;

options { tokenVocab=DiceNotationLexer; }

/**
 * Rules.
 */

notation
:
    expression EOF
;

expression
    :
    left=expression  operator=(TIMES | DIV)  right=expression
    |  left=expression  operator=(PLUS | MINUS) right=expression
    |  LPAREN subExpression=expression RPAREN
    |  uni=(PLUS | MINUS)? value=atom
    | EOF
;

 
atom
:
   dice
   | number
   | variable
;

dice
:
   quantity=DIGIT DSEPARATOR sides=DIGIT
;

number
:
   DIGIT
;

variable
:
    VAR
;

