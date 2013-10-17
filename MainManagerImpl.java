import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MainManagerImpl implements MainManager {
	SpeechRecognizer sprec;
	String motion;
	DialogueManager dialog = new DialogueManagerImpl();
	HelloAnimation hello = new HelloAnimation();
	
	MainManagerImpl() {
		try {
			sprec = new SpeechRecognizerImpl(this);
		} catch (ParserConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	public void start() {
		sprec.start();
		hello.start();
	}

	public void speechDetected(Document doc) {
		
		
		if (doc.getDocumentElement().getTagName().equals("RECOGOUT")) {
			RecogOut recogout = new RecogOut(doc);
          System.out.println(recogout.shypo[0].whypo.length);
          dialog.dialogSend(recogout.shypo[0].whypo[1].classid,hello);
          for (int i = 0; i < recogout.shypo[0].whypo.length; i++) {
        	  System.out.println(recogout.shypo[0].whypo[i].word);
              //this.dialogRule(recogout.shypo[0].whypo[i].word,recogout.shypo[0].whypo[1].classid);
             // this.count(recogout.shypo[0].whypo[i].word,i);
           
          }
		} else if (doc.getDocumentElement().getTagName().equals("STARTRECOG")) {
			System.err.println("認識開始");
		}
	}
	public String[] count(String id,int _i){
	    String[] array=new String[2];
		if(_i==1){
         System.out.println(id+_i);
         array[0]=id;
		}
		if(_i==3){
		 System.out.println(id+_i);
		 array[1]=id;
		}
		return array;
		}
	
    //動作を定義するメソッド
	
	
	public void speechDistinguish (String word){
		if(word.equals("歩いてください") || word.equals("歩け")){
			motion="work";
		}
		if(word.equals("しなれ") || word.equals("しなってください")){
            motion="しなる";
		}
		if(word.equals("押せ")||word.equals("押してください")){
			System.out.println("osetest");
		    motion="push";
		}
	}
}

    