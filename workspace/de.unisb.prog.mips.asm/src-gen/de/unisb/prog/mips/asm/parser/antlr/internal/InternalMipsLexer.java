package de.unisb.prog.mips.asm.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMipsLexer extends Lexer {
    public static final int RULE_ID=4;
    public static final int RULE_ANY_OTHER=11;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T26=26;
    public static final int RULE_COMMENT=7;
    public static final int T25=25;
    public static final int Tokens=29;
    public static final int T24=24;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=9;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int RULE_ML_COMMENT=8;
    public static final int RULE_STRING=6;
    public static final int RULE_INT=5;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int RULE_WS=10;
    public static final int T15=15;
    public static final int T16=16;
    public static final int T17=17;
    public static final int T18=18;
    public static final int T19=19;
    public InternalMipsLexer() {;} 
    public InternalMipsLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g"; }

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:10:5: ( '.text' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:10:7: '.text'
            {
            match(".text"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:11:5: ( '(' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:11:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:12:5: ( ')' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:12:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:13:5: ( 'li' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:13:7: 'li'
            {
            match("li"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:14:5: ( 'la' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:14:7: 'la'
            {
            match("la"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:15:5: ( '$' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:15:7: '$'
            {
            match('$'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:16:5: ( '.data' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:16:7: '.data'
            {
            match(".data"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:17:5: ( ':' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:17:7: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:18:5: ( '.align' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:18:7: '.align'
            {
            match(".align"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:19:5: ( '.space' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:19:7: '.space'
            {
            match(".space"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:20:5: ( '.word' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:20:7: '.word'
            {
            match(".word"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:21:5: ( '.half' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:21:7: '.half'
            {
            match(".half"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:22:5: ( '.byte' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:22:7: '.byte'
            {
            match(".byte"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:23:5: ( '.asciiz' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:23:7: '.asciiz'
            {
            match(".asciiz"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:24:5: ( '.ascii' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:24:7: '.ascii'
            {
            match(".ascii"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:25:5: ( ',' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:25:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:26:5: ( '-' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:26:7: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start RULE_COMMENT
    public final void mRULE_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_COMMENT;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:14: ( ';' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:16: ';' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match(';'); 
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:20: (~ ( ( '\\n' | '\\r' ) ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\u0000' && LA1_0<='\t')||(LA1_0>='\u000B' && LA1_0<='\f')||(LA1_0>='\u000E' && LA1_0<='\uFFFE')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:20: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:36: ( ( '\\r' )? '\\n' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\n'||LA3_0=='\r') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:37: ( '\\r' )? '\\n'
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:37: ( '\\r' )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0=='\r') ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2152:37: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_COMMENT

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2154:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2154:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2154:11: ( '^' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='^') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2154:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2154:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2156:10: ( ( '0' .. '9' )+ )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2156:12: ( '0' .. '9' )+
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2156:12: ( '0' .. '9' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2156:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\"') ) {
                alt9=1;
            }
            else if ( (LA9_0=='\'') ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2158:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='!')||(LA7_0>='#' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFE')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:62: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:82: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:87: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop8:
                    do {
                        int alt8=3;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0=='\\') ) {
                            alt8=1;
                        }
                        else if ( ((LA8_0>='\u0000' && LA8_0<='&')||(LA8_0>='(' && LA8_0<='[')||(LA8_0>=']' && LA8_0<='\uFFFE')) ) {
                            alt8=2;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:88: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2158:129: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2160:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2160:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2160:24: ( options {greedy=false; } : . )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0=='*') ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1=='/') ) {
                        alt10=2;
                    }
                    else if ( ((LA10_1>='\u0000' && LA10_1<='.')||(LA10_1>='0' && LA10_1<='\uFFFE')) ) {
                        alt10=1;
                    }


                }
                else if ( ((LA10_0>='\u0000' && LA10_0<=')')||(LA10_0>='+' && LA10_0<='\uFFFE')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2160:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match("*/"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFE')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:40: ( ( '\\r' )? '\\n' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='\n'||LA13_0=='\r') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:41: ( '\\r' )? '\\n'
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:41: ( '\\r' )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='\r') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2162:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    // $ANTLR start RULE_WS
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2164:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2164:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2164:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\t' && LA14_0<='\n')||LA14_0=='\r'||LA14_0==' ') ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ANY_OTHER
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2166:16: ( . )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2166:18: .
            {
            matchAny(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ANY_OTHER

    public void mTokens() throws RecognitionException {
        // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:8: ( T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | RULE_COMMENT | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt15=25;
        int LA15_0 = input.LA(1);

        if ( (LA15_0=='.') ) {
            switch ( input.LA(2) ) {
            case 't':
                {
                alt15=1;
                }
                break;
            case 'd':
                {
                alt15=7;
                }
                break;
            case 'w':
                {
                alt15=11;
                }
                break;
            case 's':
                {
                alt15=10;
                }
                break;
            case 'a':
                {
                int LA15_22 = input.LA(3);

                if ( (LA15_22=='s') ) {
                    int LA15_40 = input.LA(4);

                    if ( (LA15_40=='c') ) {
                        int LA15_44 = input.LA(5);

                        if ( (LA15_44=='i') ) {
                            int LA15_45 = input.LA(6);

                            if ( (LA15_45=='i') ) {
                                int LA15_46 = input.LA(7);

                                if ( (LA15_46=='z') ) {
                                    alt15=14;
                                }
                                else {
                                    alt15=15;}
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("1:1: Tokens : ( T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | RULE_COMMENT | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 15, 45, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("1:1: Tokens : ( T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | RULE_COMMENT | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 15, 44, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("1:1: Tokens : ( T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | RULE_COMMENT | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 15, 40, input);

                        throw nvae;
                    }
                }
                else if ( (LA15_22=='l') ) {
                    alt15=9;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("1:1: Tokens : ( T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | RULE_COMMENT | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 15, 22, input);

                    throw nvae;
                }
                }
                break;
            case 'b':
                {
                alt15=13;
                }
                break;
            case 'h':
                {
                alt15=12;
                }
                break;
            default:
                alt15=25;}

        }
        else if ( (LA15_0=='(') ) {
            alt15=2;
        }
        else if ( (LA15_0==')') ) {
            alt15=3;
        }
        else if ( (LA15_0=='l') ) {
            switch ( input.LA(2) ) {
            case 'i':
                {
                int LA15_27 = input.LA(3);

                if ( ((LA15_27>='0' && LA15_27<='9')||(LA15_27>='A' && LA15_27<='Z')||LA15_27=='_'||(LA15_27>='a' && LA15_27<='z')) ) {
                    alt15=19;
                }
                else {
                    alt15=4;}
                }
                break;
            case 'a':
                {
                int LA15_28 = input.LA(3);

                if ( ((LA15_28>='0' && LA15_28<='9')||(LA15_28>='A' && LA15_28<='Z')||LA15_28=='_'||(LA15_28>='a' && LA15_28<='z')) ) {
                    alt15=19;
                }
                else {
                    alt15=5;}
                }
                break;
            default:
                alt15=19;}

        }
        else if ( (LA15_0=='$') ) {
            alt15=6;
        }
        else if ( (LA15_0==':') ) {
            alt15=8;
        }
        else if ( (LA15_0==',') ) {
            alt15=16;
        }
        else if ( (LA15_0=='-') ) {
            alt15=17;
        }
        else if ( (LA15_0==';') ) {
            alt15=18;
        }
        else if ( (LA15_0=='^') ) {
            int LA15_10 = input.LA(2);

            if ( ((LA15_10>='A' && LA15_10<='Z')||LA15_10=='_'||(LA15_10>='a' && LA15_10<='z')) ) {
                alt15=19;
            }
            else {
                alt15=25;}
        }
        else if ( ((LA15_0>='A' && LA15_0<='Z')||LA15_0=='_'||(LA15_0>='a' && LA15_0<='k')||(LA15_0>='m' && LA15_0<='z')) ) {
            alt15=19;
        }
        else if ( ((LA15_0>='0' && LA15_0<='9')) ) {
            alt15=20;
        }
        else if ( (LA15_0=='\"') ) {
            int LA15_13 = input.LA(2);

            if ( ((LA15_13>='\u0000' && LA15_13<='\uFFFE')) ) {
                alt15=21;
            }
            else {
                alt15=25;}
        }
        else if ( (LA15_0=='\'') ) {
            int LA15_14 = input.LA(2);

            if ( ((LA15_14>='\u0000' && LA15_14<='\uFFFE')) ) {
                alt15=21;
            }
            else {
                alt15=25;}
        }
        else if ( (LA15_0=='/') ) {
            switch ( input.LA(2) ) {
            case '*':
                {
                alt15=22;
                }
                break;
            case '/':
                {
                alt15=23;
                }
                break;
            default:
                alt15=25;}

        }
        else if ( ((LA15_0>='\t' && LA15_0<='\n')||LA15_0=='\r'||LA15_0==' ') ) {
            alt15=24;
        }
        else if ( ((LA15_0>='\u0000' && LA15_0<='\b')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\u001F')||LA15_0=='!'||LA15_0=='#'||(LA15_0>='%' && LA15_0<='&')||(LA15_0>='*' && LA15_0<='+')||(LA15_0>='<' && LA15_0<='@')||(LA15_0>='[' && LA15_0<=']')||LA15_0=='`'||(LA15_0>='{' && LA15_0<='\uFFFE')) ) {
            alt15=25;
        }
        else {
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | RULE_COMMENT | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 15, 0, input);

            throw nvae;
        }
        switch (alt15) {
            case 1 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:10: T12
                {
                mT12(); 

                }
                break;
            case 2 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:14: T13
                {
                mT13(); 

                }
                break;
            case 3 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:18: T14
                {
                mT14(); 

                }
                break;
            case 4 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:22: T15
                {
                mT15(); 

                }
                break;
            case 5 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:26: T16
                {
                mT16(); 

                }
                break;
            case 6 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:30: T17
                {
                mT17(); 

                }
                break;
            case 7 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:34: T18
                {
                mT18(); 

                }
                break;
            case 8 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:38: T19
                {
                mT19(); 

                }
                break;
            case 9 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:42: T20
                {
                mT20(); 

                }
                break;
            case 10 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:46: T21
                {
                mT21(); 

                }
                break;
            case 11 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:50: T22
                {
                mT22(); 

                }
                break;
            case 12 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:54: T23
                {
                mT23(); 

                }
                break;
            case 13 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:58: T24
                {
                mT24(); 

                }
                break;
            case 14 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:62: T25
                {
                mT25(); 

                }
                break;
            case 15 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:66: T26
                {
                mT26(); 

                }
                break;
            case 16 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:70: T27
                {
                mT27(); 

                }
                break;
            case 17 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:74: T28
                {
                mT28(); 

                }
                break;
            case 18 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:78: RULE_COMMENT
                {
                mRULE_COMMENT(); 

                }
                break;
            case 19 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:91: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 20 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:99: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 21 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:108: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 22 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:120: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 23 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:136: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 24 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:152: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 25 :
                // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1:160: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


 

}