
public class MessageState2 extends State{

	Automaton auto;
	MessageState2(String r,Automaton _auto){
		super(r);
		auto=_auto;
	}

	public void act() {
		speech.synthesizer(auto.word1+response);
		
	}
}
