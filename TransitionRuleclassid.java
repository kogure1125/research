
public class TransitionRuleclassid extends TransitionRule{

	TransitionRuleclassid(State _currentState, int _classid, State _nextState) {
		super(_currentState,_nextState);
		classid=_classid;
	}


}
