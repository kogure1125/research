import java.util.ArrayList;

public abstract class State{
	String response;
	SpeechSynthesizer speech=new SpeechSynthesizerImpl();
	static ArrayList<String> speecharray = new ArrayList<String>();
	
	State(String r){
		response = r;
	   }

	public abstract void act();
}
