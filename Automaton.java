
public class Automaton {
 State stWait = new State("検索したいと言ってください。");
 State stSearch = new State("乗車券ですか？");
 State stSearchjosha = new State("大人ですか？子供ですか？");
 State stSearchjoshachild = new State("何駅から何駅ですか？");
 State stSearchjoshaadult = new State("何駅から何駅ですか？");
 State currentState = stWait;
 TransitionRule[] rules=new TransitionRule[11];


 Automaton(){
	 rules[0] = new TransitionRule(stWait,0,stSearch);
	 rules[1] = new TransitionRule(stSearch,3,stSearchjosha);
	 rules[2] = new TransitionRule(stSearchjosha,5,stSearchjoshachild);
	 rules[3] = new TransitionRule(stSearchjosha,6,stSearchjoshaadult);
	 rules[4] = new TransitionRule(stSearchjosha,17,stSearch);
	 rules[5] = new TransitionRule(stSearchjoshachild,9,stSearchjoshachild);
	 rules[6] = new TransitionRule(stSearchjoshachild,2,stWait);
	 rules[7] = new TransitionRule(stSearchjoshachild,17,stSearchjosha);
	 rules[8] = new TransitionRule(stSearchjoshaadult,9,stSearchjoshaadult);
	 rules[9] = new TransitionRule(stSearchjoshaadult,2,stWait);
	 rules[10] = new TransitionRule(stSearchjoshaadult,17,stSearchjosha);
 }
 void transit(int classid){
		for(TransitionRule r:rules){
		if(currentState==r.currentState&&classid == r.classid){
			currentState=r.nextState;
		  }
		}
    }
}