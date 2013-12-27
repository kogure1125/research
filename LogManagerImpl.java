import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogManagerImpl {

	
	
	
public void writesystemlog(String systemspeech){
	    File systemfile = new File("systemlog.txt");
		try{
		Calendar cal1 = Calendar.getInstance();  //(1)オブジェクトの生成
		  int month = cal1.get(Calendar.MONTH) + 1;  //(3)現在の月を取得
		  int day = cal1.get(Calendar.DATE);         //(4)現在の日を取得
		  int hour = cal1.get(Calendar.HOUR_OF_DAY); //(5)現在の時を取得
		  int minute = cal1.get(Calendar.MINUTE);    //(6)現在の分を取得
		  int second = cal1.get(Calendar.SECOND);    //(7)現在の秒を取得
		  
	      if (checkBeforeWritefile(systemfile)){
	    	  FileWriter filewriter = new FileWriter(systemfile,true);
	          
	          filewriter.write(month+"月"+day+"日"+hour+"時"+minute+"分"+second+"秒\t"+systemspeech+"\n");
	          filewriter.close();
	        }else{
	          System.out.println("ファイルに書き込めません");
	        }
		}catch(IOException e){
			System.out.println(e);
		}
    }
     public void writeuserlog(String mozi){
    	 File userfile = new File("userlog.txt");
 		try{
 			
 			Calendar cal1 = Calendar.getInstance();  //(1)オブジェクトの生成
 			  int month = cal1.get(Calendar.MONTH) + 1;  //(3)現在の月を取得
 			  int day = cal1.get(Calendar.DATE);         //(4)現在の日を取得
 			  int hour = cal1.get(Calendar.HOUR_OF_DAY); //(5)現在の時を取得
 			  int minute = cal1.get(Calendar.MINUTE);    //(6)現在の分を取得
 			  int second = cal1.get(Calendar.SECOND);    //(7)現在の秒を取得
 			  
 		      if (checkBeforeWritefile(userfile)){
 		    	 
 		    	  FileWriter filewriter = new FileWriter(userfile,true);
 		    	  System.out.println(month+"月"+day+"日"+hour+"時"+minute+"分"+second+"秒\t"+mozi+"\n");
 		          filewriter.write(month+"月"+day+"日"+hour+"時"+minute+"分"+second+"秒\t"+mozi+"\n");
 		          filewriter.close();
 		        }else{
 		         System.out.println("ファイルに書き込めません");
 		       }
 			}catch(IOException e){
 				System.out.println(e);
 			}
     }

	 private static boolean checkBeforeWritefile(File file){
		    if (file.exists()){
		      if (file.isFile() && file.canWrite()){
		        return true;
		      }
		    }
		    return  false;
	 }
}
