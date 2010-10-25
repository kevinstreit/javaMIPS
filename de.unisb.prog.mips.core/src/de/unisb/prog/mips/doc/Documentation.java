package de.unisb.prog.mips.doc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
	private static HashMap<String, InsnDoc> mnemonicDocMap = null;

	private static void readDocs() {
		mnemonicDocMap = new HashMap<String, InsnDoc>();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream in = Documentation.class.getResourceAsStream("/doc/insnDocs.xml");

			if (in == null) {
				System.err.println("Could not read instruction doc description!");
				insnDoc = null;
				mnemonicDocMap = null;
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
				    			Node iPseudo   = insn.getAttributes().getNamedItem("purpose");
				    			Node iShort    = insn.getAttributes().getNamedItem("short");

				    			String mnemonic = iMnemonic==null ? "<empty>" : iMnemonic.getNodeValue();
				    			String pseudo   = iPseudo==null ? "<empty>" : iPseudo.getNodeValue();
				    			String shrt     = iShort==null ? "<empty>" : iShort.getNodeValue();
				    			String desc     = insn.getTextContent().trim();

				    			InsnDoc doc = new InsnDoc(gInsnDocGroup, mnemonic, pseudo, shrt, desc);
				    			mnemonicDocMap.put(mnemonic, doc);
				    			gInsnDocGroup.addInsn(doc);
				    		}
				    	}
				    }
			    }
			}

			insnDoc = insnDocGroups.toArray(new InsnDocGroup[insnDocGroups.size()]);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			insnDoc = null;
			mnemonicDocMap = null;
		} catch (SAXException e) {
			e.printStackTrace();
			insnDoc = null;
			mnemonicDocMap = null;
		} catch (IOException e) {
			e.printStackTrace();
			insnDoc = null;
			mnemonicDocMap = null;
		}
	}

	public static InsnDocGroup[] getInsnDocumentation() {
		if (insnDoc == null)
			readDocs();

		return insnDoc;
	}

	public static InsnDoc getInsnDoc(String mnemonic) {
		if (mnemonicDocMap == null)
			readDocs();

		return mnemonicDocMap.get(mnemonic);
	}
}
