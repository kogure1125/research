
public class VolumeState extends State {
   
	float volume,changevolume;
	VolumeState(String r,float d ) {
		super(r);
		volume=d;
	}
	@Override
	public void act() {
        speech.synthesizer(response,volume);//ここを飛ばす！！！！！
	}

}
