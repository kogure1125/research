
public class NoActionState extends State{
	NoActionState(String r){
		super(r);
	}

	public void act() {
		speecharray.clear();
		speech.synthesizer(response);
		
	}
	
	

}
