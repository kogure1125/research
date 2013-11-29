
public class MessageState extends State{

	
	MessageState(String r) {
		super(r);
	}

	public void act() {
		speech.synthesizer(response);
	}

}
