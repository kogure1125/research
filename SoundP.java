public class SoundP extends Thread{
    boolean x=false;
    MainManager m;
    String[] data = new String[10];
         int[] new_data = new int[10];
         int[] old_data = new int[10];
    int secondstart;
    
    SoundP(MainManager _m){
    	m=_m;
    }
    public void run(){
         Camera camera = new CameraImpl(this);
    }
    public boolean get(){
            return x;
    }
    
    public void approval(String _data[]){
         String data[] =(String[])_data.clone();
         for (int i = 0; i < data.length; i++) { //(2)
                 new_data[i] = Integer.parseInt(data[i]); //(3)
                 //System.out.println("data" + new_data[i]); //(4)
                 }
         //分別処理開始
         if(old_data[0] != 0){ //旧データがあるのか？
              if(new_data[5] == 0 && old_data[5] == 59){ //timeデータの整合処理(sec)
                old_data[5] = 0;
                old_data[4] += 1;
              }
              if(new_data[4] == 0 && old_data[4] == 59){ //timeデータの整合処理(min)
                old_data[4] = 0;
                old_data[3] += 1;
              }
              if(new_data[3] == 0 && old_data[3] == 23){ //timeデータの整合処理(hou)
                old_data[3] = 0;
                old_data[2] += 1;
              }
              
        
                   if(new_data[5] - old_data[5] <= 1){ //1s以下か？
//                         System.out.println("ok");
                         int height = new_data[8] - old_data[8];
                         int width = new_data[9] - old_data[9];
                         if(height * height + width * width <= 100){ //（ユークリッド）距離が100以下か？
//                                 System.out.println("okok");
/*                                 if(new_data[5] - old_old_data[5] <= 1){
                                         int old_height = new_data[8] - old_old_data[8];
                                         int old_width = new_data[9] - old_data[9];
                                         if(old_height * old_height + old_width * old_width <= 100){
                                                 System.out.println("okokok");
                                         }
                                 }*/
                                 x=true;
                         }
                  else{
                          x=false;
                  }
                   }
         else{
         x=false;
        }
         m.camerarelay(x);
         }
         //old_old_data = (int[])old_data.clone();
         old_data = (int[])new_data.clone();
        
   }
    }
