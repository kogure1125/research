import java.io.IOException;
import java.util.ArrayList;

import jp.crestmuse.cmx.amusaj.sp.*;
import jp.crestmuse.cmx.processing.CMXController;



public class HarkDataReceiver {
	VolumeManager v;
	HarkDataReceiver(VolumeManager _v){
		v=_v;
	}

		  public void receive(){
		  try {
			System.out.println("start.");
			HarkDataStreamReceiver hdsr = new HarkDataStreamReceiver(5530);

			SampleModule sample = new SampleModule(v);
			hdsr.setTickTimer(null);

			
			CMXController cmx = CMXController.getInstance();
			
			cmx.addSPModule(hdsr);
			cmx.addSPModule(sample);
			cmx.connect(hdsr, 0, sample, 0);
			cmx.startSP();
			

		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	
	
}


class SampleModule extends SPModule {
	//音量抽出関係変数，配列
	float add = (float) 0.0;
	float result = (float) 0.0;
	float dataArray[] = new float[100];
	int dataArrays = 0;
	float addData = (float) 0.0;
	int rmsData = 0;    //音の振幅をRMSした結果
	int dBData = 0;     //RMSの結果からdBを推定した結果
	//音源定位関係変数，配列
	double angle[] = new double [100];
	double angleData = 0; 
	int angles = 0;
	double addAngle = 0;
	int angleResult = 0;
	int noiseData = 0;
	VolumeManager v;
	
	SampleModule(VolumeManager _v){
		v=_v;
	}
	public void execute(Object[] src, TimeSeriesCompatible[] dst) {
		HarkObject ho = (HarkObject)src[0];

		
		if (ho.src.length >= 1){
//          System.out.println("# of sources: " + ho.src.length);
//   		  System.out.println("x" + ho.src[0].info.x[0]);
//   		System.out.println("y" + ho.src[0].info.x[1]);
   		angleData = (ho.src[0].info.x[0] / Math.sqrt((ho.src[0].info.x[0] * ho.src[0].info.x[0]) + (ho.src[0].info.x[1] * ho.src[0].info.x[1])));

   		angleData = Math.acos(angleData) * 180 / Math.PI;
   		if(angles <=99){
   			angle[angles] = angleData;
   			angles = angles + 1;
   		}
   		if(angles == 99){
   			for(int iii= 0; iii < angle.length; iii++){
   				addAngle = addAngle + angle[iii];
   			}

   			angleResult = (int)(addAngle / angle.length);
 // 			System.out.println(angleResult);
   			angles = 0;
   			addAngle = 0;
   		}
   		
//   		System.out.println(Math.acos(angleData) * 180 / Math.PI);
		}
		
		
		
		
		if(ho.hasMicWave()){
		  for(int i = 0 ; i < ho.mic_wave_data.length ; i++){
		    add = add + ho.mic_wave_data[i] * ho.mic_wave_data[i];
		  }
   		 result = (float) Math.sqrt(add / ho.mic_wave_data.length);
   		 add = 0;
//         System.out.println(result);  		 
   		 
   		 if(dataArrays <= 99){
   			
   		dataArray[dataArrays] = result;
//   		System.out.println(dataArrays + "--" + dataArray[dataArrays]);
   		dataArrays = dataArrays + 1;
   		 }
   		if(dataArrays == 99){
  		  for(int ii = 0 ; ii < dataArrays ; ii++){
  			  addData = addData + dataArray[ii];
  		  }
  		  rmsData = (int) (addData / (dataArrays + 1));
          dBData = (int) (7.229990872 * Math.log(((int)(rmsData))) + 8.2334032299);

  		  
 
  		  
  		  // 		  System.out.println("-result-" + (resultData));
  		  
  		  
  		  ///resultDataが騒音レベル数値
  		  v.modulateNoise(rmsData,dBData);
  		  
  		  
  		  dataArrays = 0;
  		  addData = 0;
  		  rmsData = 0;
   		}
		}
		  }
		

//		System.out.println("11111111111111111");
//		System.out.println(ho.src[0].info.x[1]);
//		System.out.println("22222222222222222");
//		System.out.println(ho.src[0].info.x[2]);

	
	
	
	
	public Class[] getInputClasses() {
		return new Class[]{HarkObject.class};
	}
	public Class[] getOutputClasses() {
		return new Class[0];
	}
}
