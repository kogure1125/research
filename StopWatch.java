
class StopWatch extends Thread{
	int time=0;
	DialogueManager  d;
	    StopWatch(DialogueManager _d){
	    	time=0;
	    	this.start();
	    	d=_d;
	    }
	
	    public void run(){
		{
		for(;;){
		for (time=0;time<=20;time++) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		d.sendtime(time);
		                              }
		}
		}
	    }
	    public void settime(){
	    	time=0;
	    }
}
		
