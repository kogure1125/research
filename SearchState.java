
public class SearchState extends State {
	SearchEngine s = new SearchEngine();
	Automaton auto;
	String searchword;
	SearchState(String r,Automaton _auto) {
		super(r);
		auto=_auto;
	}
	
	public void act(){
		if(auto.user=="子供"){
		this.actchild(auto.word1,auto.word2);
		}
		else{
		this.actadult(auto.word1,auto.word2);
		}
	}

	public void actchild(String word1, String word2){
		searchword=s.search(word1,word2,"子供");
        response=word1+"から"+word2+"までの"+"子供"+"料金は"+searchword+"円です。何駅から何駅ですか？";
        System.out.println(response);
        speech.synthesizer(response);
        auto.currentState=auto.stSearchchild;
	}
	
	public void actadult(String word1,String word2){
		searchword=s.search(word1,word2,"大人");
        response=word1+"から"+word2+"までの"+"大人"+"料金は"+searchword+"円です。何駅から何駅ですか？";
        System.out.println(response);
        speech.synthesizer(response);
        auto.currentState=auto.stSearchadult;
	}

}
