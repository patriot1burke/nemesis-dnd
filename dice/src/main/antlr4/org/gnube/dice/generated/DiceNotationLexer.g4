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
 * Dice notation lexical rules.
 */
lexer grammar DiceNotationLexer;

/**
 * Tokens.
 */

// Dice markers

DSEPARATOR
:
   ('d' | 'D')
;

KSEPARATOR
:
   ('k' | 'K')
;

DIGIT
:
   ('0'..'9')+
;

VAR
:
   ('a'..'z' | 'A'..'Z')+
;

// Operation tokens

PLUS
   : '+'
   ;


MINUS
   : '-'
   ;


TIMES
   : '*'
   ;


DIV
   : '/'
   ;

LPAREN
:
   '('
;

RPAREN
:
   ')'
;

LBRACKET
:
   '['
;

RBRACKET
:
   ']'
;

// Skippable tokens

WS:
   [\t\r\n]+ -> skip
;
