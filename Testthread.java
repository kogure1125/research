
public class Testthread extends Thread{
	int time=0;
    Testthread(){
    	time=0;
    	this.start();
    }

    public void run(){
	{
	for(;;){
	for (time=0;time<=10;time++) {
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	} 
	System.out.println("test"+time);
	                              }
	}
	}
    }
    public int get(){
    	return time;
    }
}
