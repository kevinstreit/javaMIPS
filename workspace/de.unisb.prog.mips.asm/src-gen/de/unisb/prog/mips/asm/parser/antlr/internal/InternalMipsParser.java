package de.unisb.prog.mips.asm.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import de.unisb.prog.mips.asm.services.MipsGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMipsParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_COMMENT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'.text'", "'('", "')'", "'li'", "'la'", "'$'", "'.data'", "':'", "'.align'", "'.space'", "'.word'", "'.half'", "'.byte'", "'.asciiz'", "'.ascii'", "','", "'-'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_INT=5;
    public static final int RULE_COMMENT=7;
    public static final int RULE_WS=10;
    public static final int RULE_SL_COMMENT=9;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=8;

        public InternalMipsParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g"; }



     	private MipsGrammarAccess grammarAccess;
     	
        public InternalMipsParser(TokenStream input, IAstFactory factory, MipsGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Asm";	
       	}
       	
       	@Override
       	protected MipsGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleAsm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:77:1: entryRuleAsm returns [EObject current=null] : iv_ruleAsm= ruleAsm EOF ;
    public final EObject entryRuleAsm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAsm = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS", "RULE_COMMENT");
        	
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:81:2: (iv_ruleAsm= ruleAsm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:82:2: iv_ruleAsm= ruleAsm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAsmRule(), currentNode); 
            pushFollow(FOLLOW_ruleAsm_in_entryRuleAsm81);
            iv_ruleAsm=ruleAsm();
            _fsp--;

             current =iv_ruleAsm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAsm91); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end entryRuleAsm


    // $ANTLR start ruleAsm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:92:1: ruleAsm returns [EObject current=null] : ( ( (lv_elem_0_0= ruleDataSegment ) ) | ( (lv_elem_1_0= ruleTextSegment ) ) ) ;
    public final EObject ruleAsm() throws RecognitionException {
        EObject current = null;

        EObject lv_elem_0_0 = null;

        EObject lv_elem_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS", "RULE_COMMENT");
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:98:6: ( ( ( (lv_elem_0_0= ruleDataSegment ) ) | ( (lv_elem_1_0= ruleTextSegment ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:99:1: ( ( (lv_elem_0_0= ruleDataSegment ) ) | ( (lv_elem_1_0= ruleTextSegment ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:99:1: ( ( (lv_elem_0_0= ruleDataSegment ) ) | ( (lv_elem_1_0= ruleTextSegment ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==18) ) {
                alt1=1;
            }
            else if ( (LA1_0==12) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("99:1: ( ( (lv_elem_0_0= ruleDataSegment ) ) | ( (lv_elem_1_0= ruleTextSegment ) ) )", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:99:2: ( (lv_elem_0_0= ruleDataSegment ) )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:99:2: ( (lv_elem_0_0= ruleDataSegment ) )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:100:1: (lv_elem_0_0= ruleDataSegment )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:100:1: (lv_elem_0_0= ruleDataSegment )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:101:3: lv_elem_0_0= ruleDataSegment
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAsmAccess().getElemDataSegmentParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleDataSegment_in_ruleAsm141);
                    lv_elem_0_0=ruleDataSegment();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAsmRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"elem",
                    	        		lv_elem_0_0, 
                    	        		"DataSegment", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:124:6: ( (lv_elem_1_0= ruleTextSegment ) )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:124:6: ( (lv_elem_1_0= ruleTextSegment ) )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:125:1: (lv_elem_1_0= ruleTextSegment )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:125:1: (lv_elem_1_0= ruleTextSegment )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:126:3: lv_elem_1_0= ruleTextSegment
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getAsmAccess().getElemTextSegmentParserRuleCall_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleTextSegment_in_ruleAsm168);
                    lv_elem_1_0=ruleTextSegment();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getAsmRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"elem",
                    	        		lv_elem_1_0, 
                    	        		"TextSegment", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end ruleAsm


    // $ANTLR start entryRuleTextSegment
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:159:1: entryRuleTextSegment returns [EObject current=null] : iv_ruleTextSegment= ruleTextSegment EOF ;
    public final EObject entryRuleTextSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTextSegment = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:160:2: (iv_ruleTextSegment= ruleTextSegment EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:161:2: iv_ruleTextSegment= ruleTextSegment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTextSegmentRule(), currentNode); 
            pushFollow(FOLLOW_ruleTextSegment_in_entryRuleTextSegment208);
            iv_ruleTextSegment=ruleTextSegment();
            _fsp--;

             current =iv_ruleTextSegment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTextSegment218); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleTextSegment


    // $ANTLR start ruleTextSegment
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:168:1: ruleTextSegment returns [EObject current=null] : ( '.text' () ( (lv_items_2_0= ruleTextItem ) )* ) ;
    public final EObject ruleTextSegment() throws RecognitionException {
        EObject current = null;

        EObject lv_items_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:173:6: ( ( '.text' () ( (lv_items_2_0= ruleTextItem ) )* ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:174:1: ( '.text' () ( (lv_items_2_0= ruleTextItem ) )* )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:174:1: ( '.text' () ( (lv_items_2_0= ruleTextItem ) )* )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:174:3: '.text' () ( (lv_items_2_0= ruleTextItem ) )*
            {
            match(input,12,FOLLOW_12_in_ruleTextSegment253); 

                    createLeafNode(grammarAccess.getTextSegmentAccess().getTextKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:178:1: ()
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:179:5: 
            {
             
                    temp=factory.create(grammarAccess.getTextSegmentAccess().getTextSegmentAction_1().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getTextSegmentAccess().getTextSegmentAction_1(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:189:2: ( (lv_items_2_0= ruleTextItem ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID||(LA2_0>=15 && LA2_0<=16)||LA2_0==20||LA2_0==22) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:190:1: (lv_items_2_0= ruleTextItem )
            	    {
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:190:1: (lv_items_2_0= ruleTextItem )
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:191:3: lv_items_2_0= ruleTextItem
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getTextSegmentAccess().getItemsTextItemParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleTextItem_in_ruleTextSegment283);
            	    lv_items_2_0=ruleTextItem();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getTextSegmentRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"items",
            	    	        		lv_items_2_0, 
            	    	        		"TextItem", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleTextSegment


    // $ANTLR start entryRuleTextItem
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:221:1: entryRuleTextItem returns [EObject current=null] : iv_ruleTextItem= ruleTextItem EOF ;
    public final EObject entryRuleTextItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTextItem = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:222:2: (iv_ruleTextItem= ruleTextItem EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:223:2: iv_ruleTextItem= ruleTextItem EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTextItemRule(), currentNode); 
            pushFollow(FOLLOW_ruleTextItem_in_entryRuleTextItem320);
            iv_ruleTextItem=ruleTextItem();
            _fsp--;

             current =iv_ruleTextItem; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTextItem330); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleTextItem


    // $ANTLR start ruleTextItem
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:230:1: ruleTextItem returns [EObject current=null] : ( ( (lv_Label_0_0= ruleLabel ) )? ( ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) ) ) ) ;
    public final EObject ruleTextItem() throws RecognitionException {
        EObject current = null;

        EObject lv_Label_0_0 = null;

        EObject lv_item_1_1 = null;

        EObject lv_item_1_2 = null;

        EObject lv_item_1_3 = null;

        EObject lv_item_1_4 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:235:6: ( ( ( (lv_Label_0_0= ruleLabel ) )? ( ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:236:1: ( ( (lv_Label_0_0= ruleLabel ) )? ( ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:236:1: ( ( (lv_Label_0_0= ruleLabel ) )? ( ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:236:2: ( (lv_Label_0_0= ruleLabel ) )? ( ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:236:2: ( (lv_Label_0_0= ruleLabel ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==19) ) {
                    alt3=1;
                }
            }
            switch (alt3) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:237:1: (lv_Label_0_0= ruleLabel )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:237:1: (lv_Label_0_0= ruleLabel )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:238:3: lv_Label_0_0= ruleLabel
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getTextItemAccess().getLabelLabelParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleLabel_in_ruleTextItem376);
                    lv_Label_0_0=ruleLabel();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTextItemRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"Label",
                    	        		lv_Label_0_0, 
                    	        		"Label", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:260:3: ( ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:261:1: ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:261:1: ( (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:262:1: (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:262:1: (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord )
            int alt4=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt4=1;
                }
                break;
            case 15:
            case 16:
                {
                alt4=2;
                }
                break;
            case 20:
                {
                alt4=3;
                }
                break;
            case 22:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("262:1: (lv_item_1_1= ruleInstruction | lv_item_1_2= ruleSpecialInsn | lv_item_1_3= ruleAlign | lv_item_1_4= ruleWord )", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:263:3: lv_item_1_1= ruleInstruction
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getTextItemAccess().getItemInstructionParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleInstruction_in_ruleTextItem400);
                    lv_item_1_1=ruleInstruction();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTextItemRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_1_1, 
                    	        		"Instruction", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:284:8: lv_item_1_2= ruleSpecialInsn
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getTextItemAccess().getItemSpecialInsnParserRuleCall_1_0_1(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleSpecialInsn_in_ruleTextItem419);
                    lv_item_1_2=ruleSpecialInsn();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTextItemRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_1_2, 
                    	        		"SpecialInsn", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 3 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:305:8: lv_item_1_3= ruleAlign
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getTextItemAccess().getItemAlignParserRuleCall_1_0_2(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAlign_in_ruleTextItem438);
                    lv_item_1_3=ruleAlign();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTextItemRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_1_3, 
                    	        		"Align", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 4 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:326:8: lv_item_1_4= ruleWord
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getTextItemAccess().getItemWordParserRuleCall_1_0_3(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleWord_in_ruleTextItem457);
                    lv_item_1_4=ruleWord();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getTextItemRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_1_4, 
                    	        		"Word", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleTextItem


    // $ANTLR start entryRuleInstruction
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:358:1: entryRuleInstruction returns [EObject current=null] : iv_ruleInstruction= ruleInstruction EOF ;
    public final EObject entryRuleInstruction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstruction = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:359:2: (iv_ruleInstruction= ruleInstruction EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:360:2: iv_ruleInstruction= ruleInstruction EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInstructionRule(), currentNode); 
            pushFollow(FOLLOW_ruleInstruction_in_entryRuleInstruction496);
            iv_ruleInstruction=ruleInstruction();
            _fsp--;

             current =iv_ruleInstruction; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInstruction506); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleInstruction


    // $ANTLR start ruleInstruction
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:367:1: ruleInstruction returns [EObject current=null] : ( ( (lv_opcode_0_0= RULE_ID ) ) ( ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) ) ) ) ;
    public final EObject ruleInstruction() throws RecognitionException {
        EObject current = null;

        Token lv_opcode_0_0=null;
        EObject lv_form_1_1 = null;

        EObject lv_form_1_2 = null;

        EObject lv_form_1_3 = null;

        EObject lv_form_1_4 = null;

        EObject lv_form_1_5 = null;

        EObject lv_form_1_6 = null;

        EObject lv_form_1_7 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:372:6: ( ( ( (lv_opcode_0_0= RULE_ID ) ) ( ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:373:1: ( ( (lv_opcode_0_0= RULE_ID ) ) ( ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:373:1: ( ( (lv_opcode_0_0= RULE_ID ) ) ( ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:373:2: ( (lv_opcode_0_0= RULE_ID ) ) ( ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:373:2: ( (lv_opcode_0_0= RULE_ID ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:374:1: (lv_opcode_0_0= RULE_ID )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:374:1: (lv_opcode_0_0= RULE_ID )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:375:3: lv_opcode_0_0= RULE_ID
            {
            lv_opcode_0_0=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInstruction548); 

            			createLeafNode(grammarAccess.getInstructionAccess().getOpcodeIDTerminalRuleCall_0_0(), "opcode"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"opcode",
            	        		lv_opcode_0_0, 
            	        		"ID", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:397:2: ( ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:398:1: ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:398:1: ( (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )
            int alt5=7;
            switch ( input.LA(1) ) {
            case 17:
                {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==RULE_INT) ) {
                    switch ( input.LA(3) ) {
                    case 17:
                        {
                        int LA5_6 = input.LA(4);

                        if ( (LA5_6==RULE_INT) ) {
                            switch ( input.LA(5) ) {
                            case RULE_ID:
                                {
                                alt5=5;
                                }
                                break;
                            case 17:
                                {
                                alt5=1;
                                }
                                break;
                            case RULE_INT:
                            case 28:
                                {
                                alt5=2;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 9, input);

                                throw nvae;
                            }

                        }
                        else if ( (LA5_6==RULE_ID) ) {
                            switch ( input.LA(5) ) {
                            case RULE_INT:
                            case 28:
                                {
                                alt5=2;
                                }
                                break;
                            case 17:
                                {
                                alt5=1;
                                }
                                break;
                            case RULE_ID:
                                {
                                alt5=5;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 10, input);

                                throw nvae;
                            }

                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_ID:
                        {
                        alt5=4;
                        }
                        break;
                    case RULE_INT:
                    case 28:
                        {
                        alt5=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 4, input);

                        throw nvae;
                    }

                }
                else if ( (LA5_1==RULE_ID) ) {
                    switch ( input.LA(3) ) {
                    case RULE_ID:
                        {
                        alt5=4;
                        }
                        break;
                    case 17:
                        {
                        int LA5_6 = input.LA(4);

                        if ( (LA5_6==RULE_INT) ) {
                            switch ( input.LA(5) ) {
                            case RULE_ID:
                                {
                                alt5=5;
                                }
                                break;
                            case 17:
                                {
                                alt5=1;
                                }
                                break;
                            case RULE_INT:
                            case 28:
                                {
                                alt5=2;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 9, input);

                                throw nvae;
                            }

                        }
                        else if ( (LA5_6==RULE_ID) ) {
                            switch ( input.LA(5) ) {
                            case RULE_INT:
                            case 28:
                                {
                                alt5=2;
                                }
                                break;
                            case 17:
                                {
                                alt5=1;
                                }
                                break;
                            case RULE_ID:
                                {
                                alt5=5;
                                }
                                break;
                            default:
                                NoViableAltException nvae =
                                    new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 10, input);

                                throw nvae;
                            }

                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_INT:
                    case 28:
                        {
                        alt5=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 5, input);

                        throw nvae;
                    }

                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
                {
                alt5=6;
                }
                break;
            case RULE_ID:
                {
                alt5=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("399:1: (lv_form_1_1= ruleRForm | lv_form_1_2= ruleIArithForm | lv_form_1_3= ruleIExpForm | lv_form_1_4= ruleILabelForm | lv_form_1_5= ruleIBr2Form | lv_form_1_6= ruleBExpForm | lv_form_1_7= ruleBLabelForm )", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:400:3: lv_form_1_1= ruleRForm
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormRFormParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleRForm_in_ruleInstruction576);
                    lv_form_1_1=ruleRForm();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_1, 
                    	        		"RForm", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:421:8: lv_form_1_2= ruleIArithForm
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormIArithFormParserRuleCall_1_0_1(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleIArithForm_in_ruleInstruction595);
                    lv_form_1_2=ruleIArithForm();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_2, 
                    	        		"IArithForm", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 3 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:442:8: lv_form_1_3= ruleIExpForm
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormIExpFormParserRuleCall_1_0_2(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleIExpForm_in_ruleInstruction614);
                    lv_form_1_3=ruleIExpForm();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_3, 
                    	        		"IExpForm", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 4 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:463:8: lv_form_1_4= ruleILabelForm
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormILabelFormParserRuleCall_1_0_3(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleILabelForm_in_ruleInstruction633);
                    lv_form_1_4=ruleILabelForm();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_4, 
                    	        		"ILabelForm", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 5 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:484:8: lv_form_1_5= ruleIBr2Form
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormIBr2FormParserRuleCall_1_0_4(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleIBr2Form_in_ruleInstruction652);
                    lv_form_1_5=ruleIBr2Form();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_5, 
                    	        		"IBr2Form", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 6 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:505:8: lv_form_1_6= ruleBExpForm
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormBExpFormParserRuleCall_1_0_5(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleBExpForm_in_ruleInstruction671);
                    lv_form_1_6=ruleBExpForm();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_6, 
                    	        		"BExpForm", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 7 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:526:8: lv_form_1_7= ruleBLabelForm
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInstructionAccess().getFormBLabelFormParserRuleCall_1_0_6(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleBLabelForm_in_ruleInstruction690);
                    lv_form_1_7=ruleBLabelForm();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInstructionRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"form",
                    	        		lv_form_1_7, 
                    	        		"BLabelForm", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleInstruction


    // $ANTLR start entryRuleRForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:558:1: entryRuleRForm returns [EObject current=null] : iv_ruleRForm= ruleRForm EOF ;
    public final EObject entryRuleRForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRForm = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:559:2: (iv_ruleRForm= ruleRForm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:560:2: iv_ruleRForm= ruleRForm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRFormRule(), currentNode); 
            pushFollow(FOLLOW_ruleRForm_in_entryRuleRForm729);
            iv_ruleRForm=ruleRForm();
            _fsp--;

             current =iv_ruleRForm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRForm739); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleRForm


    // $ANTLR start ruleRForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:567:1: ruleRForm returns [EObject current=null] : ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_rd_2_0= ruleReg ) ) ( (lv_shamt_3_0= RULE_INT ) )? ) ;
    public final EObject ruleRForm() throws RecognitionException {
        EObject current = null;

        Token lv_shamt_3_0=null;
        EObject lv_rt_0_0 = null;

        EObject lv_rs_1_0 = null;

        EObject lv_rd_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:572:6: ( ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_rd_2_0= ruleReg ) ) ( (lv_shamt_3_0= RULE_INT ) )? ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:573:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_rd_2_0= ruleReg ) ) ( (lv_shamt_3_0= RULE_INT ) )? )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:573:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_rd_2_0= ruleReg ) ) ( (lv_shamt_3_0= RULE_INT ) )? )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:573:2: ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_rd_2_0= ruleReg ) ) ( (lv_shamt_3_0= RULE_INT ) )?
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:573:2: ( (lv_rt_0_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:574:1: (lv_rt_0_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:574:1: (lv_rt_0_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:575:3: lv_rt_0_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRFormAccess().getRtRegParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleRForm785);
            lv_rt_0_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getRFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rt",
            	        		lv_rt_0_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:597:2: ( (lv_rs_1_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:598:1: (lv_rs_1_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:598:1: (lv_rs_1_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:599:3: lv_rs_1_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRFormAccess().getRsRegParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleRForm806);
            lv_rs_1_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getRFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rs",
            	        		lv_rs_1_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:621:2: ( (lv_rd_2_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:622:1: (lv_rd_2_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:622:1: (lv_rd_2_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:623:3: lv_rd_2_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getRFormAccess().getRdRegParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleRForm827);
            lv_rd_2_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getRFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rd",
            	        		lv_rd_2_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:645:2: ( (lv_shamt_3_0= RULE_INT ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_INT) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:646:1: (lv_shamt_3_0= RULE_INT )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:646:1: (lv_shamt_3_0= RULE_INT )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:647:3: lv_shamt_3_0= RULE_INT
                    {
                    lv_shamt_3_0=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleRForm844); 

                    			createLeafNode(grammarAccess.getRFormAccess().getShamtINTTerminalRuleCall_3_0(), "shamt"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRFormRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"shamt",
                    	        		lv_shamt_3_0, 
                    	        		"INT", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleRForm


    // $ANTLR start entryRuleIArithForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:677:1: entryRuleIArithForm returns [EObject current=null] : iv_ruleIArithForm= ruleIArithForm EOF ;
    public final EObject entryRuleIArithForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIArithForm = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:678:2: (iv_ruleIArithForm= ruleIArithForm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:679:2: iv_ruleIArithForm= ruleIArithForm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIArithFormRule(), currentNode); 
            pushFollow(FOLLOW_ruleIArithForm_in_entryRuleIArithForm886);
            iv_ruleIArithForm=ruleIArithForm();
            _fsp--;

             current =iv_ruleIArithForm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIArithForm896); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIArithForm


    // $ANTLR start ruleIArithForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:686:1: ruleIArithForm returns [EObject current=null] : ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_imm_2_0= rulePNInt ) ) ) ;
    public final EObject ruleIArithForm() throws RecognitionException {
        EObject current = null;

        EObject lv_rt_0_0 = null;

        EObject lv_rs_1_0 = null;

        AntlrDatatypeRuleToken lv_imm_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:691:6: ( ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_imm_2_0= rulePNInt ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:692:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_imm_2_0= rulePNInt ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:692:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_imm_2_0= rulePNInt ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:692:2: ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( (lv_imm_2_0= rulePNInt ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:692:2: ( (lv_rt_0_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:693:1: (lv_rt_0_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:693:1: (lv_rt_0_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:694:3: lv_rt_0_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIArithFormAccess().getRtRegParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleIArithForm942);
            lv_rt_0_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIArithFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rt",
            	        		lv_rt_0_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:716:2: ( (lv_rs_1_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:717:1: (lv_rs_1_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:717:1: (lv_rs_1_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:718:3: lv_rs_1_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIArithFormAccess().getRsRegParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleIArithForm963);
            lv_rs_1_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIArithFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rs",
            	        		lv_rs_1_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:740:2: ( (lv_imm_2_0= rulePNInt ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:741:1: (lv_imm_2_0= rulePNInt )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:741:1: (lv_imm_2_0= rulePNInt )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:742:3: lv_imm_2_0= rulePNInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIArithFormAccess().getImmPNIntParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePNInt_in_ruleIArithForm984);
            lv_imm_2_0=rulePNInt();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIArithFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"imm",
            	        		lv_imm_2_0, 
            	        		"PNInt", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIArithForm


    // $ANTLR start entryRuleIExpForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:772:1: entryRuleIExpForm returns [EObject current=null] : iv_ruleIExpForm= ruleIExpForm EOF ;
    public final EObject entryRuleIExpForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIExpForm = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:773:2: (iv_ruleIExpForm= ruleIExpForm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:774:2: iv_ruleIExpForm= ruleIExpForm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIExpFormRule(), currentNode); 
            pushFollow(FOLLOW_ruleIExpForm_in_entryRuleIExpForm1020);
            iv_ruleIExpForm=ruleIExpForm();
            _fsp--;

             current =iv_ruleIExpForm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIExpForm1030); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIExpForm


    // $ANTLR start ruleIExpForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:781:1: ruleIExpForm returns [EObject current=null] : ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_imm_1_0= rulePNInt ) ) '(' ( (lv_rs_3_0= ruleReg ) ) ')' ) ;
    public final EObject ruleIExpForm() throws RecognitionException {
        EObject current = null;

        EObject lv_rt_0_0 = null;

        AntlrDatatypeRuleToken lv_imm_1_0 = null;

        EObject lv_rs_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:786:6: ( ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_imm_1_0= rulePNInt ) ) '(' ( (lv_rs_3_0= ruleReg ) ) ')' ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:787:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_imm_1_0= rulePNInt ) ) '(' ( (lv_rs_3_0= ruleReg ) ) ')' )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:787:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_imm_1_0= rulePNInt ) ) '(' ( (lv_rs_3_0= ruleReg ) ) ')' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:787:2: ( (lv_rt_0_0= ruleReg ) ) ( (lv_imm_1_0= rulePNInt ) ) '(' ( (lv_rs_3_0= ruleReg ) ) ')'
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:787:2: ( (lv_rt_0_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:788:1: (lv_rt_0_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:788:1: (lv_rt_0_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:789:3: lv_rt_0_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIExpFormAccess().getRtRegParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleIExpForm1076);
            lv_rt_0_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIExpFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rt",
            	        		lv_rt_0_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:811:2: ( (lv_imm_1_0= rulePNInt ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:812:1: (lv_imm_1_0= rulePNInt )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:812:1: (lv_imm_1_0= rulePNInt )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:813:3: lv_imm_1_0= rulePNInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIExpFormAccess().getImmPNIntParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePNInt_in_ruleIExpForm1097);
            lv_imm_1_0=rulePNInt();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIExpFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"imm",
            	        		lv_imm_1_0, 
            	        		"PNInt", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,13,FOLLOW_13_in_ruleIExpForm1107); 

                    createLeafNode(grammarAccess.getIExpFormAccess().getLeftParenthesisKeyword_2(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:839:1: ( (lv_rs_3_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:840:1: (lv_rs_3_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:840:1: (lv_rs_3_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:841:3: lv_rs_3_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIExpFormAccess().getRsRegParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleIExpForm1128);
            lv_rs_3_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIExpFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rs",
            	        		lv_rs_3_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,14,FOLLOW_14_in_ruleIExpForm1138); 

                    createLeafNode(grammarAccess.getIExpFormAccess().getRightParenthesisKeyword_4(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIExpForm


    // $ANTLR start entryRuleILabelForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:875:1: entryRuleILabelForm returns [EObject current=null] : iv_ruleILabelForm= ruleILabelForm EOF ;
    public final EObject entryRuleILabelForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleILabelForm = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:876:2: (iv_ruleILabelForm= ruleILabelForm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:877:2: iv_ruleILabelForm= ruleILabelForm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getILabelFormRule(), currentNode); 
            pushFollow(FOLLOW_ruleILabelForm_in_entryRuleILabelForm1174);
            iv_ruleILabelForm=ruleILabelForm();
            _fsp--;

             current =iv_ruleILabelForm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleILabelForm1184); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleILabelForm


    // $ANTLR start ruleILabelForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:884:1: ruleILabelForm returns [EObject current=null] : ( ( (lv_reg_0_0= ruleReg ) ) ( ( RULE_ID ) ) ) ;
    public final EObject ruleILabelForm() throws RecognitionException {
        EObject current = null;

        EObject lv_reg_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:889:6: ( ( ( (lv_reg_0_0= ruleReg ) ) ( ( RULE_ID ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:890:1: ( ( (lv_reg_0_0= ruleReg ) ) ( ( RULE_ID ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:890:1: ( ( (lv_reg_0_0= ruleReg ) ) ( ( RULE_ID ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:890:2: ( (lv_reg_0_0= ruleReg ) ) ( ( RULE_ID ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:890:2: ( (lv_reg_0_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:891:1: (lv_reg_0_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:891:1: (lv_reg_0_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:892:3: lv_reg_0_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getILabelFormAccess().getRegRegParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleILabelForm1230);
            lv_reg_0_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getILabelFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"reg",
            	        		lv_reg_0_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:914:2: ( ( RULE_ID ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:915:1: ( RULE_ID )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:915:1: ( RULE_ID )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:916:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getILabelFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleILabelForm1248); 

            		createLeafNode(grammarAccess.getILabelFormAccess().getLabelLabelCrossReference_1_0(), "label"); 
            	

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleILabelForm


    // $ANTLR start entryRuleIBr2Form
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:936:1: entryRuleIBr2Form returns [EObject current=null] : iv_ruleIBr2Form= ruleIBr2Form EOF ;
    public final EObject entryRuleIBr2Form() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIBr2Form = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:937:2: (iv_ruleIBr2Form= ruleIBr2Form EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:938:2: iv_ruleIBr2Form= ruleIBr2Form EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIBr2FormRule(), currentNode); 
            pushFollow(FOLLOW_ruleIBr2Form_in_entryRuleIBr2Form1284);
            iv_ruleIBr2Form=ruleIBr2Form();
            _fsp--;

             current =iv_ruleIBr2Form; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIBr2Form1294); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIBr2Form


    // $ANTLR start ruleIBr2Form
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:945:1: ruleIBr2Form returns [EObject current=null] : ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( ( RULE_ID ) ) ) ;
    public final EObject ruleIBr2Form() throws RecognitionException {
        EObject current = null;

        EObject lv_rt_0_0 = null;

        EObject lv_rs_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:950:6: ( ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( ( RULE_ID ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:951:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( ( RULE_ID ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:951:1: ( ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( ( RULE_ID ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:951:2: ( (lv_rt_0_0= ruleReg ) ) ( (lv_rs_1_0= ruleReg ) ) ( ( RULE_ID ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:951:2: ( (lv_rt_0_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:952:1: (lv_rt_0_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:952:1: (lv_rt_0_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:953:3: lv_rt_0_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIBr2FormAccess().getRtRegParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleIBr2Form1340);
            lv_rt_0_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIBr2FormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rt",
            	        		lv_rt_0_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:975:2: ( (lv_rs_1_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:976:1: (lv_rs_1_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:976:1: (lv_rs_1_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:977:3: lv_rs_1_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIBr2FormAccess().getRsRegParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleIBr2Form1361);
            lv_rs_1_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIBr2FormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rs",
            	        		lv_rs_1_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:999:2: ( ( RULE_ID ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1000:1: ( RULE_ID )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1000:1: ( RULE_ID )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1001:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getIBr2FormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIBr2Form1379); 

            		createLeafNode(grammarAccess.getIBr2FormAccess().getLabelLabelCrossReference_2_0(), "label"); 
            	

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIBr2Form


    // $ANTLR start entryRuleBExpForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1021:1: entryRuleBExpForm returns [EObject current=null] : iv_ruleBExpForm= ruleBExpForm EOF ;
    public final EObject entryRuleBExpForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBExpForm = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1022:2: (iv_ruleBExpForm= ruleBExpForm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1023:2: iv_ruleBExpForm= ruleBExpForm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBExpFormRule(), currentNode); 
            pushFollow(FOLLOW_ruleBExpForm_in_entryRuleBExpForm1415);
            iv_ruleBExpForm=ruleBExpForm();
            _fsp--;

             current =iv_ruleBExpForm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBExpForm1425); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleBExpForm


    // $ANTLR start ruleBExpForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1030:1: ruleBExpForm returns [EObject current=null] : ( (lv_addr_0_0= RULE_INT ) ) ;
    public final EObject ruleBExpForm() throws RecognitionException {
        EObject current = null;

        Token lv_addr_0_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1035:6: ( ( (lv_addr_0_0= RULE_INT ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1036:1: ( (lv_addr_0_0= RULE_INT ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1036:1: ( (lv_addr_0_0= RULE_INT ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1037:1: (lv_addr_0_0= RULE_INT )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1037:1: (lv_addr_0_0= RULE_INT )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1038:3: lv_addr_0_0= RULE_INT
            {
            lv_addr_0_0=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleBExpForm1466); 

            			createLeafNode(grammarAccess.getBExpFormAccess().getAddrINTTerminalRuleCall_0(), "addr"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getBExpFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"addr",
            	        		lv_addr_0_0, 
            	        		"INT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleBExpForm


    // $ANTLR start entryRuleBLabelForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1068:1: entryRuleBLabelForm returns [EObject current=null] : iv_ruleBLabelForm= ruleBLabelForm EOF ;
    public final EObject entryRuleBLabelForm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBLabelForm = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1069:2: (iv_ruleBLabelForm= ruleBLabelForm EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1070:2: iv_ruleBLabelForm= ruleBLabelForm EOF
            {
             currentNode = createCompositeNode(grammarAccess.getBLabelFormRule(), currentNode); 
            pushFollow(FOLLOW_ruleBLabelForm_in_entryRuleBLabelForm1506);
            iv_ruleBLabelForm=ruleBLabelForm();
            _fsp--;

             current =iv_ruleBLabelForm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleBLabelForm1516); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleBLabelForm


    // $ANTLR start ruleBLabelForm
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1077:1: ruleBLabelForm returns [EObject current=null] : ( ( RULE_ID ) ) ;
    public final EObject ruleBLabelForm() throws RecognitionException {
        EObject current = null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1082:6: ( ( ( RULE_ID ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1083:1: ( ( RULE_ID ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1083:1: ( ( RULE_ID ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1084:1: ( RULE_ID )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1084:1: ( RULE_ID )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1085:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getBLabelFormRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleBLabelForm1558); 

            		createLeafNode(grammarAccess.getBLabelFormAccess().getLabelLabelCrossReference_0(), "label"); 
            	

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleBLabelForm


    // $ANTLR start entryRuleSpecialInsn
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1105:1: entryRuleSpecialInsn returns [EObject current=null] : iv_ruleSpecialInsn= ruleSpecialInsn EOF ;
    public final EObject entryRuleSpecialInsn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpecialInsn = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1106:2: (iv_ruleSpecialInsn= ruleSpecialInsn EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1107:2: iv_ruleSpecialInsn= ruleSpecialInsn EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSpecialInsnRule(), currentNode); 
            pushFollow(FOLLOW_ruleSpecialInsn_in_entryRuleSpecialInsn1593);
            iv_ruleSpecialInsn=ruleSpecialInsn();
            _fsp--;

             current =iv_ruleSpecialInsn; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSpecialInsn1603); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSpecialInsn


    // $ANTLR start ruleSpecialInsn
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1114:1: ruleSpecialInsn returns [EObject current=null] : ( ( (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant ) ) ) ;
    public final EObject ruleSpecialInsn() throws RecognitionException {
        EObject current = null;

        EObject lv_insn_0_1 = null;

        EObject lv_insn_0_2 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1119:6: ( ( ( (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1120:1: ( ( (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1120:1: ( ( (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1121:1: ( (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1121:1: ( (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1122:1: (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1122:1: (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==16) ) {
                alt7=1;
            }
            else if ( (LA7_0==15) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1122:1: (lv_insn_0_1= ruleLoadAddress | lv_insn_0_2= ruleLoadConstant )", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1123:3: lv_insn_0_1= ruleLoadAddress
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpecialInsnAccess().getInsnLoadAddressParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleLoadAddress_in_ruleSpecialInsn1650);
                    lv_insn_0_1=ruleLoadAddress();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpecialInsnRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"insn",
                    	        		lv_insn_0_1, 
                    	        		"LoadAddress", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1144:8: lv_insn_0_2= ruleLoadConstant
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSpecialInsnAccess().getInsnLoadConstantParserRuleCall_0_1(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleLoadConstant_in_ruleSpecialInsn1669);
                    lv_insn_0_2=ruleLoadConstant();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSpecialInsnRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"insn",
                    	        		lv_insn_0_2, 
                    	        		"LoadConstant", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSpecialInsn


    // $ANTLR start entryRuleLoadConstant
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1176:1: entryRuleLoadConstant returns [EObject current=null] : iv_ruleLoadConstant= ruleLoadConstant EOF ;
    public final EObject entryRuleLoadConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadConstant = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1177:2: (iv_ruleLoadConstant= ruleLoadConstant EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1178:2: iv_ruleLoadConstant= ruleLoadConstant EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLoadConstantRule(), currentNode); 
            pushFollow(FOLLOW_ruleLoadConstant_in_entryRuleLoadConstant1707);
            iv_ruleLoadConstant=ruleLoadConstant();
            _fsp--;

             current =iv_ruleLoadConstant; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLoadConstant1717); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLoadConstant


    // $ANTLR start ruleLoadConstant
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1185:1: ruleLoadConstant returns [EObject current=null] : ( 'li' ( (lv_rt_1_0= ruleReg ) ) ( (lv_val_2_0= RULE_INT ) ) ) ;
    public final EObject ruleLoadConstant() throws RecognitionException {
        EObject current = null;

        Token lv_val_2_0=null;
        EObject lv_rt_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1190:6: ( ( 'li' ( (lv_rt_1_0= ruleReg ) ) ( (lv_val_2_0= RULE_INT ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1191:1: ( 'li' ( (lv_rt_1_0= ruleReg ) ) ( (lv_val_2_0= RULE_INT ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1191:1: ( 'li' ( (lv_rt_1_0= ruleReg ) ) ( (lv_val_2_0= RULE_INT ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1191:3: 'li' ( (lv_rt_1_0= ruleReg ) ) ( (lv_val_2_0= RULE_INT ) )
            {
            match(input,15,FOLLOW_15_in_ruleLoadConstant1752); 

                    createLeafNode(grammarAccess.getLoadConstantAccess().getLiKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1195:1: ( (lv_rt_1_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1196:1: (lv_rt_1_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1196:1: (lv_rt_1_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1197:3: lv_rt_1_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLoadConstantAccess().getRtRegParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleLoadConstant1773);
            lv_rt_1_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLoadConstantRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rt",
            	        		lv_rt_1_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1219:2: ( (lv_val_2_0= RULE_INT ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1220:1: (lv_val_2_0= RULE_INT )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1220:1: (lv_val_2_0= RULE_INT )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1221:3: lv_val_2_0= RULE_INT
            {
            lv_val_2_0=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleLoadConstant1790); 

            			createLeafNode(grammarAccess.getLoadConstantAccess().getValINTTerminalRuleCall_2_0(), "val"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLoadConstantRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"val",
            	        		lv_val_2_0, 
            	        		"INT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLoadConstant


    // $ANTLR start entryRuleLoadAddress
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1251:1: entryRuleLoadAddress returns [EObject current=null] : iv_ruleLoadAddress= ruleLoadAddress EOF ;
    public final EObject entryRuleLoadAddress() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLoadAddress = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1252:2: (iv_ruleLoadAddress= ruleLoadAddress EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1253:2: iv_ruleLoadAddress= ruleLoadAddress EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLoadAddressRule(), currentNode); 
            pushFollow(FOLLOW_ruleLoadAddress_in_entryRuleLoadAddress1831);
            iv_ruleLoadAddress=ruleLoadAddress();
            _fsp--;

             current =iv_ruleLoadAddress; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLoadAddress1841); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLoadAddress


    // $ANTLR start ruleLoadAddress
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1260:1: ruleLoadAddress returns [EObject current=null] : ( 'la' ( (lv_rt_1_0= ruleReg ) ) ( ( RULE_ID ) ) ) ;
    public final EObject ruleLoadAddress() throws RecognitionException {
        EObject current = null;

        EObject lv_rt_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1265:6: ( ( 'la' ( (lv_rt_1_0= ruleReg ) ) ( ( RULE_ID ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1266:1: ( 'la' ( (lv_rt_1_0= ruleReg ) ) ( ( RULE_ID ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1266:1: ( 'la' ( (lv_rt_1_0= ruleReg ) ) ( ( RULE_ID ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1266:3: 'la' ( (lv_rt_1_0= ruleReg ) ) ( ( RULE_ID ) )
            {
            match(input,16,FOLLOW_16_in_ruleLoadAddress1876); 

                    createLeafNode(grammarAccess.getLoadAddressAccess().getLaKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1270:1: ( (lv_rt_1_0= ruleReg ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1271:1: (lv_rt_1_0= ruleReg )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1271:1: (lv_rt_1_0= ruleReg )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1272:3: lv_rt_1_0= ruleReg
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getLoadAddressAccess().getRtRegParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleReg_in_ruleLoadAddress1897);
            lv_rt_1_0=ruleReg();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLoadAddressRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"rt",
            	        		lv_rt_1_0, 
            	        		"Reg", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1294:2: ( ( RULE_ID ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1295:1: ( RULE_ID )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1295:1: ( RULE_ID )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1296:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getLoadAddressRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLoadAddress1915); 

            		createLeafNode(grammarAccess.getLoadAddressAccess().getLabelLabelCrossReference_2_0(), "label"); 
            	

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLoadAddress


    // $ANTLR start entryRuleReg
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1316:1: entryRuleReg returns [EObject current=null] : iv_ruleReg= ruleReg EOF ;
    public final EObject entryRuleReg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReg = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1317:2: (iv_ruleReg= ruleReg EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1318:2: iv_ruleReg= ruleReg EOF
            {
             currentNode = createCompositeNode(grammarAccess.getRegRule(), currentNode); 
            pushFollow(FOLLOW_ruleReg_in_entryRuleReg1951);
            iv_ruleReg=ruleReg();
            _fsp--;

             current =iv_ruleReg; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleReg1961); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleReg


    // $ANTLR start ruleReg
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1325:1: ruleReg returns [EObject current=null] : ( '$' ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) ) ) ;
    public final EObject ruleReg() throws RecognitionException {
        EObject current = null;

        Token lv_num_1_0=null;
        Token lv_name_2_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1330:6: ( ( '$' ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1331:1: ( '$' ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1331:1: ( '$' ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1331:3: '$' ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) )
            {
            match(input,17,FOLLOW_17_in_ruleReg1996); 

                    createLeafNode(grammarAccess.getRegAccess().getDollarSignKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1335:1: ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_INT) ) {
                alt8=1;
            }
            else if ( (LA8_0==RULE_ID) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("1335:1: ( ( (lv_num_1_0= RULE_INT ) ) | ( (lv_name_2_0= RULE_ID ) ) )", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1335:2: ( (lv_num_1_0= RULE_INT ) )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1335:2: ( (lv_num_1_0= RULE_INT ) )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1336:1: (lv_num_1_0= RULE_INT )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1336:1: (lv_num_1_0= RULE_INT )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1337:3: lv_num_1_0= RULE_INT
                    {
                    lv_num_1_0=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleReg2014); 

                    			createLeafNode(grammarAccess.getRegAccess().getNumINTTerminalRuleCall_1_0_0(), "num"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRegRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"num",
                    	        		lv_num_1_0, 
                    	        		"INT", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1360:6: ( (lv_name_2_0= RULE_ID ) )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1360:6: ( (lv_name_2_0= RULE_ID ) )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1361:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1361:1: (lv_name_2_0= RULE_ID )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1362:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleReg2042); 

                    			createLeafNode(grammarAccess.getRegAccess().getNameIDTerminalRuleCall_1_1_0(), "name"); 
                    		

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getRegRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_2_0, 
                    	        		"ID", 
                    	        		lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleReg


    // $ANTLR start entryRuleDataSegment
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1392:1: entryRuleDataSegment returns [EObject current=null] : iv_ruleDataSegment= ruleDataSegment EOF ;
    public final EObject entryRuleDataSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataSegment = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1393:2: (iv_ruleDataSegment= ruleDataSegment EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1394:2: iv_ruleDataSegment= ruleDataSegment EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDataSegmentRule(), currentNode); 
            pushFollow(FOLLOW_ruleDataSegment_in_entryRuleDataSegment2084);
            iv_ruleDataSegment=ruleDataSegment();
            _fsp--;

             current =iv_ruleDataSegment; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataSegment2094); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDataSegment


    // $ANTLR start ruleDataSegment
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1401:1: ruleDataSegment returns [EObject current=null] : ( '.data' () ( (lv_items_2_0= ruleDataItem ) )* ) ;
    public final EObject ruleDataSegment() throws RecognitionException {
        EObject current = null;

        EObject lv_items_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1406:6: ( ( '.data' () ( (lv_items_2_0= ruleDataItem ) )* ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1407:1: ( '.data' () ( (lv_items_2_0= ruleDataItem ) )* )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1407:1: ( '.data' () ( (lv_items_2_0= ruleDataItem ) )* )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1407:3: '.data' () ( (lv_items_2_0= ruleDataItem ) )*
            {
            match(input,18,FOLLOW_18_in_ruleDataSegment2129); 

                    createLeafNode(grammarAccess.getDataSegmentAccess().getDataKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1411:1: ()
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1412:5: 
            {
             
                    temp=factory.create(grammarAccess.getDataSegmentAccess().getDataSegmentAction_1().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDataSegmentAccess().getDataSegmentAction_1(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1422:2: ( (lv_items_2_0= ruleDataItem ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID||(LA9_0>=20 && LA9_0<=26)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1423:1: (lv_items_2_0= ruleDataItem )
            	    {
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1423:1: (lv_items_2_0= ruleDataItem )
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1424:3: lv_items_2_0= ruleDataItem
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDataSegmentAccess().getItemsDataItemParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDataItem_in_ruleDataSegment2159);
            	    lv_items_2_0=ruleDataItem();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDataSegmentRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"items",
            	    	        		lv_items_2_0, 
            	    	        		"DataItem", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDataSegment


    // $ANTLR start entryRuleDataItem
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1454:1: entryRuleDataItem returns [EObject current=null] : iv_ruleDataItem= ruleDataItem EOF ;
    public final EObject entryRuleDataItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataItem = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1455:2: (iv_ruleDataItem= ruleDataItem EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1456:2: iv_ruleDataItem= ruleDataItem EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDataItemRule(), currentNode); 
            pushFollow(FOLLOW_ruleDataItem_in_entryRuleDataItem2196);
            iv_ruleDataItem=ruleDataItem();
            _fsp--;

             current =iv_ruleDataItem; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataItem2206); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDataItem


    // $ANTLR start ruleDataItem
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1463:1: ruleDataItem returns [EObject current=null] : ( ( (lv_label_0_0= ruleLabel ) )? ( (lv_data_1_0= ruleDataDecl ) ) ) ;
    public final EObject ruleDataItem() throws RecognitionException {
        EObject current = null;

        EObject lv_label_0_0 = null;

        EObject lv_data_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1468:6: ( ( ( (lv_label_0_0= ruleLabel ) )? ( (lv_data_1_0= ruleDataDecl ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1469:1: ( ( (lv_label_0_0= ruleLabel ) )? ( (lv_data_1_0= ruleDataDecl ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1469:1: ( ( (lv_label_0_0= ruleLabel ) )? ( (lv_data_1_0= ruleDataDecl ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1469:2: ( (lv_label_0_0= ruleLabel ) )? ( (lv_data_1_0= ruleDataDecl ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1469:2: ( (lv_label_0_0= ruleLabel ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ID) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1470:1: (lv_label_0_0= ruleLabel )
                    {
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1470:1: (lv_label_0_0= ruleLabel )
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1471:3: lv_label_0_0= ruleLabel
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataItemAccess().getLabelLabelParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleLabel_in_ruleDataItem2252);
                    lv_label_0_0=ruleLabel();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataItemRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"label",
                    	        		lv_label_0_0, 
                    	        		"Label", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1493:3: ( (lv_data_1_0= ruleDataDecl ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1494:1: (lv_data_1_0= ruleDataDecl )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1494:1: (lv_data_1_0= ruleDataDecl )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1495:3: lv_data_1_0= ruleDataDecl
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDataItemAccess().getDataDataDeclParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleDataDecl_in_ruleDataItem2274);
            lv_data_1_0=ruleDataDecl();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDataItemRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"data",
            	        		lv_data_1_0, 
            	        		"DataDecl", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDataItem


    // $ANTLR start entryRuleDataDecl
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1525:1: entryRuleDataDecl returns [EObject current=null] : iv_ruleDataDecl= ruleDataDecl EOF ;
    public final EObject entryRuleDataDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataDecl = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1526:2: (iv_ruleDataDecl= ruleDataDecl EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1527:2: iv_ruleDataDecl= ruleDataDecl EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDataDeclRule(), currentNode); 
            pushFollow(FOLLOW_ruleDataDecl_in_entryRuleDataDecl2310);
            iv_ruleDataDecl=ruleDataDecl();
            _fsp--;

             current =iv_ruleDataDecl; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataDecl2320); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDataDecl


    // $ANTLR start ruleDataDecl
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1534:1: ruleDataDecl returns [EObject current=null] : ( ( (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord ) ) ) ;
    public final EObject ruleDataDecl() throws RecognitionException {
        EObject current = null;

        EObject lv_item_0_1 = null;

        EObject lv_item_0_2 = null;

        EObject lv_item_0_3 = null;

        EObject lv_item_0_4 = null;

        EObject lv_item_0_5 = null;

        EObject lv_item_0_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1539:6: ( ( ( (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1540:1: ( ( (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1540:1: ( ( (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1541:1: ( (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1541:1: ( (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1542:1: (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1542:1: (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord )
            int alt11=6;
            switch ( input.LA(1) ) {
            case 25:
            case 26:
                {
                alt11=1;
                }
                break;
            case 20:
                {
                alt11=2;
                }
                break;
            case 21:
                {
                alt11=3;
                }
                break;
            case 24:
                {
                alt11=4;
                }
                break;
            case 23:
                {
                alt11=5;
                }
                break;
            case 22:
                {
                alt11=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("1542:1: (lv_item_0_1= ruleStr | lv_item_0_2= ruleAlign | lv_item_0_3= ruleSpace | lv_item_0_4= ruleByte | lv_item_0_5= ruleHalf | lv_item_0_6= ruleWord )", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1543:3: lv_item_0_1= ruleStr
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataDeclAccess().getItemStrParserRuleCall_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleStr_in_ruleDataDecl2367);
                    lv_item_0_1=ruleStr();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_0_1, 
                    	        		"Str", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1564:8: lv_item_0_2= ruleAlign
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataDeclAccess().getItemAlignParserRuleCall_0_1(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleAlign_in_ruleDataDecl2386);
                    lv_item_0_2=ruleAlign();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_0_2, 
                    	        		"Align", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 3 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1585:8: lv_item_0_3= ruleSpace
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataDeclAccess().getItemSpaceParserRuleCall_0_2(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleSpace_in_ruleDataDecl2405);
                    lv_item_0_3=ruleSpace();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_0_3, 
                    	        		"Space", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 4 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1606:8: lv_item_0_4= ruleByte
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataDeclAccess().getItemByteParserRuleCall_0_3(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleByte_in_ruleDataDecl2424);
                    lv_item_0_4=ruleByte();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_0_4, 
                    	        		"Byte", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 5 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1627:8: lv_item_0_5= ruleHalf
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataDeclAccess().getItemHalfParserRuleCall_0_4(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleHalf_in_ruleDataDecl2443);
                    lv_item_0_5=ruleHalf();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_0_5, 
                    	        		"Half", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 6 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1648:8: lv_item_0_6= ruleWord
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDataDeclAccess().getItemWordParserRuleCall_0_5(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleWord_in_ruleDataDecl2462);
                    lv_item_0_6=ruleWord();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDataDeclRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"item",
                    	        		lv_item_0_6, 
                    	        		"Word", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDataDecl


    // $ANTLR start entryRuleLabel
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1680:1: entryRuleLabel returns [EObject current=null] : iv_ruleLabel= ruleLabel EOF ;
    public final EObject entryRuleLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLabel = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1681:2: (iv_ruleLabel= ruleLabel EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1682:2: iv_ruleLabel= ruleLabel EOF
            {
             currentNode = createCompositeNode(grammarAccess.getLabelRule(), currentNode); 
            pushFollow(FOLLOW_ruleLabel_in_entryRuleLabel2500);
            iv_ruleLabel=ruleLabel();
            _fsp--;

             current =iv_ruleLabel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLabel2510); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleLabel


    // $ANTLR start ruleLabel
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1689:1: ruleLabel returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ':' ) ;
    public final EObject ruleLabel() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1694:6: ( ( ( (lv_name_0_0= RULE_ID ) ) ':' ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1695:1: ( ( (lv_name_0_0= RULE_ID ) ) ':' )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1695:1: ( ( (lv_name_0_0= RULE_ID ) ) ':' )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1695:2: ( (lv_name_0_0= RULE_ID ) ) ':'
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1695:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1696:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1696:1: (lv_name_0_0= RULE_ID )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1697:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleLabel2552); 

            			createLeafNode(grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_0_0(), "name"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getLabelRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_0_0, 
            	        		"ID", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }

            match(input,19,FOLLOW_19_in_ruleLabel2567); 

                    createLeafNode(grammarAccess.getLabelAccess().getColonKeyword_1(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleLabel


    // $ANTLR start entryRuleAlign
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1731:1: entryRuleAlign returns [EObject current=null] : iv_ruleAlign= ruleAlign EOF ;
    public final EObject entryRuleAlign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAlign = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1732:2: (iv_ruleAlign= ruleAlign EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1733:2: iv_ruleAlign= ruleAlign EOF
            {
             currentNode = createCompositeNode(grammarAccess.getAlignRule(), currentNode); 
            pushFollow(FOLLOW_ruleAlign_in_entryRuleAlign2603);
            iv_ruleAlign=ruleAlign();
            _fsp--;

             current =iv_ruleAlign; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAlign2613); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleAlign


    // $ANTLR start ruleAlign
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1740:1: ruleAlign returns [EObject current=null] : ( '.align' ( (lv_align_1_0= RULE_INT ) ) ) ;
    public final EObject ruleAlign() throws RecognitionException {
        EObject current = null;

        Token lv_align_1_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1745:6: ( ( '.align' ( (lv_align_1_0= RULE_INT ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1746:1: ( '.align' ( (lv_align_1_0= RULE_INT ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1746:1: ( '.align' ( (lv_align_1_0= RULE_INT ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1746:3: '.align' ( (lv_align_1_0= RULE_INT ) )
            {
            match(input,20,FOLLOW_20_in_ruleAlign2648); 

                    createLeafNode(grammarAccess.getAlignAccess().getAlignKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1750:1: ( (lv_align_1_0= RULE_INT ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1751:1: (lv_align_1_0= RULE_INT )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1751:1: (lv_align_1_0= RULE_INT )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1752:3: lv_align_1_0= RULE_INT
            {
            lv_align_1_0=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAlign2665); 

            			createLeafNode(grammarAccess.getAlignAccess().getAlignINTTerminalRuleCall_1_0(), "align"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getAlignRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"align",
            	        		lv_align_1_0, 
            	        		"INT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleAlign


    // $ANTLR start entryRuleSpace
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1782:1: entryRuleSpace returns [EObject current=null] : iv_ruleSpace= ruleSpace EOF ;
    public final EObject entryRuleSpace() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpace = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1783:2: (iv_ruleSpace= ruleSpace EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1784:2: iv_ruleSpace= ruleSpace EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSpaceRule(), currentNode); 
            pushFollow(FOLLOW_ruleSpace_in_entryRuleSpace2706);
            iv_ruleSpace=ruleSpace();
            _fsp--;

             current =iv_ruleSpace; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSpace2716); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSpace


    // $ANTLR start ruleSpace
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1791:1: ruleSpace returns [EObject current=null] : ( '.space' ( (lv_bytes_1_0= RULE_INT ) ) ) ;
    public final EObject ruleSpace() throws RecognitionException {
        EObject current = null;

        Token lv_bytes_1_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1796:6: ( ( '.space' ( (lv_bytes_1_0= RULE_INT ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1797:1: ( '.space' ( (lv_bytes_1_0= RULE_INT ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1797:1: ( '.space' ( (lv_bytes_1_0= RULE_INT ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1797:3: '.space' ( (lv_bytes_1_0= RULE_INT ) )
            {
            match(input,21,FOLLOW_21_in_ruleSpace2751); 

                    createLeafNode(grammarAccess.getSpaceAccess().getSpaceKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1801:1: ( (lv_bytes_1_0= RULE_INT ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1802:1: (lv_bytes_1_0= RULE_INT )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1802:1: (lv_bytes_1_0= RULE_INT )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1803:3: lv_bytes_1_0= RULE_INT
            {
            lv_bytes_1_0=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSpace2768); 

            			createLeafNode(grammarAccess.getSpaceAccess().getBytesINTTerminalRuleCall_1_0(), "bytes"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getSpaceRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"bytes",
            	        		lv_bytes_1_0, 
            	        		"INT", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSpace


    // $ANTLR start entryRuleWord
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1833:1: entryRuleWord returns [EObject current=null] : iv_ruleWord= ruleWord EOF ;
    public final EObject entryRuleWord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWord = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1834:2: (iv_ruleWord= ruleWord EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1835:2: iv_ruleWord= ruleWord EOF
            {
             currentNode = createCompositeNode(grammarAccess.getWordRule(), currentNode); 
            pushFollow(FOLLOW_ruleWord_in_entryRuleWord2809);
            iv_ruleWord=ruleWord();
            _fsp--;

             current =iv_ruleWord; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWord2819); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleWord


    // $ANTLR start ruleWord
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1842:1: ruleWord returns [EObject current=null] : ( '.word' ( (lv_val_1_0= ruleIntList ) ) ) ;
    public final EObject ruleWord() throws RecognitionException {
        EObject current = null;

        EObject lv_val_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1847:6: ( ( '.word' ( (lv_val_1_0= ruleIntList ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1848:1: ( '.word' ( (lv_val_1_0= ruleIntList ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1848:1: ( '.word' ( (lv_val_1_0= ruleIntList ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1848:3: '.word' ( (lv_val_1_0= ruleIntList ) )
            {
            match(input,22,FOLLOW_22_in_ruleWord2854); 

                    createLeafNode(grammarAccess.getWordAccess().getWordKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1852:1: ( (lv_val_1_0= ruleIntList ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1853:1: (lv_val_1_0= ruleIntList )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1853:1: (lv_val_1_0= ruleIntList )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1854:3: lv_val_1_0= ruleIntList
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getWordAccess().getValIntListParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleIntList_in_ruleWord2875);
            lv_val_1_0=ruleIntList();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getWordRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"val",
            	        		lv_val_1_0, 
            	        		"IntList", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleWord


    // $ANTLR start entryRuleHalf
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1884:1: entryRuleHalf returns [EObject current=null] : iv_ruleHalf= ruleHalf EOF ;
    public final EObject entryRuleHalf() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHalf = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1885:2: (iv_ruleHalf= ruleHalf EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1886:2: iv_ruleHalf= ruleHalf EOF
            {
             currentNode = createCompositeNode(grammarAccess.getHalfRule(), currentNode); 
            pushFollow(FOLLOW_ruleHalf_in_entryRuleHalf2911);
            iv_ruleHalf=ruleHalf();
            _fsp--;

             current =iv_ruleHalf; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleHalf2921); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleHalf


    // $ANTLR start ruleHalf
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1893:1: ruleHalf returns [EObject current=null] : ( '.half' ( (lv_val_1_0= ruleIntList ) ) ) ;
    public final EObject ruleHalf() throws RecognitionException {
        EObject current = null;

        EObject lv_val_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1898:6: ( ( '.half' ( (lv_val_1_0= ruleIntList ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1899:1: ( '.half' ( (lv_val_1_0= ruleIntList ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1899:1: ( '.half' ( (lv_val_1_0= ruleIntList ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1899:3: '.half' ( (lv_val_1_0= ruleIntList ) )
            {
            match(input,23,FOLLOW_23_in_ruleHalf2956); 

                    createLeafNode(grammarAccess.getHalfAccess().getHalfKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1903:1: ( (lv_val_1_0= ruleIntList ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1904:1: (lv_val_1_0= ruleIntList )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1904:1: (lv_val_1_0= ruleIntList )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1905:3: lv_val_1_0= ruleIntList
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getHalfAccess().getValIntListParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleIntList_in_ruleHalf2977);
            lv_val_1_0=ruleIntList();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getHalfRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"val",
            	        		lv_val_1_0, 
            	        		"IntList", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleHalf


    // $ANTLR start entryRuleByte
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1935:1: entryRuleByte returns [EObject current=null] : iv_ruleByte= ruleByte EOF ;
    public final EObject entryRuleByte() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleByte = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1936:2: (iv_ruleByte= ruleByte EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1937:2: iv_ruleByte= ruleByte EOF
            {
             currentNode = createCompositeNode(grammarAccess.getByteRule(), currentNode); 
            pushFollow(FOLLOW_ruleByte_in_entryRuleByte3013);
            iv_ruleByte=ruleByte();
            _fsp--;

             current =iv_ruleByte; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleByte3023); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleByte


    // $ANTLR start ruleByte
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1944:1: ruleByte returns [EObject current=null] : ( '.byte' ( (lv_val_1_0= ruleIntList ) ) ) ;
    public final EObject ruleByte() throws RecognitionException {
        EObject current = null;

        EObject lv_val_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1949:6: ( ( '.byte' ( (lv_val_1_0= ruleIntList ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1950:1: ( '.byte' ( (lv_val_1_0= ruleIntList ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1950:1: ( '.byte' ( (lv_val_1_0= ruleIntList ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1950:3: '.byte' ( (lv_val_1_0= ruleIntList ) )
            {
            match(input,24,FOLLOW_24_in_ruleByte3058); 

                    createLeafNode(grammarAccess.getByteAccess().getByteKeyword_0(), null); 
                
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1954:1: ( (lv_val_1_0= ruleIntList ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1955:1: (lv_val_1_0= ruleIntList )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1955:1: (lv_val_1_0= ruleIntList )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1956:3: lv_val_1_0= ruleIntList
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getByteAccess().getValIntListParserRuleCall_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleIntList_in_ruleByte3079);
            lv_val_1_0=ruleIntList();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getByteRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"val",
            	        		lv_val_1_0, 
            	        		"IntList", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleByte


    // $ANTLR start entryRuleStr
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1986:1: entryRuleStr returns [EObject current=null] : iv_ruleStr= ruleStr EOF ;
    public final EObject entryRuleStr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStr = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1987:2: (iv_ruleStr= ruleStr EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1988:2: iv_ruleStr= ruleStr EOF
            {
             currentNode = createCompositeNode(grammarAccess.getStrRule(), currentNode); 
            pushFollow(FOLLOW_ruleStr_in_entryRuleStr3115);
            iv_ruleStr=ruleStr();
            _fsp--;

             current =iv_ruleStr; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStr3125); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleStr


    // $ANTLR start ruleStr
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:1995:1: ruleStr returns [EObject current=null] : ( ( '.asciiz' | '.ascii' ) ( (lv_val_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleStr() throws RecognitionException {
        EObject current = null;

        Token lv_val_2_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2000:6: ( ( ( '.asciiz' | '.ascii' ) ( (lv_val_2_0= RULE_STRING ) ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2001:1: ( ( '.asciiz' | '.ascii' ) ( (lv_val_2_0= RULE_STRING ) ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2001:1: ( ( '.asciiz' | '.ascii' ) ( (lv_val_2_0= RULE_STRING ) ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2001:2: ( '.asciiz' | '.ascii' ) ( (lv_val_2_0= RULE_STRING ) )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2001:2: ( '.asciiz' | '.ascii' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==25) ) {
                alt12=1;
            }
            else if ( (LA12_0==26) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2001:2: ( '.asciiz' | '.ascii' )", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2001:4: '.asciiz'
                    {
                    match(input,25,FOLLOW_25_in_ruleStr3161); 

                            createLeafNode(grammarAccess.getStrAccess().getAsciizKeyword_0_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2006:7: '.ascii'
                    {
                    match(input,26,FOLLOW_26_in_ruleStr3177); 

                            createLeafNode(grammarAccess.getStrAccess().getAsciiKeyword_0_1(), null); 
                        

                    }
                    break;

            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2010:2: ( (lv_val_2_0= RULE_STRING ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2011:1: (lv_val_2_0= RULE_STRING )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2011:1: (lv_val_2_0= RULE_STRING )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2012:3: lv_val_2_0= RULE_STRING
            {
            lv_val_2_0=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStr3195); 

            			createLeafNode(grammarAccess.getStrAccess().getValSTRINGTerminalRuleCall_1_0(), "val"); 
            		

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getStrRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"val",
            	        		lv_val_2_0, 
            	        		"STRING", 
            	        		lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleStr


    // $ANTLR start entryRuleIntList
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2042:1: entryRuleIntList returns [EObject current=null] : iv_ruleIntList= ruleIntList EOF ;
    public final EObject entryRuleIntList() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntList = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2043:2: (iv_ruleIntList= ruleIntList EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2044:2: iv_ruleIntList= ruleIntList EOF
            {
             currentNode = createCompositeNode(grammarAccess.getIntListRule(), currentNode); 
            pushFollow(FOLLOW_ruleIntList_in_entryRuleIntList3236);
            iv_ruleIntList=ruleIntList();
            _fsp--;

             current =iv_ruleIntList; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntList3246); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleIntList


    // $ANTLR start ruleIntList
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2051:1: ruleIntList returns [EObject current=null] : ( ( (lv_vals_0_0= rulePNInt ) ) ( ',' ( (lv_vals_2_0= rulePNInt ) ) )* ) ;
    public final EObject ruleIntList() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_vals_0_0 = null;

        AntlrDatatypeRuleToken lv_vals_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2056:6: ( ( ( (lv_vals_0_0= rulePNInt ) ) ( ',' ( (lv_vals_2_0= rulePNInt ) ) )* ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2057:1: ( ( (lv_vals_0_0= rulePNInt ) ) ( ',' ( (lv_vals_2_0= rulePNInt ) ) )* )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2057:1: ( ( (lv_vals_0_0= rulePNInt ) ) ( ',' ( (lv_vals_2_0= rulePNInt ) ) )* )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2057:2: ( (lv_vals_0_0= rulePNInt ) ) ( ',' ( (lv_vals_2_0= rulePNInt ) ) )*
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2057:2: ( (lv_vals_0_0= rulePNInt ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2058:1: (lv_vals_0_0= rulePNInt )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2058:1: (lv_vals_0_0= rulePNInt )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2059:3: lv_vals_0_0= rulePNInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getIntListAccess().getValsPNIntParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_rulePNInt_in_ruleIntList3292);
            lv_vals_0_0=rulePNInt();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getIntListRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		add(
            	       			current, 
            	       			"vals",
            	        		lv_vals_0_0, 
            	        		"PNInt", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2081:2: ( ',' ( (lv_vals_2_0= rulePNInt ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==27) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2081:4: ',' ( (lv_vals_2_0= rulePNInt ) )
            	    {
            	    match(input,27,FOLLOW_27_in_ruleIntList3303); 

            	            createLeafNode(grammarAccess.getIntListAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2085:1: ( (lv_vals_2_0= rulePNInt ) )
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2086:1: (lv_vals_2_0= rulePNInt )
            	    {
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2086:1: (lv_vals_2_0= rulePNInt )
            	    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2087:3: lv_vals_2_0= rulePNInt
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getIntListAccess().getValsPNIntParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePNInt_in_ruleIntList3324);
            	    lv_vals_2_0=rulePNInt();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getIntListRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"vals",
            	    	        		lv_vals_2_0, 
            	    	        		"PNInt", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleIntList


    // $ANTLR start entryRulePNInt
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2117:1: entryRulePNInt returns [String current=null] : iv_rulePNInt= rulePNInt EOF ;
    public final String entryRulePNInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePNInt = null;


        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2118:2: (iv_rulePNInt= rulePNInt EOF )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2119:2: iv_rulePNInt= rulePNInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPNIntRule(), currentNode); 
            pushFollow(FOLLOW_rulePNInt_in_entryRulePNInt3363);
            iv_rulePNInt=rulePNInt();
            _fsp--;

             current =iv_rulePNInt.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePNInt3374); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePNInt


    // $ANTLR start rulePNInt
    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2126:1: rulePNInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken rulePNInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2131:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2132:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2132:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2132:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2132:2: (kw= '-' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==28) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.unisb.prog.mips.asm/src-gen/de/unisb/prog/mips/asm/parser/antlr/internal/InternalMips.g:2133:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,28,FOLLOW_28_in_rulePNInt3413); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getPNIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rulePNInt3430); 

            		current.merge(this_INT_1);
                
             
                createLeafNode(grammarAccess.getPNIntAccess().getINTTerminalRuleCall_1(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePNInt


 

    public static final BitSet FOLLOW_ruleAsm_in_entryRuleAsm81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAsm91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataSegment_in_ruleAsm141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTextSegment_in_ruleAsm168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTextSegment_in_entryRuleTextSegment208 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTextSegment218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleTextSegment253 = new BitSet(new long[]{0x0000000000518012L});
    public static final BitSet FOLLOW_ruleTextItem_in_ruleTextSegment283 = new BitSet(new long[]{0x0000000000518012L});
    public static final BitSet FOLLOW_ruleTextItem_in_entryRuleTextItem320 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTextItem330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLabel_in_ruleTextItem376 = new BitSet(new long[]{0x0000000000518010L});
    public static final BitSet FOLLOW_ruleInstruction_in_ruleTextItem400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSpecialInsn_in_ruleTextItem419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlign_in_ruleTextItem438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWord_in_ruleTextItem457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInstruction_in_entryRuleInstruction496 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInstruction506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInstruction548 = new BitSet(new long[]{0x0000000000020030L});
    public static final BitSet FOLLOW_ruleRForm_in_ruleInstruction576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIArithForm_in_ruleInstruction595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIExpForm_in_ruleInstruction614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleILabelForm_in_ruleInstruction633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIBr2Form_in_ruleInstruction652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBExpForm_in_ruleInstruction671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBLabelForm_in_ruleInstruction690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRForm_in_entryRuleRForm729 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRForm739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReg_in_ruleRForm785 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleRForm806 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleRForm827 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleRForm844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIArithForm_in_entryRuleIArithForm886 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIArithForm896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReg_in_ruleIArithForm942 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleIArithForm963 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_rulePNInt_in_ruleIArithForm984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIExpForm_in_entryRuleIExpForm1020 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIExpForm1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReg_in_ruleIExpForm1076 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_rulePNInt_in_ruleIExpForm1097 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleIExpForm1107 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleIExpForm1128 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleIExpForm1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleILabelForm_in_entryRuleILabelForm1174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleILabelForm1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReg_in_ruleILabelForm1230 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleILabelForm1248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIBr2Form_in_entryRuleIBr2Form1284 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIBr2Form1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReg_in_ruleIBr2Form1340 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleIBr2Form1361 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIBr2Form1379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBExpForm_in_entryRuleBExpForm1415 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBExpForm1425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleBExpForm1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBLabelForm_in_entryRuleBLabelForm1506 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBLabelForm1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleBLabelForm1558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSpecialInsn_in_entryRuleSpecialInsn1593 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSpecialInsn1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLoadAddress_in_ruleSpecialInsn1650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLoadConstant_in_ruleSpecialInsn1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLoadConstant_in_entryRuleLoadConstant1707 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLoadConstant1717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleLoadConstant1752 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleLoadConstant1773 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleLoadConstant1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLoadAddress_in_entryRuleLoadAddress1831 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLoadAddress1841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleLoadAddress1876 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_ruleReg_in_ruleLoadAddress1897 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLoadAddress1915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleReg_in_entryRuleReg1951 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleReg1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleReg1996 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleReg2014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleReg2042 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataSegment_in_entryRuleDataSegment2084 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataSegment2094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleDataSegment2129 = new BitSet(new long[]{0x0000000007F00012L});
    public static final BitSet FOLLOW_ruleDataItem_in_ruleDataSegment2159 = new BitSet(new long[]{0x0000000007F00012L});
    public static final BitSet FOLLOW_ruleDataItem_in_entryRuleDataItem2196 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataItem2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLabel_in_ruleDataItem2252 = new BitSet(new long[]{0x0000000007F00000L});
    public static final BitSet FOLLOW_ruleDataDecl_in_ruleDataItem2274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataDecl_in_entryRuleDataDecl2310 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataDecl2320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStr_in_ruleDataDecl2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlign_in_ruleDataDecl2386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSpace_in_ruleDataDecl2405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleByte_in_ruleDataDecl2424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHalf_in_ruleDataDecl2443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWord_in_ruleDataDecl2462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLabel_in_entryRuleLabel2500 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLabel2510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleLabel2552 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleLabel2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAlign_in_entryRuleAlign2603 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAlign2613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleAlign2648 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAlign2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSpace_in_entryRuleSpace2706 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSpace2716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleSpace2751 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSpace2768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWord_in_entryRuleWord2809 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWord2819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleWord2854 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_ruleIntList_in_ruleWord2875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleHalf_in_entryRuleHalf2911 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleHalf2921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleHalf2956 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_ruleIntList_in_ruleHalf2977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleByte_in_entryRuleByte3013 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleByte3023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleByte3058 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_ruleIntList_in_ruleByte3079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStr_in_entryRuleStr3115 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStr3125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleStr3161 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_26_in_ruleStr3177 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStr3195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntList_in_entryRuleIntList3236 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntList3246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePNInt_in_ruleIntList3292 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleIntList3303 = new BitSet(new long[]{0x0000000010000020L});
    public static final BitSet FOLLOW_rulePNInt_in_ruleIntList3324 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_rulePNInt_in_entryRulePNInt3363 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePNInt3374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rulePNInt3413 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_rulePNInt3430 = new BitSet(new long[]{0x0000000000000002L});

}