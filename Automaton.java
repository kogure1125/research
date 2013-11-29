
public class Automaton {
 String word1,word2,user;
 State stWait = new NoActionState("初期状態に戻ります");
 State stSearch = new MessageState("子供ですか？大人ですか？");
 State stSearchchild = new MessageState("何駅から何駅ですか？");
 State stSearchadult = new MessageState("何駅から何駅ですか？");
 State stTime = new MessageState("貴方様どうしましたか");//10秒たった時に遷移する。
 State stTimeover = new MessageState("初期状態に戻ります");//20秒たった時に遷移する。
 State stSearchEngine = new SearchState("",this);
 State stVolume = new VolumeState("テスト安定");
 State currentState = stWait;
 TransitionRule[] rules=new TransitionRule[12];
 boolean syntherule=false;

 Automaton(){
	 rules[0] = new TransitionRule(stWait,stSearch);
	 rules[1] = new TransitionRule(stSearch,1,stSearchchild,"子供");
	 rules[2] = new TransitionRule(stSearch,2,stSearchadult,"大人");
	 rules[3] = new TransitionRule(stSearchchild,5,stSearchEngine);
	 rules[4] = new TransitionRule(stSearchchild,10,stSearch);
	 rules[5] = new TransitionRule(stSearchadult,5,stSearchEngine);
	 rules[6] = new TransitionRule(stSearchadult,10,stSearch);
	 rules[7] = new TransitionRule(stSearchchild,0,stWait);
	 rules[8] = new TransitionRule(stSearch,11,stSearch);
	 rules[9] = new TransitionRule(stSearchchild,11,stSearchchild);
	 rules[10] = new TransitionRule(stSearchadult,11,stSearchadult);
	 rules[11] = new TransitionRule(stSearchEngine,11,stSearchEngine);
	 
 }
 void transit(int classid){//ユーザーの発話内容の遷移ルール
	    if(classid==12&&currentState!=stWait){
	    	this.Transitionchange(stVolume, currentState);
	    }
		for(TransitionRule r:rules){
		if(currentState==r.currentState&&classid == r.classid){
			currentState=r.nextState;
			syntherule=true;//遷移した時のみシステムの発話を行う。
			if(r.user=="子供"||r.user=="大人"){
				user=r.user;
			}
		  }
		}
    }
   boolean transit(boolean cameraflag){ //カメラ情報の遷移ルール
        for(TransitionRule r:rules){
        if(cameraflag&&currentState==stWait){//カメラflagがtrueで現在の状態がWaitの時のみtrueを返す。
        	currentState=r.nextState;
        	return true;
        }
        }
        return false;
 }
    void transittime(int time){           //時間の遷移ルール
	   if(time==10){ //10秒たった時かつ状態がWait以外の時、Time状態のアクションを行い、現在の状態に戻る
       this.Transitionchange(stTime,currentState);
	   }
	   else if(time==20){//20秒たった時、Timeover状態のアクションを行い、Wait状態に戻る。
	   this.Transitionchange(stTimeover,stWait);
	   }
   }
   
 
   void Transitionchange(State actionState,State nextState){//状態のアクションを行い、別の状態に遷移する時に使用
	   State changeState;
	   changeState=nextState;
	   currentState=actionState;
	   currentState.act();
	   currentState=changeState;
	   currentState.act();
   }
   

void Setword(String _word1,String _word2){
	 word1=_word1;
	 word2=_word2;
 }
}