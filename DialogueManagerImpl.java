
public class DialogueManagerImpl implements DialogueManager  {
	Automaton auto=new Automaton();;

	public void dialogSend(int classid) {
	auto.transit(classid);
	String msg = auto.currentState.response;
	System.out.println(msg);
	}
	}

