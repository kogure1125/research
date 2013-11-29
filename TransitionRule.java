
public class TransitionRule{
        State currentState;
        State nextState;
        String user;
        int classid;

TransitionRule(State _currentState,State _nextState){
    currentState=_currentState;
    nextState=_nextState;
}

TransitionRule(State _currentState,int _classid,State _nextState){
	this(_currentState,_nextState);
	classid=_classid;
}

TransitionRule(State _currentState,int classid,State _nextState,String _user){
	this(_currentState,classid,_nextState);
	user=_user;
}
}


	    


