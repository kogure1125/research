
public class MessageState extends State{

	
	MessageState(String r) {
		super(r);
	}
    
	MessageState(String r,Automaton auto){
		super(r);
	}
	public void act() {
		System.out.println(this);
		speech.synthesizer(response);
	}
    
}
