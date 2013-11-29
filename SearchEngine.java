
import java.util.HashMap;
	
public class SearchEngine {
	
  public String search(String _in, String _out, String _user){
	HashMap<String,String> map = new HashMap<String, String>();
    map.put("新宿", "0");
    map.put("初台", "1.7");
    map.put("幡ケ谷", "2.7");
    map.put("笹塚", "3.6");
    map.put("代田橋", "4.4");
    map.put("明大前", "5.2");
    map.put("下高井戸", "6.1");
    map.put("桜上水", "7.0");
    map.put("上北沢", "7.8");
    map.put("八幡山", "8.4");
    map.put("芦花公園", "9.1");
    map.put("千歳烏山", "9.9");
    map.put("仙川", "11.5");
    map.put("つつじケ丘", "12.5");
    map.put("柴崎", "13.3");
    map.put("国領", "14.2");
    map.put("布田", "14.9");
    map.put("調布", "15.5");
    map.put("西調布", "17.0");
    map.put("飛田給", "17.7");
    map.put("武蔵野台", "18.8");
    map.put("多磨霊園", "19.6");
    map.put("東府中", "20.4");
    map.put("府中", "21.9");
    map.put("分倍河原", "23.1");
    map.put("中河原", "24.7");
    map.put("聖蹟桜ヶ丘", "26.3");
    map.put("百草園", "28.0");
    map.put("高幡不動", "29.7");
    map.put("南平", "32.1");
    map.put("平山城址公園", "33.4");
    map.put("長沼", "34.9");
    map.put("北野", "36.1");
    map.put("京王八王子", "37.9");

    
    String in = _in;
    String out = _out;
    String user = _user;
    String data = "0";
    float inFloat = 0;
    float outFloat = 0;
    float dataFloat = 0;
    int resultAdult = 100000;
    int value = 0;

    
    if (map.containsKey(in)){
      inFloat = Float.parseFloat(map.get(in));
    //  System.out.println(inFloat);
    }if (map.containsKey(out)){
        outFloat = Float.parseFloat(map.get(out));
    //    System.out.println(outFloat);
      }else{
      System.out.println("指定したキーは存在しません");
      data = "-1";
    }
    if(inFloat > outFloat){
    	dataFloat = inFloat - outFloat;
    }else if(outFloat > inFloat){
    	dataFloat = outFloat - inFloat;
    }
	dataFloat = (float) Math.ceil(dataFloat);
    //System.out.println(dataFloat);
    if(0 < dataFloat && dataFloat < 4){
    	resultAdult = 120;
    }else if(5 <= dataFloat && dataFloat <= 6){
    	resultAdult = 130;
    }else if(7 <= dataFloat && dataFloat <= 9){
    	resultAdult = 150;
    }else if(10 <= dataFloat && dataFloat <= 12){
    	resultAdult = 170;
    }else if(13 <= dataFloat && dataFloat <= 15){
    	resultAdult = 190;
    }else if(16 <= dataFloat && dataFloat <= 19){
    	resultAdult = 230;
    }else if(20 <= dataFloat && dataFloat <= 24){
    	resultAdult = 270;
    }else if(25 <= dataFloat && dataFloat <= 30){
    	resultAdult = 310;
    }else if(31 <= dataFloat && dataFloat <= 37){
    	resultAdult = 330;
    }else if(38 <= dataFloat && dataFloat <= 44){
    	resultAdult = 350;
    }else if(45 <= dataFloat && dataFloat <= 52){
    	resultAdult = 370;
    }
    if(user.equals("大人")){
//      System.out.println("adult" + data);
      data = Integer.toString(resultAdult);
      
    }else if(user.equals("子供")){
    	value = (int) Math.ceil((double)resultAdult / 20) * 10;
      data = Integer.toString(value);
//      System.out.println("child" + data);
    } 
    
	return data;
	
  }	
}