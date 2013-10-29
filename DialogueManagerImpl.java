
public class DialogueManagerImpl implements DialogueManager  {
	Automaton auto=new Automaton();
	SpeechSynthesizer speech = new SpeechSynthesizerImpl();
	
	public void senddialog(int classid,String mozi) {
	auto.transit(classid);
	String msg = auto.currentState.response;
	String motion = auto.currentState.motion;
	System.out.println(msg);
    
	if(auto.syntherule==true){
	speech.synthesizer(msg);
	}
	auto.syntherule=false;
	}

	/*public String getmsg() {
		return msg;
	}

	@Override
	public String getmotion() {
		return motion;
	}*/
	}

