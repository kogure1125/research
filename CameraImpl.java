import java.util.regex.*;

public class CameraImpl implements Camera {
	SoundP sound = new SoundP();
		String[] aa =new String[10]; //{year,month,day,hour,minute,second,zahyouX,zahyouY,height,width}
		//String[] stringArray = {"year","month","day","hour","minute","second","zahyouX","zahyouY","height","width"};
	  String year="",month="",day="",hour="",minute="",second="",zahyouX="",zahyouY="",height="",width="";
	  public void camerastart(){
		  PipeReceiver receive = new PipeReceiver();
		  receive.start();
	}
	  public void dosplit(String _s){
		  String str = new String(_s);
		  String[] strAry = str.split(",");
			  year=strAry[0];
			  month=strAry[1];
			  day=strAry[2];
			  hour=strAry[3];
			  minute=strAry[4];
			  second=strAry[5];
			  zahyouX=strAry[6];
			  zahyouY=strAry[7];
			  height=strAry[8];
			  width=strAry[9];
			  System.out.println(width);
	  }
	}
