
public class LastState extends SearchState{

	LastState(String r) {
		super(r);
	}

	public void act() {
		   speech.synthesizer("ご案内は以上です。ご利用ありがとう御座いました。");
		   speecharray.clear();//Arrayから検索内容を削除
		   auto.changestWait();//初期状態に戻る
		
	}

	
}
