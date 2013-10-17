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
	String motion,msg;
	DialogueManager dialog = new DialogueManagerImpl();
	HelloAnimation hello = new HelloAnimation();
	SoundP sound = new SoundP();
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
		sound.start();
		hello.start();
	}

	public void speechDetected(Document doc) {
		if(sound.get()){
		if (doc.getDocumentElement().getTagName().equals("RECOGOUT")) {
			RecogOut recogout = new RecogOut(doc);
          System.out.println(recogout.shypo[0].whypo.length);
          dialog.senddialog(recogout.shypo[0].whypo[1].classid,hello);
          for (int i = 0; i < recogout.shypo[0].whypo.length; i++) {
        	  System.out.println(recogout.shypo[0].whypo[i].word);
              //this.dialogRule(recogout.shypo[0].whypo[i].word,recogout.shypo[0].whypo[1].classid);
             // this.count(recogout.shypo[0].whypo[i].word,i);
           
          }
		} else if (doc.getDocumentElement().getTagName().equals("STARTRECOG")) {
			System.err.println("認識開始");
		}
		}
		else{
			System.out.println("認識失敗");
		}
	}
	public void Action(){
		//msg=dialog.getmsg();
		//motion=dialog.getmotion();
		//hello.setValue(motion);
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
}

    