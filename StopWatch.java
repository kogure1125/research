
class StopWatch extends Thread{
	int time=0;
	DialogueManagerImpl  d;
	MainManagerImpl m;
	    StopWatch(DialogueManagerImpl _d){
	    	time=0;
	    	this.start();
	    	d=_d;
	    }

		public void run(){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		for(;;){
		  for (time=0;time<=20;time++) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		if(time==3||time==10||time==20){
		d.sendtime(time);
		}
		System.out.println(time+"秒");
	    if(d.auto.currentState.speech.get()==0){
	    	System.out.println("aa");
	 	time=0;
	    }
		
                              }
		}
	    }
	    
	    public void settime(){
	    	time=0;
	    }
}
		
