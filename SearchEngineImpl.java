import java.net.*;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class SearchEngineImpl implements SearchEngine {

	private static String stationcode;
	private static String stationname;
	private static String stationnamekana;
	private static String prefecture;
	private static String stationnamehiragana;
	public Document doc;
	private String departureStationNumer;
	private String arrivalStationNumber;
	FileReader fr;
	
	
	public String stationToAPINumber(String _stationName){
		String APINumber = null;
		String stationName = _stationName;

		try {
			fr = new FileReader("20131201.csv");
			LineNumberReader lnr = new LineNumberReader(fr);
			String str;

			while ((str = lnr.readLine()) != null) {
			String[] strAry = str.split(",");
			if (strAry.length == 4) {
			Data(strAry);
			if(strAry[1].equals(stationName)){
			   System.out.println(stationcode);
			   APINumber = stationcode;
			}
			}
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return APINumber;
	}
	
	
	public static void Data(String[] strAry) {
		if ((strAry != null) && (strAry.length == 4)) {
		stationcode = strAry[0];
		stationname = strAry[1];
		stationnamekana = strAry[2];
		prefecture = strAry[3];
		//stationnamehiragana =strAry[4];
		}
		}
	
	

	@Override
	public String[] search(String _departureStation, String _arrivalStation) {
		// TODO Auto-generated method stub
		String departureStation = _departureStation;
		String arrivalStation = _arrivalStation;
		
		departureStationNumer = stationToAPINumber(departureStation);
		arrivalStationNumber = stationToAPINumber(arrivalStation);
		
		
		
		
    	String xmlResponse = "";

         System.setProperty("proxySet", "true");
         System.setProperty("proxyHost", "cache.educ.chs.nihon-u.ac.jp");
         System.setProperty("proxyPort", "8080");
     //   System.setProperty("proxyHost", "localhost");
      //  System.setProperty("proxyPort", "10080");
         String request = "http://api.ekispert.jp/v1/xml/search/course?";
         String keyData = "key=R5pfmmTkY7mSArak";
         String from = "&from=";
         String to = "&to=";
         String fromData = departureStationNumer;       
         String toData = arrivalStationNumber;

         String requestData = request + keyData + to + toData + from + fromData;
         System.out.println(requestData);
         
         
		try {
			URL url = new URL(requestData);
	         HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();

	         urlconn.setRequestMethod("GET");
	         urlconn.setInstanceFollowRedirects(false);
	         urlconn.setRequestProperty("Accept-Language", "ja;q=0.7,en;q=0.3");

	         urlconn.connect();

	         Map headers = urlconn.getHeaderFields();
	         Iterator it = headers.keySet().iterator();
	         System.out.println("レスポンスヘッダ:");
	         while (it.hasNext()){
	             String key= (String)it.next();
	             System.out.println("  " + key + ": " + headers.get(key));
	         }

	         System.out.println("レスポンスコード[" + urlconn.getResponseCode() + "] " +
	                            "レスポンスメッセージ[" + urlconn.getResponseMessage() + "]");
	         System.out.println("\n---- ボディ ----");

	         BufferedReader reader =
	             new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
	         

	         while (true){
	             String line = reader.readLine();
	             if ( line == null ){
	                 break;
	             }
	             //System.out.println(line);
	             xmlResponse = xmlResponse + line;

	             	Calendar cal1 = Calendar.getInstance(); 
	                 int year = cal1.get(Calendar.YEAR);        //(2)現在の年を取得
	                 int month = cal1.get(Calendar.MONTH) + 1;  //(3)現在の月を取得
	                 int day = cal1.get(Calendar.DATE);         //(4)現在の日を取得
	                 int hour = cal1.get(Calendar.HOUR_OF_DAY); //(5)現在の時を取得
	                 int minute = cal1.get(Calendar.MINUTE);    //(6)現在の分を取得
	                 int second = cal1.get(Calendar.SECOND);
	                 String fileName = String.valueOf(year) + "_" + String.valueOf(month) + "_" + String.valueOf(day) + "_" + String.valueOf(hour) + "_" + String.valueOf(minute) + "_" + String.valueOf(second) + ".xml";
	                 
	             	File file = new File(fileName);
	                 PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	                 pw.println(line);
	                 pw.close();
	             
	         }

	         reader.close();
	         urlconn.disconnect();
			
	         
	         //パーサを作成
	         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	         DocumentBuilder db = dbf.newDocumentBuilder(); //例外処理ParserConfigurationException
	       //入力ストリームを作成
	         StringReader sr = new StringReader(xmlResponse);
	         InputSource is = new InputSource(sr);
	       //パース
	         doc = db.parse(is); //例外処理SAXException
	         
	         

	         
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        


         

     	
         //全体的なパース
         NodeList courseList = doc.getElementsByTagName("Course");
         
         //一件目のパースを開始
         Node course1 = courseList.item(0);
         NodeList routeList1 = course1.getChildNodes();
         Node route1 = routeList1.item(0);
         NodeList route1child = route1.getChildNodes();
         System.out.println("長さ：" + route1child.getLength());
         //駅の経路データの抽出
         String[] result1_station = new String[route1child.getLength() + 2];
         for(int i = 0; i < route1child.getLength();i++){
         	if(i % 2 == 0){
         		result1_station[i] = route1child.item(i).getLastChild().getFirstChild().getFirstChild().getNodeValue();
         	}
         	else{
         		result1_station[i] = route1child.item(i).getFirstChild().getFirstChild().getNodeValue();
         	}
         }
         //料金データの抽出（大人料金しか出ないみたい
         Node route11 = routeList1.item(1);
         result1_station[route1child.getLength()] = route11.getFirstChild().getFirstChild().getNodeValue();
         //所要時間データを抽出（分単位みたい
         NamedNodeMap attrs = route1.getAttributes();
         Node timeOnBoard = attrs.getNamedItem("timeOnBoard");
         int timeOnBoardInt = Integer.parseInt(timeOnBoard.getNodeValue());
         Node timeWalk = attrs.getNamedItem("timeWalk");
         int timeWalkInt = Integer.parseInt(timeWalk.getNodeValue());
         Node timeOther = attrs.getNamedItem("timeOther");
         int timeOtherInt = Integer.parseInt(timeOther.getNodeValue());
         result1_station[route1child.getLength() + 1] = Integer.toString(timeOnBoardInt + timeWalkInt + timeOtherInt);
         
       //二件目のパースを開始
         Node course2 = courseList.item(1);
         NodeList routeList2 = course2.getChildNodes();
         Node route2 = routeList2.item(0);
         NodeList route2child = route2.getChildNodes();
         System.out.println("長さ：" + route2child.getLength());
         
       //駅の経路データの抽出
     	String[] result2_station = new String[route2child.getLength() + 2];
         for(int i = 0; i < route2child.getLength();i++){
         	if(i % 2 == 0){
         		result2_station[i] = route2child.item(i).getLastChild().getFirstChild().getFirstChild().getNodeValue();
         	}
         	else{
         		result2_station[i] = route2child.item(i).getFirstChild().getFirstChild().getNodeValue();
         	}
             
         }
       //料金データの抽出（大人料金しか出ないみたい      
         Node route21 = routeList2.item(1);
         result2_station[route2child.getLength()] = route21.getFirstChild().getFirstChild().getNodeValue();
       //所要時間データを抽出（分単位みたい
         NamedNodeMap attrs2 = route2.getAttributes();
         Node timeOnBoard2 = attrs2.getNamedItem("timeOnBoard");
         int timeOnBoard2Int = Integer.parseInt(timeOnBoard2.getNodeValue());
         Node timeWalk2 = attrs2.getNamedItem("timeWalk");
         int timeWalk2Int = Integer.parseInt(timeWalk2.getNodeValue());
         Node timeOther2 = attrs2.getNamedItem("timeOther");
         int timeOther2Int = Integer.parseInt(timeOther2.getNodeValue());
         result2_station[route2child.getLength() + 1] = Integer.toString(timeOnBoard2Int + timeWalk2Int + timeOther2Int);
         
         
         ///抽出データの出力（一件目）
         for(int i = 0; i < result1_station.length - 2 ;i++){
         	System.out.println(result1_station[i]);
         }
         System.out.println("\\" + result1_station[result1_station.length -2] + ":" + result1_station[result1_station.length -1] + "分");
         
       ///抽出データの出力（二件目）
/*         for(int i = 0; i < result2_station.length - 2 ;i++){
         	System.out.println(result2_station[i]);
         }
         System.out.println("\\" + result2_station[result2_station.length -2] + ":" + result2_station[result2_station.length -1] + "分");
*/ 
        String[] result_station = new String[20];
        result_station=result1_station;
		for(int i=0;i<result1_station.length-2;i=i+2){
			//result_station[i]=changekana(result1_station[i]);
		}
		return result_station;
	}
	
	public String changekana(String _stationName){
		String APINumber = null;
		String stationName = _stationName;

		try {
			fr = new FileReader("20131201.csv");
			LineNumberReader lnr = new LineNumberReader(fr);
			String str;

			while ((str = lnr.readLine()) != null) {
			String[] strAry = str.split(",");
			if (strAry.length == 4) {
			Data(strAry);
			if(strAry[1].equals(stationName)){
			   APINumber = stationnamehiragana;
			}
			}
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return APINumber;
	}
	}
	
	