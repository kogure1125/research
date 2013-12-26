
public class VolumeManager {
    MainManager m;
    static float volume = (float)0;//ボリューム値
    VolumeManager(){}
	
    VolumeManager(MainManager _m){
	    m=_m;
	    HarkDataReceiver h = new HarkDataReceiver(this);
	    h.receive();
	}

	void modulateNoise(int noisedata){//騒音の大きさによって、音量を変更する
		System.out.print(noisedata+".");
        
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
		return volume;
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
