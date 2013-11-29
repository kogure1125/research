
public class DialogueManagerImpl implements DialogueManager  {
<<<<<<< HEAD
	SoundP p;
	Automaton auto=new Automaton();
	SpeechSynthesizer speech;
	int classid;
	 StopWatch stop=new StopWatch(this);
	
	
	DialogueManagerImpl(SpeechSynthesizer _speech,SoundP _p){
		speech =_speech;
		p = _p;
	}
	
	public void senddialog(int classid,String mozi) {
	stop.settime();
	auto.transit(classid);
	if(auto.syntherule==true){
	auto.currentState.act();
	stop.settime();
	}
	auto.syntherule=false;
=======
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
>>>>>>> 474e1b8472b36209e2a4fda1635762cca2964f1a
	}
	
	public void sendcamerainfomation(boolean cameraflag) {//カメラの情報をオートマトンに送る。
	if(auto.transit(cameraflag)){
		speech.synthesizer("認識を開始します。");
		stop.settime();
		auto.currentState.act();
        stop.settime();
	}
	}
	
	public void sendtime(int time){//時間の情報をオートマトンに送る。
		if(auto.currentState==auto.stWait){
		stop.settime();
		}
		System.out.println(time);
		auto.transittime(time);
	}
	
	   void Transitionchange(State actionState,State nextState){//状態のアクションを行い、別の状態に遷移する時に使用
		   State changeState;
		   changeState=nextState;
		   auto.currentState=actionState;
		   auto.currentState.act();
		   auto.currentState=changeState;
		   auto.currentState.act();
	   }
	
	
	
	public void Setword(String _word1,String _word2){
		auto.Setword(_word1, _word2);
	}
}
