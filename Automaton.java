
public class Automaton {
 String word1,word2,user;
 State stWait = new NoActionState("");
 State stWait2 = new MessageState("こんにちは,乗り換え案内システムです");
 State stSearch = new MessageState("どこ駅までいきますか？");
 State stTime = new MessageState("聞こえますか？");//10秒たった時に遷移する。
 State stTimeover = new MessageState("初期状態に戻ります");//20秒たった時に遷移する。
 State stSearchconfirm = new MessageState.MessageState2("までですね？",this);
 State stSearchEngine = new SearchState("",this);
 State stSearchMessage0 = new SearchMessageState("1",0,this);
 State stSearchMessage1 = new SearchMessageState("2",1,this);
 State stSearchMessage2 = new SearchMessageState("3",2,this);
 State stSearchMessage3 = new SearchMessageState("4",3,this);
 State stSearchMessage4 = new SearchMessageState("5",4,this);
 State stSearchMessage5 = new SearchMessageState("6",5,this);
 State stSearchMessage6 = new SearchMessageState("7",6,this);
 State stSearchMessage7 = new SearchMessageState("8",7,this);
 State stSearchMessage8 = new SearchMessageState("9",8,this);
 State stSearchMessage9 = new SearchMessageState("10",9,this);
 State stSearchMessageLast = new LastState("以上の案内でよろしいですか？");
 State stVolumeUP = new VolumeState("音量をあげます",5);
 State stVolumeDown = new VolumeState("音量を下げます",-5);
 State stVolumeconfirm = new MessageState("これくらいで宜しいですか？");
 State currentState = stWait;
 State previousState,VolumeState;
 TransitionRule[] rules=new TransitionRule[68];
 boolean syntherule=false,syntheruletime=false;
 DialogueManager d;
 
 Automaton(DialogueManager _d){
	 d=_d;
	 rules[0] = new TransitionRule(stWait,stWait2);
	 rules[1] = new TransitionRuleclassid(stSearch,5,stSearchconfirm);
	 rules[2] = new TransitionRuleclassid(stSearch,100,stWait);
	 rules[3] = new TransitionRuleclassid(stSearch,11,stSearch);
	 rules[4] = new TransitionRuleclassid(stSearchEngine,11,stSearchEngine);
	 rules[5] = new TransitionRuleclassid(stVolumeconfirm,17,stVolumeUP);
	 rules[6] = new TransitionRuleclassid(stVolumeconfirm,17,stVolumeDown);
	 rules[7] = new TransitionRuletime(stSearchMessage8,3,stSearchMessage9);
	 rules[8] = new TransitionRuletime(stSearchMessage7,3,stSearchMessage8);
	 rules[9] = new TransitionRuletime(stSearchMessage6,3,stSearchMessage7);
	 rules[10] = new TransitionRuletime(stSearchMessage5,3,stSearchMessage6);
	 rules[11] = new TransitionRuletime(stSearchMessage4,3,stSearchMessage5);
	 rules[12] = new TransitionRuletime(stSearchMessage3,3,stSearchMessage4);
	 rules[13] = new TransitionRuletime(stSearchMessage2,3,stSearchMessage3);
	 rules[14] = new TransitionRuletime(stSearchMessage1,3,stSearchMessage2);
	 rules[15] = new TransitionRuletime(stSearchMessage0,3,stSearchMessage1);
	 rules[16] = new TransitionRuleclassid(stSearchMessage9,11,stSearchMessage9);
	 rules[17] = new TransitionRuleclassid(stSearchMessage8,11,stSearchMessage8);
	 rules[18] = new TransitionRuleclassid(stSearchMessage7,11,stSearchMessage7);
	 rules[19] = new TransitionRuleclassid(stSearchMessage6,11,stSearchMessage6);
	 rules[20] = new TransitionRuleclassid(stSearchMessage5,11,stSearchMessage5);
	 rules[21] = new TransitionRuleclassid(stSearchMessage4,11,stSearchMessage4);
	 rules[22] = new TransitionRuleclassid(stSearchMessage3,11,stSearchMessage3);
	 rules[23] = new TransitionRuleclassid(stSearchMessage2,11,stSearchMessage2);
	 rules[24] = new TransitionRuleclassid(stSearchMessage1,11,stSearchMessage1);
	 rules[25] = new TransitionRuleclassid(stSearchMessage0,11,stSearchMessage0);
	 rules[26] = new TransitionRuletime(stSearch,10,stTime);
	 rules[27] = new TransitionRuletime(stSearchMessage0,10,stTime);
	 rules[28] = new TransitionRuletime(stSearchMessage1,10,stTime);
	 rules[29] = new TransitionRuletime(stSearchMessage2,10,stTime);
	 rules[30] = new TransitionRuletime(stSearchMessage3,10,stTime);
	 rules[31] = new TransitionRuletime(stSearchMessage4,10,stTime);
	 rules[32] = new TransitionRuletime(stSearchMessage5,10,stTime);
	 rules[33] = new TransitionRuletime(stSearchMessage6,10,stTime);
	 rules[34] = new TransitionRuletime(stSearchMessage7,10,stTime);
	 rules[35] = new TransitionRuletime(stSearchMessage8,10,stTime);
	 rules[36] = new TransitionRuletime(stSearchMessage9,10,stTime);
	 rules[37] = new TransitionRuletime(stSearch,20,stWait);
	 rules[38] = new TransitionRuletime(stSearchMessage0,20,stWait);
	 rules[39] = new TransitionRuletime(stSearchMessage1,20,stWait);
	 rules[40] = new TransitionRuletime(stSearchMessage2,20,stWait);
	 rules[41] = new TransitionRuletime(stSearchMessage3,20,stWait);
	 rules[42] = new TransitionRuletime(stSearchMessage4,20,stWait);
	 rules[43] = new TransitionRuletime(stSearchMessage5,20,stWait);
	 rules[44] = new TransitionRuletime(stSearchMessage6,20,stWait);
	 rules[45] = new TransitionRuletime(stSearchMessage7,20,stWait);
	 rules[46] = new TransitionRuletime(stSearchMessage8,20,stWait);
	 rules[47] = new TransitionRuletime(stSearchMessage9,20,stWait);
	 rules[48] = new TransitionRuleclassid(stSearchMessage8,15,stSearchMessage9);
	 rules[49] = new TransitionRuleclassid(stSearchMessage7,15,stSearchMessage8);
	 rules[50] = new TransitionRuleclassid(stSearchMessage6,15,stSearchMessage7);
	 rules[51] = new TransitionRuleclassid(stSearchMessage5,15,stSearchMessage6);
	 rules[52] = new TransitionRuleclassid(stSearchMessage4,15,stSearchMessage5);
	 rules[53] = new TransitionRuleclassid(stSearchMessage3,15,stSearchMessage4);
	 rules[54] = new TransitionRuleclassid(stSearchMessage2,15,stSearchMessage3);
	 rules[55] = new TransitionRuleclassid(stSearchMessage1,15,stSearchMessage2);
	 rules[56] = new TransitionRuleclassid(stSearchMessage0,15,stSearchMessage1);
	 rules[57] = new TransitionRuleclassid(stSearchMessage9,10,stSearchMessage8);
	 rules[58] = new TransitionRuleclassid(stSearchMessage8,10,stSearchMessage7);
	 rules[59] = new TransitionRuleclassid(stSearchMessage7,10,stSearchMessage6);
	 rules[60] = new TransitionRuleclassid(stSearchMessage6,10,stSearchMessage5);
	 rules[61] = new TransitionRuleclassid(stSearchMessage5,10,stSearchMessage4);
	 rules[62] = new TransitionRuleclassid(stSearchMessage4,10,stSearchMessage3);
	 rules[63] = new TransitionRuleclassid(stSearchMessage3,10,stSearchMessage2);
	 rules[64] = new TransitionRuleclassid(stSearchMessage2,10,stSearchMessage1);
	 rules[65] = new TransitionRuleclassid(stSearchMessage1,10,stSearchMessage0);
	 rules[66] = new TransitionRuleclassid(stSearchconfirm,15,stSearchEngine);
	 rules[67] = new TransitionRuleclassid(stSearchconfirm,11,stSearchconfirm);
	 
 }

void transit(int classid){//ユーザーの発話内容の遷移ルール
	    if(classid==12&&currentState!=stWait&&currentState!=stWait2){//うるさいや静かにしてなど音量を下げて欲しい場合
	    	previousState = currentState;
	    	VolumeState=stVolumeDown;
	    	this.Transitionchange(stVolumeDown, stVolumeconfirm);
	    	classid=50;
	    }
	    if(classid==13&&currentState!=stWait&&currentState!=stWait2){//聞こえないなど音量をあげて欲しい場合
	    	previousState = currentState;
	    	VolumeState=stVolumeUP;
	    	this.Transitionchange(stVolumeUP, stVolumeconfirm);
	    	classid=50;
	    }
	    if(classid==15&&currentState==stVolumeconfirm){//ボリューム確認状態の時、ユーザーが納得した時。
	    	currentState=previousState;
	    	currentState.act();
	    	classid=50;
	    }
	    if(classid==16&&currentState==stVolumeconfirm){
	    	this.Transitionchange(VolumeState,stVolumeconfirm);
	    	classid=50;
	    }
	    
		for(TransitionRule r:rules){
		if(currentState==r.currentState&&classid == r.classid){
			currentState=r.nextState;
			syntherule=true;//遷移した時のみシステムの発話を行う。
		  }

		}
}

   boolean transittime(int time){
	    for(TransitionRule r:rules){
		if(currentState==r.currentState&&time == r.time){
			   if(time==3){
				   currentState=r.nextState;
				   currentState.act();
				  return true;
			  }
			     if(time==10){
			      this.Transitionchange(stTime,currentState);
			      return true;
			     }
			     if(time==20){
			       currentState=r.nextState;
			      currentState.act();
			      return true;
			     }
		}
		
	    }
	    return false;
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

   void Transitionchange(State actionState,State nextState){//状態のアクションを行い、別の状態に遷移する時に使用
	   State changeState;
	   changeState=nextState;
	   currentState=actionState;
	   d.action();
	   currentState=changeState;
	   d.action();
   }
   

void Setword(String _word1,String _word2){
	 word1=_word1;
	 word2=_word2;
 }
void changestWait(){
	currentState=stWait;
	   d.action();
}
}