import java.util.regex.*;

public class CameraImpl extends Thread implements Camera {
	SoundP sound;
		String[] aa =new String[10]; //{year,month,day,hour,minute,second,zahyouX,zahyouY,height,width}
		//String[] stringArray = {"year","month","day","hour","minute","second","zahyouX","zahyouY","height","width"};
	  String year="",month="",day="",hour="",minute="",second="",zahyouX="",zahyouY="",height="",width="";
	  int intx;
	  int intsecond;
	  String[] strAry = new String[10];
	  CameraImpl(SoundP x){
		  this.start();
		  sound=x;
		  PipeReceiver receive = new PipeReceiver();
		  receive.start(this);
	  }
	 // public void camerastart(){
		//  PipeReceiver receive = new PipeReceiver();
		// receive.start(this);
	//}
	  public void dosplit(String _s){
		  String str = new String(_s);
		  String[] strAry = str.split(",");
/*			  year=strAry[0];
			  month=strAry[1];
			  day=strAry[2];
			  hour=strAry[3];
			  minute=strAry[4];
			  second=strAry[5];
			  zahyouX=strAry[6];
			  zahyouY=strAry[7];
			  height=strAry[8];
			  width=strAry[9];
	          intx=Integer.parseInt(zahyouX);
	          intsecond=Integer.parseInt(second);
	          */
			  sound.approval(strAry);
	  }
	  public void run(){
		 try{
			while(true){
		    sound.x = false;
			Thread.sleep(2000);
		    }
		    }catch(InterruptedException e){
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		  }
		  
	  }
	  
 }
