import java.util.regex.*;

public class CameraImpl extends Thread implements Camera {
        SoundP sound;
         String[] strAry = new String[10];
         CameraImpl(SoundP x){
                 this.start();
                 sound=x;
                 PipeReceiver receive = new PipeReceiver();
                 receive.start(this);
         }
         // public void camerastart(){
                // PipeReceiver receive = new PipeReceiver();
                // receive.start(this);
        //}
         public void dosplit(String _s){
                 String str = new String(_s);
                 String[] strAry = str.split(",");
                         sound.approval(strAry);
         }
         public void run(){
                 try{
                        while(true){
                 sound.x = false;
                        Thread.sleep(5000);
                 }
                 }catch(InterruptedException e){
                        // TODO 自動生成された catch ブロック
                        e.printStackTrace();
                 }
                
         }
        
 }