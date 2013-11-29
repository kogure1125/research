import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MainManagerImpl extends Thread implements MainManager {
	SpeechRecognizer sprec;
	String motion,msg;
	//HelloAnimation hello = new HelloAnimation();
	SoundP sound = new SoundP(this);
	SpeechSynthesizer s= new SpeechSynthesizerImpl();
	DialogueManager dialog = new DialogueManagerImpl(s,sound);
	boolean dialogflag=true;
	Cameratest aa= new Cameratest(this,sound,s);
	
	
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
		
		//hello.start();
	}

	public void speechDetected(Document doc) {
		//if(sound.get()){
		if (doc.getDocumentElement().getTagName().equals("RECOGOUT")) {
			RecogOut recogout = new RecogOut(doc);
          System.out.println(recogout.shypo[0].whypo.length);
          if(recogout.shypo[0].whypo.length>3){
              dialog.Setword(recogout.shypo[0].whypo[1].word,recogout.shypo[0].whypo[3].word);
          }
          
          for (int i = 0; i < recogout.shypo[0].whypo.length; i++) {
        	  System.out.println(recogout.shypo[0].whypo[i].word);
              //this.dialogRule(recogout.shypo[0].whypo[i].word,recogout.shypo[0].whypo[1].classid);
             // this.count(recogout.shypo[0].whypo[i].word,i);
           
          }
          dialog.senddialog(recogout.shypo[0].whypo[1].classid,recogout.shypo[0].whypo[1].word);
		} else if (doc.getDocumentElement().getTagName().equals("STARTRECOG")) {
			System.err.println("認識開始");
		}
		else{
			System.out.println("認識失敗");
		}
		/*}
		else{
			System.out.println("認証失敗");
		}*/
	}
	public void camerarelay(boolean _cameraflag){
		dialog.sendcamerainfomation(_cameraflag);
	}
	}

    