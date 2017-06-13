package com.lzairport.ais.utils;

import java.util.ArrayList;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.lzairport.ais.models.aodb.Flight;

/**
 * 语音的工具类
 * @author ZhangYu
 * @version 0.9a 13/05/14
 * @since JDK1.6 
 * 
 */
public class SpeechUtil extends Thread {
	
	private String message;

	public SpeechUtil(String message) {
		super();
		this.message = message;
	}

	/**
	 * 根据输入的字串转换成语音输出
	 * @param message 需要转换的字符串
	 */
	public synchronized void speakMessage() {  
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");   
		Dispatch sapo = sap.getObject();  
        try {  
            sap.setProperty("Volume", new Variant(80));  
            sap.setProperty("Rate", new Variant(0));  
            Dispatch.call(sapo, "Speak", new Object[]{message});  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            sapo.safeRelease();  
            sap.safeRelease();  
        }  
    }
	
	/**
	 * 将航班的航班号变成民航的数字读法的字符串
	 * @param flight
	 * @return  转换后的字符串
	 */
	public static String getFlightVoiceNo(Flight flight){
		
		String airline = "";
		
		if (flight.getAircraft() != null){
			airline = flight.getAircraft().getCarrier().getAirlines().getCnShortName().replace("航空", "");
		}
		
		List<String> listNo = new ArrayList<String>();
		String flightNo=airline;
		listNo.add(flight.getFlightNO().substring(2,3));
		listNo.add(" ");
		listNo.add(flight.getFlightNO().substring(3,4));
		listNo.add(" ");
		listNo.add(flight.getFlightNO().substring(4,5));
		listNo.add(" ");
		listNo.add(flight.getFlightNO().substring(5,6));
		
		for (String no:listNo){
			if (no.equals("0")){
				no = "洞";
			}else if (no.equals("7")){
				no = "拐";
			}else if (no.equals("2")){
				no = "两";
			}else if (no.equals("1")){
				no = "幺";
			}
			flightNo +=no;
		}
		return flightNo;
	}

	@Override
	public void run() {
		speakMessage();
	}

	
}
