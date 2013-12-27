
public class MessageState extends State{
	
	MessageState(String r) {
		super(r);
	}
    

	public void act() {
		speech.synthesizer(response);
	}
    
	
	public static class MessageState2 extends State{
    	Automaton auto;
    MessageState2(String r,Automaton _auto){
    	super(r);
    	auto=_auto;
    }
    public void act(){
    	speech.synthesizer(auto.word1+response);
    }
    }
}
