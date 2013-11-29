
public class DialogueManagerImpl implements DialogueManager  {
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
