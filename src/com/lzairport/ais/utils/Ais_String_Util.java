package com.lzairport.ais.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串处理得工具类
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */

public class Ais_String_Util {
	
	public static String SplitChar="|";

	/**
	 * 
	 * @Description: 将字符串分解，按照传入的分解字符
	 * @param content
	 * @param splitChar
	 * @return
	 */
	public static List<String> SplitField(String content,String splitChar){
		List<String> listStrs = new ArrayList<String>();
		Pattern pat = Pattern.compile("[^"+splitChar+"]+");   
		Matcher mat = pat.matcher(content);  
		while (mat.find()){
			listStrs.add(mat.group());
		}
		return listStrs;
	}
	
	
	
	/**
	 * 将一个String以|为分隔符拆分成一个个的字符串
	 * @param Content 输入字符串
	 * @return List 字符串集合
	 */
	public static List<String> SplitField(String Content){
		List<String> listStrs = new ArrayList<String>();
		Pattern pat = Pattern.compile("[^\\|]+");   
		Matcher mat = pat.matcher(Content);  
		while (mat.find()){
			listStrs.add(mat.group());
		}
		return listStrs;
	}
	
	
	/**
	 * 将一个String以空格为分隔符拆分成一个个的字符串
	 * @param Content 输入字符串
	 * @return List 字符串集合
	 */
	public static List<String> SplitField_Spc(String Content){
		List<String> listStrs = new ArrayList<String>();
		Pattern pat = Pattern.compile("[^\\s]+");   
		Matcher mat = pat.matcher(Content);  
		while (mat.find()){
			listStrs.add(mat.group());
		}
		return listStrs;
	}
	
	/**
	 * 将一个String以"\"为分隔符拆分成一个个的字符串
	 * @param Content
	 * @return
	 */
	public static List<String> SplitSubEname(String Content){
		return SplitField(Content,"/");
	}

	/**
	 * 将一个String以换行为分隔符分成一行行的电报行
	 * @param Content 输入字符串
	 * @return List 字符串集合
	 */
	public static List<String> SplitLine(String Content){
		List<String> listStrs = new ArrayList<String>();
		Pattern pat = Pattern.compile(".+");   
		Matcher mat = pat.matcher(Content);  
		while (mat.find()){
			listStrs.add(mat.group());
		}
		return listStrs;
	}
	
	/**
	 * 利用正则表达式来判断整个字符串是否符合规则，要求全部匹配	
	 * @param regEx 规则字符串
	 * @param str 需要判断的字符串
	 * @return 真假
	 */
	public static boolean PatternIsMatch(String regEx,String str){
		Pattern pat = Pattern.compile(regEx);   
		Matcher mat = pat.matcher(str);  
		return mat.matches();
	}
	
	/**
	 * 利用正则表达式来寻找符合规则的字符串，并返回，不要求全部匹配
	 * @param regEx 规则字符串
	 * @param str 需要判断的字符串
	 * @return
	 */
	
	public static String PatternFind(String regEx,String str){
		Pattern pat = Pattern.compile(regEx);   
		Matcher mat = pat.matcher(str);  
		if (mat.find()){

			return mat.group();
		}
		else {
			return null;
		}
	}
	
	/**
	 * 判断一个字符串是否是数字
	 * @param str 字符串
	 * @return 是否
	 */
	public static boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
	}
	
	/**
	 * 
	 * @Description: 判断一个字符串的最后一个是否为奇数
	 * @param str 字符串
	 * @return 是否为奇数
	 */
	public static boolean lastCharIsOdd(String str){
		String lastNum = str.substring(str.length()-1);
		if (Character.isDigit(lastNum.charAt(0))){
			if (Integer.valueOf(lastNum) %2 !=0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	
	
 
}
