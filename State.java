<<<<<<< HEAD
public abstract class State{
	String response;
	SpeechSynthesizer speech =new SpeechSynthesizerImpl();
	
	State(String r){
		response = r;
	   }

	public abstract void act();
=======
public class State{
	String response,motion;
	SearchEngine s;
	State(String r,String m){
		response = r;
		motion = m;
	}
>>>>>>> 474e1b8472b36209e2a4fda1635762cca2964f1a
}
