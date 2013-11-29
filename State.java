public abstract class State{
	String response;
	SpeechSynthesizer speech =new SpeechSynthesizerImpl();
	
	State(String r){
		response = r;
	   }

	public abstract void act();
}
