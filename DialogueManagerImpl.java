
public class DialogueManagerImpl implements DialogueManager  {
	SoundP p;
	StopWatch stop=new StopWatch(this);
	Automaton auto=new Automaton(this);
	int classid;
	LogManagerImpl userlog = new LogManagerImpl();
	 

	DialogueManagerImpl(SoundP _p){
		p = _p;
	}
	
	public void senddialog(int classid,String mozi) {
	userlog.writeuserlog(mozi);
	auto.transit(classid);
	if(auto.syntherule==true){
	
	auto.currentState.act();
	stop.settime();
	}
	auto.syntherule=false;
	classid=100;
	}
	
	public void sendtime(int time){
	if(auto.transittime(time)){
		stop.settime();
	}
	}
	
	public void sendcamerainfomation(boolean cameraflag) {
		//カメラの情報をオートマトンに送る。
	if(auto.transit(cameraflag)){
		auto.currentState.act();
		auto.currentState=auto.stSearch;
		auto.currentState.act();
	}
	}
	public void sendcamerainfomation2(boolean cameraflag){
	if(auto.transit(cameraflag)){
	auto.currentState.act();
	auto.currentState=auto.stSearch;
	auto.currentState.act();
	}
	}
	
	   public void action(){
		   auto.currentState.act();
	   }
	
	
	
	public void Setword(String _word1,String _word2){
		auto.Setword(_word1, _word2);
	}
}
