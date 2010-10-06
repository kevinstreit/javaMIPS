package de.unisb.prog.mips.doc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Documentation {
	private static InsnDocGroup[] insnDoc = null;
	public static InsnDocGroup[] getInsnDocumentation() {
		if (insnDoc != null)
			return insnDoc;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream in = Documentation.class.getResourceAsStream("/doc/insnDocs.xml");
			
			if (in == null) {
				System.err.println("Could not read instruction doc description!");
				return null;
			}
			
			Document document = db.parse(in);
			document.getDocumentElement().normalize();
			
			Vector<InsnDocGroup> insnDocGroups = new Vector<InsnDocGroup>();
			
			Node docRoot = document.getFirstChild();
			docRoot.normalize();
			NodeList groups = docRoot.getChildNodes();
			
			for (int g = 0; g < groups.getLength(); ++g) {
			    Node group = groups.item(g);
			    
			    if (group.hasAttributes()) {
				    Node gName = group.getAttributes().getNamedItem("name");
				    if (gName != null) {
				    	InsnDocGroup gInsnDocGroup = new InsnDocGroup(gName.getNodeValue());
				    	insnDocGroups.add(gInsnDocGroup);
				    	
				    	NodeList insnsInGroup = group.getChildNodes();
				    	for (int i = 0; i < insnsInGroup.getLength(); ++i) {
				    		Node insn = insnsInGroup.item(i);
				    		
				    		if (insn.hasAttributes()) {
				    			Node iMnemonic = insn.getAttributes().getNamedItem("mnemonic");
				    			Node iName = insn.getAttributes().getNamedItem("name");
				    			Node iExample = insn.getAttributes().getNamedItem("example");
				    			
				    			String mnemonic = iMnemonic==null ? "<empty>" : iMnemonic.getNodeValue();
				    			String name = iName==null ? "<empty>" : iName.getNodeValue();
				    			String example = iExample==null ? "<empty>" : iExample.getNodeValue();
				    			String desc = insn.getTextContent().trim();
				    			
				    			gInsnDocGroup.addInsn(new InsnDoc(gInsnDocGroup, mnemonic, name, example, desc));
				    		}
				    	}
				    }
			    }
			}
			
			insnDoc = insnDocGroups.toArray(new InsnDocGroup[insnDocGroups.size()]);
			return insnDoc;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
