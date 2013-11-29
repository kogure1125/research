import java.io.*;

public class PipeReceiver {
    public void start(Camera c){
<<<<<<< HEAD
                String[] aa =new String[10]; //{year,month,day,hour,minute,second,zahyouX,zahyouY,height,width}
                //String[] stringArray = {"year","month","day","hour","minute","second","zahyouX","zahyouY","height","width"};
        
         String[] strArray = new String[10];
         int[] new_data = new int[10];
         int[] old_data = new int[10];
         int[] old_old_data = new int[10];
        
            
            try {
         BufferedReader r = new BufferedReader(new FileReader("/tmp/mypipe"));
         while (true) {
                String s = r.readLine();
                c.dosplit(s);
                if (s == null)
                 break;
         }
         r.close();
        } catch (IOException e) {
         e.printStackTrace();
        }
    }
}
=======
		String[] aa =new String[10]; //{year,month,day,hour,minute,second,zahyouX,zahyouY,height,width}
		//String[] stringArray = {"year","month","day","hour","minute","second","zahyouX","zahyouY","height","width"};
	  
	  String[] strArray = new String[10];
	  int[] new_data = new int[10];
	  int[] old_data = new int[10];
	  int[] old_old_data = new int[10];
	  
    	
    	try {
	    BufferedReader r = new BufferedReader(new FileReader("/tmp/mypipe"));
	    while (true) {
		String s = r.readLine();
		c.dosplit(s);
		if (s == null)
		    break;
	    }
	    r.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
>>>>>>> 474e1b8472b36209e2a4fda1635762cca2964f1a
