
public class TransitionRuletime extends TransitionRule {

	TransitionRuletime(State _currentState, int _time, State _nextState) {
		super(_currentState,_nextState);
		time=_time;
	}

}
