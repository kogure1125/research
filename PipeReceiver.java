    import java.io.*;
    
	public class PipeReceiver {
		Camera c=new CameraImpl();
		public void start(){
	    	try {
	    	String s;
		    BufferedReader r = new BufferedReader(new FileReader("/tmp/mypipe"));
		    while (true) {
			    s = r.readLine();
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