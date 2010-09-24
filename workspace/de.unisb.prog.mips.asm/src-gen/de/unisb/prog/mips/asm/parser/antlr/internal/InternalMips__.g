lexer grammar InternalMips;
@header {
package de.unisb.prog.mips.asm.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T12 : '.text' ;
T13 : '(' ;
T14 : ')' ;
T15 : 'li' ;
T16 : 'la' ;
T17 : '$' ;
T18 : '.data' ;
T19 : ':' ;
T20 : '.align' ;
T21 : '.space' ;
T22 : '.word' ;
T23 : '.half' ;
T24 : '.byte' ;
T25 : '.asciiz' ;
T26 : '.ascii' ;
T27 : ',' ;
T28 : '-' ;

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2152
RULE_COMMENT : ';' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2154
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2156
RULE_INT : ('0'..'9')+;

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2158
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2160
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2162
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2164
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g" 2166
RULE_ANY_OTHER : .;


