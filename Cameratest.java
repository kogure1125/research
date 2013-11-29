public class Cameratest extends Thread {
	
    boolean flag=true;
    MainManager main;
    SpeechSynthesizer s;
    SoundP p;
    
    Cameratest(MainManager _main,SoundP _p,SpeechSynthesizer _s){ //コンストラクタ
    	main=_main;
    	p=_p;
    	s=_s;
    }
	public void run(){
	  for(;;){
	  if(p.get()==true){ //カメラが認証したかどうか。
		  if(flag==true){ //flagがtrueの時のみ。
		  System.out.println("aa");
	   s.synthesizer("認識を開始します検索したいといってください");
	      flag=false;
	                     }
	                    }
	  else{
		  System.out.print("");
		   }
	  
	          }

      }
}
