import java.util.ArrayList;

public abstract class State{
	String response;
	SpeechSynthesizer speech=new SpeechSynthesizerImpl();
	static ArrayList<String> speecharray = new ArrayList<String>();
	logManagerImpl log = new logManagerImpl();
	
	State(String r){
		response = r;
	   }

	public abstract void act();
	public void savelog(State state){
		log.writesystemlog(state);
	}
}
