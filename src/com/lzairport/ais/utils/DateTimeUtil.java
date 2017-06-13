package com.lzairport.ais.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.lzairport.ais.models.aodb.Flight;
import com.lzairport.ais.utils.SYS_VARS.Week;



/**
 * 日期时间类型的工具类
 * @author ZhangYu
 * @version 0.9a 13/05/14
 * @since JDK1.6 
 * 
 */

public class DateTimeUtil {
	
	
	public final static int addHours=8;
	
	public final static int Err_Day=-999;
	
	/**
	 * 计算两个日期之间相差的天数
	 * @param smdate 前一个日期
	 * @param bdate  后一个日期
	 * @return 两个日期之间相差的天数
	 * @throws ParseException
	 */
	 public static int daysBetween(Date smdate,Date bdate)    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	        try {
	        	smdate=sdf.parse(sdf.format(smdate));  	        	
				bdate=sdf.parse(sdf.format(bdate));
				Calendar cal = Calendar.getInstance();    
		        cal.setTime(smdate);    
		        long time1 = cal.getTimeInMillis();                 
		        cal.setTime(bdate);    
		        long time2 = cal.getTimeInMillis();         
		        long between_days=(time2-time1)/(1000*3600*24);  
		        return Integer.parseInt(String.valueOf(between_days)); 				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Err_Day;
			}  
	                  
	    }   
	 
	 

	 
	 
	 
	 /**
	  * 计算两个日期中的间隔毫秒数    
     * @param smdate 前一个日期
	 * @param bdate  后一个日期
	 * @return 两个日期之间相差的毫秒数
	 * @throws ParseException
	  */
	 public static long MillisecondBetween(Date smdate,Date bdate) {
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	       return time2-time1;  
	 }
	 
	 /**
	  * 将0930这样的时间字符串转化为毫秒 如果转换错误返回0
	  * @param HHMM 时间字符串
	  * @return 转换的毫秒数
	  */
	 public static long HHMMToMillisecond(String HHMM){
		 
		try {
			int Hour = Integer.parseInt(HHMM.substring(0, 2));
			int Minute = Integer.parseInt(HHMM.substring(2, 4));
			int Days =0; 
			if (HHMM.length()>=6){
				Days = Integer.parseInt(HHMM.substring(5));
				if (HHMM.substring(4, 5).equals("+")){
					return  Hour*3600*1000+Minute*1000*60+Days*3600*1000*24;
				}else{
					return  Hour*3600*1000+Minute*1000*60-Days*3600*1000*24; 
				}
			}else {
				return Hour*3600*1000+Minute*1000*60;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	 }
	 
	 
	 /**
	  * 以HHmm格式返回航班实体类的日期类型数据
	  * @param flight 航班实体类
	  * @param date 需要转换的日期
	  * @return 返回HHmm格式的字符串
	  */
	 public static String dateToHHMM(Flight flight,Date date){
		String result = null;
		String hhnn = DispTime(date);  
		Integer days = DateTimeUtil.daysBetween( flight.getExecDate(),date);
		if (days >0){
			result = hhnn+"+"+days.toString();
		}else if (days < 0 ){
			result = hhnn+days.toString();
		}else{
			result = hhnn;
		}
		return result;
	 }
	 
	 /**
	  * 
	  * @Description: 返回对应日期的差别年份的日期
	  * @param date  日期
	  * @param diffYear 差别的年份
	  * @return
	  */
	 public static Date getDiffYearDate(Date date,int diffYear){
		 	Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.YEAR,(cal.get(Calendar.YEAR)-diffYear));
			return cal.getTime();
	}
	 
	 
	 /**
	  * 
	  * @Description: 将字符串hhmm转换成一个日期类型
	  * @param date 基础日期
	  * @param hhmm 时间字符串hh-小时，mm-分钟
	  * @return
	  */
	 public static Date hhmmtoDate(Date date,String hhmm){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hhmm.substring(0, 2)));
			cal.set(Calendar.MINUTE, Integer.parseInt(hhmm.substring(2, 4)));	
			return cal.getTime();			
	 }
	 
	 
	 /**
	  * 以HHmm格式返回航班实体类的日期类型数据
	  * @param flight 航班实体类
	  * @param date 需要转换的日期
	  * @return 返回HHmm格式的字符串
	  */
	 public static String dateToHH_MM(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
  		return sdf.format(date);
	 }
	 
	 
	 
	 public static String dateToYYYYMMDD(Date date){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 return sdf.format(date);
	 }
	 
	 /**
	  * 一个日期加上毫秒返回结果
	  * @param date 需要增加的日期
	  * @param millis 增加的毫秒数
	  * @return 增加毫秒数的日期
	  */
	 public static Date addMillisecond(Date date,long millis){
		 
		 Calendar cal = Calendar.getInstance();    
	     cal.setTime(date);   
	     cal.add(Calendar.HOUR_OF_DAY,(int) (millis/(1000*3600)));
	     cal.add(Calendar.MINUTE, (int) (millis%(1000*3600)/(1000*60)));
		return cal.getTime();
		 
	 }
	 
	 
	 /**
	  * 将一个时间的分钟以靠0和靠5的方式进行近似取值
	  * @param date 需要取值的时间
	  * @return 返回的近似值
	  */
	 public static Date roundDate(Date date){
		 Calendar cal = Calendar.getInstance();    
	     cal.setTime(date);
	     int mBit = cal.get(Calendar.MINUTE) % 10;
	     if ((mBit >= 0)&&(mBit <3 )){
	    	 //如果>0  and <3 取值0
	    	 cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-mBit);
	     }else if(mBit < 8){
	    	 //如果>3 and <8 取值 5
	    	 cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-(mBit-5));
	     }else {
	    	 //如果>8 进位+1 取值为0
	    	 cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+(10-mBit));
	     }
		return cal.getTime();
	 }
	 
	 /**
	  *  以"时时分分"返回日期类型
	  * @param date 传入的日期时间类
	  * @return "时时分分"形式的字符串
	  */
	 public static String DispTime(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("HHmm");
		return sdf.format(date);
		 
	 }
	 
	 
		/**
		 * 返回在今天的基础上加或者减天数的日期,返回日期时，分，秒为0
		 * @param day 天数
		 * @return 加减后日期
		 */
		public static Date addToDay(Date date,int day){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 
					calendar.get(Calendar.DATE),0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.add(Calendar.DATE, day);
			return calendar.getTime();
			
		}
		
		/**
		 * 将01MAY14之类格式的Eterm日期字符串转换为日期类型
		 * @param EtermDate Eterm日期字符串
		 * @return 日期类型
		 * @throws ParseException 转换异常
		 */
		public static Date parseEtermDate(String EtermDate){
			SimpleDateFormat sdf =null;
			sdf = new SimpleDateFormat("ddMMMy",Locale.US);
			try {
				return sdf.parse(EtermDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		/**
		 * 
		 * @Description: 转换一个民航统计系统日期字符串
		 * @param AspDate 民航统计系统日期字符串
		 * @return Java.util类型的Date
		 */
		public static Date  parseAspDate(String AspDate){
			SimpleDateFormat sdf =null;
			sdf = new SimpleDateFormat("MM/dd/y");
			try {
				return sdf.parse(AspDate);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			
		}

		
		
		public static String formatEtermDate(Date date){
			SimpleDateFormat sdf =null;
			sdf = new SimpleDateFormat("ddMMMyy",Locale.US);
			
			return sdf.format(date);
		}
	 
		
		/**
		 * 将字符串转换为日期时间
		 * @param str 字符串
		 * @return 转换的日期时间
		 */
		public static Date strToDate(String str){
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			if (Ais_String_Util.PatternIsMatch("\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}", str)){
				//匹配2014/09/29  12:00:00这样的日期
				sdf.applyPattern("yyyy/M/d HH:mm:ss");
			}else if (Ais_String_Util.PatternIsMatch("\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{2}:\\d{2}", str)){
				//匹配2014/09/29  12:00:00这样的日期
				sdf.applyPattern("yyyy/MM/dd HH:mm");
			}else 	if (Ais_String_Util.PatternIsMatch("\\d{4}-\\d{1}-\\d{2}", str)){
				//匹配2014-09-29这样的日期
				sdf.applyPattern("yyyy-M-dd");
				
			}else if (Ais_String_Util.PatternIsMatch("\\d{4}-\\d{2}-\\d{2}", str)){
				//匹配2014-09-29这样的日期
				sdf.applyPattern("yyyy-MM-dd");
				
			}else if (Ais_String_Util.PatternIsMatch("\\d{4}-\\d{2}-\\d{2}\\s\\d{4}", str)){
				//匹配2014-09-29  1200这样的日期
				sdf.applyPattern("yyyy-MM-dd HHmm");
				
			}else if (Ais_String_Util.PatternIsMatch("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}", str)){
				//匹配2014-09-29  12:00:00这样的日期
				sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
				
			}else if (Ais_String_Util.PatternIsMatch("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}", str)){
				//匹配2014-09-29  12:00:00这样的日期
				sdf.applyPattern("yyyy-MM-dd HH:mm");
				
			}else if (Ais_String_Util.PatternIsMatch("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d", str)){
				//匹配2014-09-29  12:00:00这样的日期
				sdf.applyPattern("yyyy-MM-dd HH:mm:ss.S");
				
			}else if (Ais_String_Util.PatternIsMatch("\\d{8}", str)){
				sdf.applyPattern("yyyyMMdd");
			}
			
			
			if ((str !=null)&&(!str.trim().equals(""))){
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
		
		
		/**
		 * 将日期时间转换为yyyy-MM-dd HHmm的方式字符串
		 * @param date  日期时间
		 * @return 转换后的字符串
		 */
		public static String DateToStr(Date date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmm");
			return sdf.format(date);
		}
		
		/**
		 * 
		 * @Description: TODO转换成清算中心需要的格式
		 * @param date
		 * @return
		 */
		public static String DateToSettlementStr(Date date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			return sdf.format(date);
		}
		
		/**
		 * 根据电报日时组返回一个GMT+8的日期时间
		 * @param date
		 * @param dateGroup
		 * @return GMT+8的日期时间
		 */
		public static Date getgrpGMT8Time(Date date,String dateGroup){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR,-addHours);
			cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dateGroup.substring(0, 2)));
			cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(dateGroup.substring(2, 4))+addHours);
			cal.set(Calendar.MINUTE, Integer.parseInt(dateGroup.substring(4, 6)));
			return cal.getTime();
			
		}
		

		/**
		 * 
		 * @Description: 根据本地时间返回一个UTC时间
		 * @param date 本地时间
		 * @return UTC时间
		 */
		public static Date getUTCTime(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR,-addHours);
			return cal.getTime();
		}
		
		/**
		 * 根据HHmm的时间返回一个换算过的北京时间 ，一般用于计算航班的起飞，落地，时间
		 * @param execDate 航班执行日期
		 * @param dateGroup 电报日时组
		 * @param HHmm 时间
		 * @return 北京时间
		 */
		public static Date getExecTime(Date execDate,String dateGroup,String HHmm){
			Calendar cal = Calendar.getInstance();
			cal.setTime(execDate);
			//取得日期
			int execDay = cal.get(Calendar.DATE);
			//取得上个月最后一天
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			cal.set(Calendar.DATE, 1);
			int preLastDay = cal.getActualMaximum(Calendar.DATE);
				//恢复月份,日期
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
			cal.set(Calendar.DATE, execDay);
			//取得发电日时组的日期
			int grpDay = Integer.parseInt(dateGroup.substring(0, 2));
			
			if ((grpDay != execDay) &&(grpDay == preLastDay)
					&&(execDay == 1)){
				/**
				 * 如果发电日时组的day是保存电报时间上一个月的月底
				 * 而且保存电报的day与发电日时组的day不相同，
				 * 主要是要排除相同的情况下，月份不能减。
				 */
				cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			}
			cal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(HHmm.substring(0, 2))+addHours);
			cal.set(Calendar.MINUTE, Integer.parseInt(HHmm.substring(2, 4)));	
			return cal.getTime();			
		}
		
		/**
		 * 返回一个航班的估计执行日期
		 * @param telexDate 报文保存时间
		 * @param dateGroup 电报日时组
		 * @return 航班执行时间
		 */
		public static Date getExecDate(Date telexDate,String dateGroup){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(telexDate);
			//取得日期
			int telexDay = cal.get(Calendar.DATE);
			//取得上个月最后一天
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			cal.set(Calendar.DATE, 1);
			int preLastDay = cal.getActualMaximum(Calendar.DATE);
			//恢复月份,日期
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
			cal.set(Calendar.DATE, telexDay);
			
			//取得发电日时组的日期和小时
			int grpDay = Integer.parseInt(dateGroup.substring(0, 2));
			int grpHour = Integer.parseInt(dateGroup.substring(2, 4));
			
			if (((grpDay != telexDay) &&(grpDay == preLastDay)
					&&(telexDay == 1))||grpDay > telexDay){
				/**
				 * 如果发电日时组的day是保存电报时间上一个月的月底
				 * 而且保存电报的day与发电日时组的day不相同，
				 * 主要是要排除相同的情况下，月份不能减。
				 */
				cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
			}
			
			cal.set(Calendar.DATE, grpDay);
			cal.set(Calendar.HOUR_OF_DAY, grpHour+addHours);
    		cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND, 0);
			return addToDay(cal.getTime(),0);
			
		}
		
		
		
		/**
		 * 返回航班由可能的第二日期
		 * @param telexDate 电报保存时间
		 * @param dateGroup 电报日时组
		 * @return 航班可能的第二个日期
		 */
		public static Date getSupplyDate(Date telexDate,String dateGroup){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(telexDate);
			//取得日期
			int telexDay = cal.get(Calendar.DATE);
			//恢复月份
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
			//取得发电日时组的日期和小时
			int grpDay = Integer.parseInt(dateGroup.substring(0, 2));
			int grpHour = Integer.parseInt(dateGroup.substring(2, 4));
			
			if ((telexDay != grpDay)&&(grpHour<16)){

				return getExecDate(telexDate, dateGroup);
				
			}else if ((telexDay != grpDay)&&(grpHour>=16)){
				
				return addToDay(getExecDate(telexDate, dateGroup), -1);
				
			}else if ((telexDay == grpDay)&&(grpHour<16)){
				
				return getExecDate(telexDate, dateGroup);
				
			}else if ((telexDay == grpDay)&&(grpHour>=16)){
				
				return addToDay(getExecDate(telexDate, dateGroup),-1);
			}else {
				return null;
			}
	
			
		}
		
		/**
		 * 将输入的日期转换为格式为年月日时分的字符串
		 * @param time	   例如 2016-01-01 00:00
		 * @return 返回      例如  201601010000
		 */
		public static String TimeToATMTime(Date time){
			String returnStr="";
			if (time != null){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
				returnStr = sdf.format(time);
			}
			return returnStr;
		}
		
		/**
		 * 将输入的日期转换为格式为年月日的字符串
		 */

		public static String DateToATMDate(Date date){
			String returnStr="";
			if (date != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				returnStr = sdf.format(date);
			}
			
			return returnStr;
		}
		
		
		/**
		 * 
		 * @Description: 根据日期，返回对应的星期几
		 * @param date  日期
		 * @return 对应的星期几
		 */
		public static Week getWeek(Date date){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-2;
			//转换星期 ，应为定义从星期一开始下标从0开始，Calendar.DAY_OF_WEEK从礼拜天开始，下标从1开始
			if (dayOfWeek  == -1){ 
				dayOfWeek =6;
			}
			return Week.values()[dayOfWeek];
			
		}
		
		/**
		 * 
		 * @Description: 根据给定的日期，返回对应的该月的第一天
		 * @param date 给定的日期
		 * @return 返回对应的该月的第一天
		 */
		public static Date getMonthFristDate(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			return addToDay(cal.getTime(),0);
		}
		
		
		/**
		 * 
		 * @Description: 根据给定的日期，返回对应的该月的最后一天
		 * @param date   给定的日期
		 * @return       返回对应的该月的最后一天
		 */
		public static Date getMonthLastDate(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			return addToDay(cal.getTime(),0);
		}
		
		/**
		 * 
		 * @Description:根据给定的日期，返回对应的该年的第一天
		 * @param date 给定的日期
		 * @return 对应的该年的第一天
		 */
		public static Date getYearFristDate(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			return cal.getTime();
		}
}
