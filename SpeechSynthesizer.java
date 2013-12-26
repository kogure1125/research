public interface SpeechSynthesizer {
	
	public abstract void synthesizer(String syntheValue,float volume);
	public abstract void synthesizer(String syntheValue);
	public abstract void wavPlay();
	public int get();
}
