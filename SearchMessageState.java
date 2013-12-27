
public class SearchMessageState extends SearchState{
	int count;
	SearchMessageState(String r,int _count,Automaton _auto){
		super(r,_auto);
		count = _count;
	}
	public void act(){
        for(int i=0;i<speecharray.size();i++){
        	if(i==count){
			   speech.synthesizer(speecharray.get(i));
			   if(i==speecharray.size()-1){//speecharrayが最後の内容になった
				   speech.synthesizer("ご案内は以上です。ご利用ありがとう御座いました。");
				   speecharray.clear();//Arrayから検索内容を削除
				   auto.changestWait();//初期状態に戻る
				   
			   }
			  }
			}
		}
	}
		/*if(response.equals("1")){
        speech.synthesizer(speechword[0]);
		}
		if(response.equals("2")){
		speech.synthesizer(speechword[1]+"tset");
		}
		if(response.equals("3")){
		speech.synthesizer(speechword[2]+"test2");
		}
	}*/

