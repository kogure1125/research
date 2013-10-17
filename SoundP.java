
public class SoundP extends Thread{
    boolean x=false;
    int zahyouX=0;
    int secondstart;
    public void run(){
	Camera camera = new CameraImpl(this);
 }
    public boolean get(){
    	System.out.println(zahyouX);
    	return x;
    }
    public void approval(int _zahyouX,int second){
	zahyouX=_zahyouX;
	if(200<zahyouX&&zahyouX<400){
		x=true;
	}
	else{
	    x=false;
         }
  }
    }
