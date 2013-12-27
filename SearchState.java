import java.util.ArrayList;

public class SearchState extends State {
	SearchEngine s = new SearchEngineImpl();
	Automaton auto;
	String[] searchword;
	static String[] speechword = new String[20];
	
	SearchState(String r,Automaton _auto) {
		super(r);
		auto=_auto;
	}
	SearchState(String r){
		super(r);
	}
	public void act(){
		searchword=s.search("桜上水",auto.word1);//APIで検索
		 for(int i=0;i<searchword.length-5;i=i+2){
		 speecharray.add(searchword[i]+"駅から"+searchword[i+1]+"で"+searchword[i+2]+"駅で乗り換えてください");
		 }
         speecharray.add(searchword[searchword.length-5]+"駅から"+searchword[searchword.length-4]+"で"+searchword[searchword.length-3]+"駅に到着です");
         speecharray.add("料金は"+searchword[searchword.length-2]+"円,乗車時間は"+searchword[searchword.length-1]+"分掛かります。");
         speecharray.add("以上で宜しいでしょうか");
         speecharray.add("わかりました。");
         auto.currentState=auto.stSearchMessage0;
         auto.currentState.act();
		 
	}
}
