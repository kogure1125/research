
public class Automaton {
 State stWait = new State("検索したいと言ってください。","しなる");
 State stSearch = new State("乗車券ですか？定期券ですか？","push");
 State stSearchjosha = new State("大人ですか？子供ですか？","しなる");
 State stSearchjoshachild = new State("何駅から何駅ですか？","stop");
 State stSearchjoshaadult = new State("何駅から何駅ですか？","stop");
 State stSearchteiki = new State("学生ですか？","しなる");
 State stSearchteikikukan = new State("区間を指定してください？","push");
 State currentState = stWait;
 TransitionRule[] rules=new TransitionRule[17];
 boolean syntherule=false;

 Automaton(){
	 rules[0] = new TransitionRule(stWait,0,stSearch);
	 rules[1] = new TransitionRule(stSearch,3,stSearchjosha);
	 rules[2] = new TransitionRule(stSearch,4,stSearchteiki);
	 rules[3] = new TransitionRule(stSearchjosha,5,stSearchjoshachild);
	 rules[4] = new TransitionRule(stSearchjosha,6,stSearchjoshaadult);
	 rules[5] = new TransitionRule(stSearchjosha,17,stSearch);
	 rules[6] = new TransitionRule(stSearchjoshachild,9,stSearchjoshachild);
	 rules[7] = new TransitionRule(stSearchjoshachild,2,stWait);
	 rules[8] = new TransitionRule(stSearchjoshachild,17,stSearchjosha);
	 rules[9] = new TransitionRule(stSearchjoshaadult,9,stSearchjoshaadult);
	 rules[10] = new TransitionRule(stSearchjoshaadult,2,stWait);
	 rules[11] = new TransitionRule(stSearchjoshaadult,17,stSearchjosha);
	 rules[12] = new TransitionRule(stSearchteiki,14,stSearchteikikukan);
	 rules[13] = new TransitionRule(stSearchteiki,17,stSearch);
	 rules[14] = new TransitionRule(stSearchteikikukan,9,stSearchteikikukan);
	 rules[15] = new TransitionRule(stSearchteikikukan,17,stSearchteiki);
	 rules[16] = new TransitionRule(stSearchteikikukan,2,stWait);
 }
 void transit(int classid){
		for(TransitionRule r:rules){
		if(currentState==r.currentState&&classid == r.classid){
			currentState=r.nextState;
			syntherule=true;
		  }
		}
    }
}