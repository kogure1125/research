import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class SpeechRecognizerImpl extends Thread implements SpeechRecognizer {
    String line;
    MainManager main;
    DocumentBuilder dBuilder;
    
    SpeechRecognizerImpl(MainManager main) throws ParserConfigurationException {
    	this.main = main;
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	dBuilder = dbFactory.newDocumentBuilder();
    }
	public void run(){
		try{
		      Socket socket=new Socket("localhost",10500);
		      InputStream is = socket.getInputStream();
		      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	//	      SpeechSynthesizer s = new SpeechSynthesizerImpl();
		      while((this.line=reader.readLine())!=null){
	//	    	  line=reader.readLine();
		    	  addline(line);
//		          s.Synthese(line,t);
		      } 
		      socket.close();
		}catch(Exception e){
		         e.printStackTrace();
		}
	}

	String data = "";
	
	void addline(String ret) throws ParserConfigurationException, SAXException, IOException {
		if (ret.equals(".")) {
			data = data.replaceAll("\"<", "\"&lt;");
			data = data.replaceAll(">\"", "&gt;\"");
			BufferedInputStream is = new BufferedInputStream(new ByteArrayInputStream(data.getBytes()));
			Document doc = dBuilder.parse(is);
	        System.err.println(doc);
	        main.speechDetected(doc);
	        data = "";
		} else {
			data += ret;
		}
	}
	
	}

