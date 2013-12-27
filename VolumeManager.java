
public class VolumeManager {
    MainManager m;
    static float volume = (float)-20;//ボリューム値
    static float volume2 = (float) -20;
    static boolean playState;
    static float dBData0;
    static int[] playStateData = {0,0,0,0,0};
    
    float dBData1 = 0;
    float dBData2 = 0;
    float dBData3 = 0;
    float dBData4 = 0;
    boolean isNowPlayingSynthe = false;
    int isNowPlayingSyntheCount = 0;
    boolean needAddControl = false;
	static int advDBData = 0;
	static boolean canPlayState;
	static int rmsData;
	static int dBData;
	static int delData;
	static int delData0;
    
    VolumeManager(){}
	
    VolumeManager(MainManager _m){
	    m=_m;
	    HarkDataReceiver h = new HarkDataReceiver(this);
	    h.receive();
	}
    
    

	void modulateNoise(int _rmsData,int _dBData){//騒音の大きさによって、音量を変更する
		rmsData = _rmsData;
		dBData = _dBData;


		if(!isNowPlayingSynthe){
			dBData0 = dBData;
//          System.out.println("aaaaaaaaaaaaaaaa" + delData0);
          delData0 = (int) (1.0549184204 * dBData0 -80.9943840151);
		}
		if(playStateData[1] != 0 && playStateData[0] != 0){
			advDBData = (playStateData[1] + playStateData[0]) /2 ;
		}
		if(playStateData[0] == 0 ){
			playStateData[0] = dBData;
		}
		else if(playStateData[1] == 0){
			playStateData[1] = playStateData[0];
			playStateData[0] = dBData;
		}
		else if(playStateData[2] == 0){
			playStateData[2] = playStateData[1];
			playStateData[1] = playStateData[0];
			playStateData[0] = dBData;
		}
		else if(playStateData[3] == 0){
			playStateData[3] = playStateData[2];
			playStateData[2] = playStateData[1];
			playStateData[1] = playStateData[0];
			playStateData[0] = dBData;
		}
		else{
			playStateData[4] = playStateData[3];
			playStateData[3] = playStateData[2];
			playStateData[2] = playStateData[1];
			playStateData[1] = playStateData[0];
			playStateData[0] = dBData;
		}
		
//		System.out.print("RMSData:" + rmsData + " " + "dBData:" + dBData );
//元		volume = (int) (1.0549184204 * dBData -94.9943840151);

		if(advDBData == 0){
		volume = (int) (1.0549184204 * dBData -80.9943840151);
		volume2 = (int) (1.0549184204 * dBData -90.9943840151);
		}
		else{
		volume = (int) (1.0549184204 * advDBData -80.9943840151);
		volume2 = (int) (1.0549184204 * advDBData -90.9943840151);
		}
		if(volume < -40){
			volume = -40;
		}
		if(volume2 < -40){
			volume2 = -40;
		}
		
		
		System.out.println("5s_dBData" + playStateData[0] + ":" +  playStateData[1] + ":" + playStateData[2]+ ":" + playStateData[3]+ ":" + playStateData[4]);
		
		int playLevel = 74;
		
		if( (playStateData[0] + playStateData[1] + playStateData[2] + playStateData[3] + playStateData[4]) / 5 > playLevel || playStateData[0] > playLevel -15 || playStateData[1] > playLevel -10 || playStateData[2] > playLevel -5){
			playState = false;
		}
		else{
			playState = true;
		}
		
		if( (playStateData[0] + playStateData[1] + playStateData[2] + playStateData[3] + playStateData[4]) / 5 > playLevel + 2 || playStateData[0] > playLevel +2 || playStateData[1] > playLevel +2 || playStateData[2] > playLevel + 2){
			canPlayState = false;
		}
		else{
			canPlayState = true;
		}
		
		//再生中の処理に関して
/*		if(isNowPlayingSynthe){
			dBData0 = dBData1 = dBData2 = dBData3 = dBData4 = 0;
			dBData0 = dBData;
		}else{
			if(dBData1 == 0 && dBData2 == 0 && dBData3 == 0 && dBData4 == 0){
				dBData1 = dBData;
			}
			else if(dBData2 == 0 && dBData3 == 0 && dBData4 == 0){
				dBData2 = dBData1;
				dBData1 = dBData;
			}
			else if(dBData3 == 0 && dBData4 == 0){
				dBData3 = dBData2;
				dBData2 = dBData1;
				dBData1 = dBData;
			}
			else{
				dBData4 = dBData3;
				dBData3 = dBData2;
				dBData2 = dBData1;
				dBData1 = dBData;
			}
		}
		
		if(isNowPlayingSynthe){
			if(dBData1 == 0 && dBData2 == 0 && dBData1 - dBData0 > 5 && dBData2 - dBData1 > 5 ){
				needAddControl = true;
				
			}
		}else{
			needAddControl = false;
		}
	*/	
			
		
	}
	
	
     String modulatedialog(float changevolume,String synthevalue){
		String changevalue=volumelimitmanager();
		if(changevalue==null){
		volume=volume+changevolume;
		return synthevalue;
		}
        return changevalue;//発話内容を変更
	}
	
	
	float volumeGet(){
		return volume -2;
	}
	
	float volumeGet2(float _FC){
		float FC = _FC;
//		float data = _data;
		float playRms = FCToPlayRms(FC);
		
		System.out.println("FC:" + FC + " playRms:" + playRms + "  rmsData:" + rmsData +" playRms/rmsData:" + playRms / rmsData );
		if(playRms / rmsData > 0.3 && playRms / rmsData < 1.8){ ///元のデータを使用
//			System.out.println("---------------0001" + delData0);
//			System.out.println("---------------0001" + FC);
			return FC;
		}
		else if(playRms / rmsData <= 0.3 ){//騒音が大きい時
			return FC + 5;
		}
		else{//騒音が小さい時
			return volume -5;
		}
		
	}
	
	float FCToPlayRms(float _FC2){
		float FC2 = _FC2;
//		float data = _data;
//		float playRms = 578 * FC2 + 17420;
		float playRms;
		if(FC2 > -25){
		    playRms = (float) (16866.4355048608 * Math.exp(0.1252726703 * FC2));
		}else{
			playRms = (float) (69140.016808083 * Math.exp(0.1533534075 * FC2));
		}
		if(playRms < 10){
			return 10;
		}
		else{
			return playRms;
		}
	}
	
	

	String volumelimitmanager(){//ボリュームの最高と最小を管理
		if(volume>0){//0以上の時はボリュームを上げる事ができない。
			return "これ以上ボリュームを上げる事ができません。";
		}
		else if(-40>volume){//-15以下の時はボリュームを下げる事ができない。
			return "これ以上ボリュームを下げる事ができません。";
		}
		return null;
	}
}
