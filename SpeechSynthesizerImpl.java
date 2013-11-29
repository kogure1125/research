import java.io.BufferedWriter;
import jp.crestmuse.cmx.processing.CMXController;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.JFrame;
import java.util.*;
import java.io.*;

public class SpeechSynthesizerImpl extends JFrame implements SpeechSynthesizer {
    
	CMXController controller = CMXController.getInstance();
	String synthetest="";
	
    public void synthesizer(String syntheValue,int volume){
    	
    }
	public void synthesizer(String syntheValue){
        String dic = "/usr/local/share/open_jtalk/open_jtalk_dic_utf_8-1.06";
        String voice = "/usr/local/share/hts_voice/nitech_jp_atr503_m001-1.05";
        List<String> list = new ArrayList<String>();
        list.add("open_jtalk");
        list.add("-x");
        list.add(dic);
        list.add("-m");
        list.add(voice + "/nitech_jp_atr503_m001.htsvoice");
        list.add("-ow");
        list.add("out.wav");

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
          //標準エラー出力を見る場合"/**/"を外す
          /*
    	BufferedReader err =  new BufferedReader(new InputStreamReader(p.getErrorStream()));
    	BufferedReader out = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	String s = null;
    	while ((s = err.readLine()) != null) {
    		System.err.println(s);
    	}
    	s = null;
    	while ((s = out.readLine()) != null) {
    		System.out.println(s);
    	}
    */
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
		controller.wavread(inData);
		controller.playMusic();
		for(int i=0;i<3;i++){
	       	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		/*while(controller.isNowPlaying()){
			System.out.println("aa");
			}*/
		controller.stopMusic();
		System.out.println("stop"); 
		//System.exit(0);

    	}
}
    