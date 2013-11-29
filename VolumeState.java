
public class VolumeState extends State {

	VolumeState(String r) {
		super(r);
	}
	@Override
	public void act() {
        System.out.println("テスト安定");
        speech.synthesizer(response);
	}

}
