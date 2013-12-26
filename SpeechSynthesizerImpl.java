import java.io.BufferedWriter;
import jp.crestmuse.cmx.processing.CMXController;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;
import java.util.*;
import java.io.*;


public class SpeechSynthesizerImpl extends JFrame implements SpeechSynthesizer {
    
	CMXController controller = CMXController.getInstance();
	String synthetest="";
	VolumeManager v = new VolumeManager();
	int time=0;
	StopWatch stop;
	
	SpeechSynthesizerImpl(){
	}
	
	public int get(){
		return time;
	}
	public void synthesizer(String syntheValue,float volume){//ValumeStateの時のみ使用
    	syntheValue=v.modulatedialog(volume,syntheValue);
    	
    	this.synthesizer(syntheValue);
    	}
	
	public void synthetiming(String syntheValue,StopWatch stop){
		while(stop.time>15){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//stop.settime();
			}
		}

	public void synthesizer(String syntheValue){
		time=0;
		System.out.println(syntheValue);

	    String dic = "/usr/local/share/open_jtalk/open_jtalk_dic_utf_8-1.06";
	    String voice = "/usr/local/share/hts_voice/mei_normal";
	    List<String> list = new ArrayList<String>();
	    list.add("open_jtalk");
	    list.add("-x");
	    list.add(dic);
	    list.add("-td");
	    list.add(voice + "/tree-dur.inf");
	    list.add("-tm");
	    list.add(voice + "/tree-mgc.inf");
	    list.add("-tf");
	    list.add(voice + "/tree-lf0.inf");
	    list.add("-tl");
	    list.add(voice + "/tree-lpf.inf");
	    list.add("-md");
	    list.add(voice + "/dur.pdf");
	    list.add("-mm");
	    list.add(voice + "/mgc.pdf");
	    list.add("-mf");
	    list.add(voice + "/lf0.pdf");
	    list.add("-ml");
	    list.add(voice + "/lpf.pdf");
	    list.add("-dm");
	    list.add(voice + "/mgc.win1");
	    list.add("-dm");
	    list.add(voice + "/mgc.win2");
	    list.add("-dm");
	    list.add(voice + "/mgc.win3");
	    list.add("-df");
	    list.add(voice + "/lf0.win1");
	    list.add("-df");
	    list.add(voice + "/lf0.win2");
	    list.add("-df");
	    list.add(voice + "/lf0.win3");
	    list.add("-dl");
	    list.add(voice + "/lpf.win1");
	    list.add("-ow");
	    list.add("out.wav");
	    list.add("-s");
	    list.add("48000");
	    list.add("-p");
	    list.add("260");
	    list.add("-a");
	    list.add("0.58");
	    list.add("-u");
	    list.add("0");
	    list.add("-em");
	    list.add(voice + "/tree-gv-mgc.inf");
	    list.add("-ef");
	    list.add(voice + "/tree-gv-lf0.inf");
	    list.add("-cm");
	    list.add(voice + "/gv-mgc.pdf");
	    list.add("-cf");
	    list.add(voice + "/gv-lf0.pdf");
	    list.add("-jm");
	    list.add("0.7");
	    list.add("-jf");
	    list.add("0.5");
	    list.add("-k");
	    list.add(voice + "/gv-switch.inf");
	    list.add("-z");
	    list.add("6000");

        //リストの中身を見る場合
        /*String ss = "";
        for (String s : list) {
          ss += s + " ";
        }
        System.out.println(ss);
        */

        //外部プロセス実行
        try{
          ProcessBuilder pb = new ProcessBuilder(list);
          Process p = pb.start();
          BufferedOutputStream os = new BufferedOutputStream(p.getOutputStream());  // Javaから見るとデータをOpenJTalkに出力してるのでBufferedOutputStreamを用いる．
    //標準入力（コマンドラインやecho）から入力したい場合は"/**/"を外して下の一行をコメントアウト
    /*
          BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
          byte[] sentence = r.readLine().getBytes();
    */
    	byte[] sentence = syntheValue.getBytes();  //音声合成したい文字列をバイトデータに
    	os.write(sentence, 0, sentence.length);
    	os.flush();  //書き込んだデータを全て吐き出す
    	os.close();  //吐き出した後は閉じる
     
            int ret = p.waitFor();  //プロセスが終了したら次の処理に移る
            System.out.println("戻り値：" + ret);
          }catch(InterruptedException e){
            System.out.println("error1");
          }catch(IOException e){
            System.out.println("error2");
          }
        wavPlay();
}

    
    public void wavPlay(){
    	
		CMXController controller = CMXController.getInstance();
		String inData = "out.wav";
		controller.wavread(0,inData);
		 float volum = (float)v.volumeGet();
		FloatControl FC = controller.getMasterGainControl(0);
//		System.out.println(FC.getValue()+"test");
		FC.setValue(volum);
//		System.out.println(FC.getValue()+"test2");
    	
		String inData1 = "wait.wav";
		controller.wavread(1,inData1);
		FloatControl FC1 = controller.getMasterGainControl(1);
//		System.out.println(FC1.getValue()+"test");
		FC1.setValue(-20);
//		System.out.println(FC1.getValue()+"test2");

    	
    	while(!v.playState ){
    		

    		
/*			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			System.out.println("発話停止中");
			if(!controller.isNowPlaying(1)){
//				controller.playMusic(1);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
/*			while(controller.isNowPlaying(1)){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("おまちください再生中");
			}
			controller.stopMusic(1);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
	
			
			}
    	

    	



		
		controller.playMusic(0);
		v.isNowPlayingSynthe = true;
		/*for(int i=0;i<4;i++){
	       	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}*/
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(controller.isNowPlaying(0)){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!v.canPlayState){
				controller.stopMusic(0);
				System.out.println("再生音声強制終了");
				v.isNowPlayingSynthe = false;
				wavPlay();
			}
			else{
				FC.setValue(v.volumeGet2(FC.getValue()));
				System.out.println("音量変更" + v.volumeGet2(FC.getValue()));
			}
			
			}
		controller.stopMusic(0);
		v.isNowPlayingSynthe = false;
		System.out.println("発話終了"); 
		time=1;
    	}
    public boolean playtiming(){
    	boolean play = false;
    	return play;
    }
}
    
