
public class DialogueManagerImpl implements DialogueManager  {
	SoundP p;
	StopWatch stop=new StopWatch(this);
	Automaton auto=new Automaton(this);
	int classid;
	logManagerImpl userlog = new logManagerImpl();
	 

	DialogueManagerImpl(SoundP _p){
		p = _p;
	}
	
	public void senddialog(int classid,String mozi) {
	userlog.writeuserlog(mozi);
	userlog.writesystemlog(auto.currentState);
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
	//auto.currentState.act();
	
	stop.settime();
	}
	}
	
	public void sendcamerainfomation(boolean cameraflag) {
		//カメラの情報をオートマトンに送る。
	if(auto.transit(cameraflag)){
		//stop.settime();
		auto.currentState.act();
        //stop.settime();
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
