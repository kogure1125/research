import java.util.ArrayList;

import org.w3c.dom.*;
public class RecogOut {
  Document doc;
  SHypo[] shypo;
  RecogOut(Document doc) {
	  this.doc = doc;
	  NodeList nl = doc.getElementsByTagName("SHYPO");
	  shypo = new SHypo[nl.getLength()];
	  for (int i = 0; i < shypo.length; i++) {
		  shypo[i] = new SHypo(nl.item(i));
	  }
  }
  
  class SHypo {
	  WHypo[] whypo = new WHypo[0];
	  int rank;
	  double score;
	  int gram;
	  SHypo(Node node) {
		  NodeList nl = node.getChildNodes();
//		  whypo = new WHypo[nl.getLength()];
		  ArrayList<WHypo> whypo_list = new ArrayList<WHypo>();
		  for (int i = 0; i < nl.getLength(); i++) {
			  if (nl.item(i).getNodeName().equals("WHYPO")) {
				  whypo_list.add(new WHypo(nl.item(i)));
			  }
//			  whypo[i] = new WHypo(nl.item(i));
		  }
		  whypo = (WHypo[]) whypo_list.toArray(whypo);
		  rank = Integer.parseInt(node.getAttributes().getNamedItem("RANK").getNodeValue());
		  score = Double.parseDouble(node.getAttributes().getNamedItem("SCORE").getNodeValue());
		  gram = Integer.parseInt(node.getAttributes().getNamedItem("GRAM").getNodeValue());
	  }
	  
	  class WHypo {
		  String word;
		  int classid;
		  String phone;
		  double cm;
		  WHypo(Node node) {
			  System.err.println(node);
			  word = node.getAttributes().getNamedItem("WORD").getNodeValue();
			  classid = Integer.parseInt(node.getAttributes().getNamedItem("CLASSID").getNodeValue());
			  phone = node.getAttributes().getNamedItem("PHONE").getNodeValue();
			  cm = Double.parseDouble(node.getAttributes().getNamedItem("CM").getNodeValue());
		  }
	  }
	  
  }
}
