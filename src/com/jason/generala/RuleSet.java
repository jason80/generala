package com.jason.generala;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jason.generala.xml.XML;

public class RuleSet {
	
	private static final String RULE_SET_FILE = "rules.xml";
	
	public enum GeneralaServida {
		SCORE, WINS_GAME
	}
	
	private boolean useAsEscalera = true;
	private GeneralaServida generalaServida = GeneralaServida.WINS_GAME;
	
	public void setUseAsEscalera(boolean useAsEscalera) {
		this.useAsEscalera = useAsEscalera;
	}
	
	public boolean isUseAsEscalera() {
		return useAsEscalera;
	}
	
	public GeneralaServida getGeneralaServida() {
		return generalaServida;
	}
	
	public void setGeneralaServida(GeneralaServida generalaServida) {
		this.generalaServida = generalaServida;
	}
	
	public void save() {
		Document doc = XML.newDocument();
		
		Element root = doc.createElement("rule-set");
		doc.appendChild(root);
		
		Element rules = doc.createElement("rules");
		root.appendChild(rules);
		
		rules.setAttribute("use-as", 
				useAsEscalera ? "true" : "false");
		rules.setAttribute("gen-servida", 
				generalaServida == GeneralaServida.WINS_GAME ?
						"wins" : "score");
		
		XML.saveDocument(doc, RULE_SET_FILE);
	}
	
	public void load() {
		Document doc = XML.loadDocument(RULE_SET_FILE);
		if (doc == null) return ;
		
		Element root = doc.getDocumentElement();
		
		if (!"rule-set".equals(root.getNodeName())) return ;
		
		NodeList childs = root.getChildNodes();
		
		for (int i = 0; i < childs.getLength(); i ++) {
			if (childs.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element child = (Element) childs.item(i);
				
				if ("rules".equals(child.getNodeName())) {
					useAsEscalera = "true".equals(child.getAttribute("use-as"));
					generalaServida = 
							"wins".equals(child.getAttribute("gen-servida")) ?
									GeneralaServida.WINS_GAME : GeneralaServida.SCORE;
				}
			}
		}
	}
	
}
