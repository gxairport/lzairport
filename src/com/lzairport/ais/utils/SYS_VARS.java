package com.lzairport.ais.utils;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.springframework.context.support.AbstractApplicationContext;

import com.lzairport.ais.service.IService;

/**
 * 定义系统扩展字符串及显示参数设定
 * @author ZhangYu
 * @version 0.9a 28/06/14
 * @since JDK 1.6
 */

public class SYS_VARS {
	
	
	//实体类所对应的表格颜色处理类的关系，一一对应
	
	/**
	 *  表格颜色实体类的集合
	 */
	public final static List<String> RGBInClassName  =Arrays.asList(
			"com.lzairport.ais.models.telex.TelexBO", 
			"com.lzairport.ais.models.aodb.DynFlight",
			"com.lzairport.ais.models.aodb.PlnFlight"
			);
	
	/**
	 *  表格颜色实体类对应的TableColor的集合
	 */
	public final static List<String> RGBOutClassName  =   Arrays.asList(
			"com.lzairport.ais.tablecolor.TelexBkColor",
			"com.lzairport.ais.tablecolor.FlightBkColor",
			"com.lzairport.ais.tablecolor.FlightBkColor"
			);
	
	
	
	//关联实体类，一一对应
	/**
	 *  实体类数组
	 */
	public final static List<String> ModelName  =Arrays.asList(
			"com.lzairport.ais.models.TelexBO",
			"com.lzairport.ais.models.aodb.FlightState"
			);
	
	
	/**
	 * 对应的关联实体类数组
	 */
	public final static List<String> RelationModelName  =Arrays.asList(
			"com.lzairport.ais.models.TelexBO",
			"com.lzairport.ais.models.aodb.DynFlight"
			);

	
	 //首选项颜色设置参数中List字段名
	/**
	  *  分析类型颜色字段名   
	  */
	public final static String AnalyticyTypeColorFieldName="name";
	
	/**
	 *  电报类型颜色字段名
	 */
	public final static String TelexTypeColorFieldName="name";

	
    //  首选项颜色设置的标志位
     
	/**
	 *  背景字体颜色标志
	 */
	public final static int brfrColorSet = 0;
	
	/**
	 *  单元格字体颜色标志
	 */
	public final static int cellColorSet = 1;
	
	/**
	 *  所有颜色标志
	 */
	public final static int allColorSet =2 ;
   
	/**
	 *  编辑对话框每列最多的控件数
	 */
	public final static int RowMaxNum = 10;

	/**
	 *  实体类的头
	 */
	public static String ModelClassHeader="com.lzairport.ais.models.";
	
	
	//  查找定义的逻辑运算符
	public static String LinkSqlNull = "";
	
	public static String LinkSqlAnd  = "AND";
	
	public static String LinkSqlOr   = "OR";
	
	public static String Oper_Like   = "LIKE";
	
	public static String Oper_Is     = "IS";
	
	public static String SqlOrderBy="Order By";
	
	public static List<String> LinkSqlLev = Arrays.asList("(",LinkSqlOr,LinkSqlAnd);
	
	public static List<String> Operations= Arrays.asList(">","<",">=","<=","<>","=",Oper_Like,Oper_Is);

	public static List<String> OperationsCN= Arrays.asList("大于","小于","大于等于","小于等于","不等于","等于","包含");
	
	public static String Aggregation_Field ="Field";

	
	/**
	 *  本地机场三字代码
	 */
	public static String LocalAirportThreeCode="LZH";
	
	/**
	 * 本地机场中文名
	 */
	public static String LocalAirportCn="柳州";

	/**
	 *  本地机场四字代码
	 */
	public static String LocalAirportFourCode="ZGZH";
	
	/**
	 *  航班延误提醒次数
	 */
	
	public static int FlightDelayTimes =2;
	
	/**
	 * 本场航班发送FPL提示次数
	 */
	public static int FlightFPLTimes = 1;
	
	/**
	 * 本场航班发送FPL提醒的距离航班计划起飞的秒数
	 * <p>一个小时
	 */
	public static int FlightFPLSecond = -60*60*1000;
	
	/**
	 *  升序
	 */
	public static String AscSORT="ASC";
	
	
	/**
	 * 降序
	 */
	public static String DescSORT="DESC";
	
	/**
	 * 
	 * 载量信息导入状态
	 *
	 */
	
	public enum LoadState {  
		NotInmport,PlnImported,Imported
      }  	
	
	public static List<String> LoadStateCn = 
			Arrays.asList("未导入","预导入","已导入");
	/**
	 * ViewConfig的显示类型
	 */
	public static String  ViewItemType_Static = "Static";
	public static String  ViewItemType_Dyniamic = "Dyniamic";
	public static String  ViewItemType_Enum = "Enum" ;
	public static String  ViewItemType_Num = "Number" ;
	public static String  ViewItemType_Bool ="Bool";
	
	public static List<String> ViewItemTypeCn = 
			Arrays.asList("静态","动态","枚举","序号");

	public static String[] ViewItemType = 
			{ViewItemType_Static,ViewItemType_Dyniamic,ViewItemType_Enum,
					ViewItemType_Num,ViewItemType_Bool};
	

	public enum SizeType {  
		Big,Mid,Small
      }  
	
	
	/**
	 *  进行聚合查询时用来标记年，月，日的枚举类型
	 */
	public enum GrpDate{
		Year,Month,Day
	}
	
	
	public enum  SummaryType{
		Sum,Avg,None
	}

	public static List<String> SizeTypeCn = 
			Arrays.asList("重型","中型","小型");
	
    /**
     * 执行周期
     */
	public enum Week {  
        Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday  
      }  
	
	/**
	 *   付费旅客类型 
	 */
	public enum  PayingServiceType{
		Gold,silver,FClass
	}
	
	public static List<String> PayingServiceTypeCn = 
			Arrays.asList("金卡","银卡","头等舱");
    
    /**
     * 班期计划类型
     */
	public enum Quarter{
		WinterSpring,SummerAutumn
    }
	
	public static List<String> QuarterCn = Arrays.asList("冬春","夏秋");
	
	
	//代表聚合字段的类型，求和，平均数,计数，表达式,计算（查询完根据结果）
	public enum AggregationFieldType {
		Sum,Avg,Count,None
	}
	
	
	/**
	 *   服务环节的类型 
	 */
	public enum DisPatchItemType{
		End,Start_End,Start_End_Times
	}
	
	public static List<String> DisPatchItemTypeCn 
	= Arrays.asList("结束","开始/结束","开始/结束/次数");
	
	
	
	
	
	
	
	/**
	 * 
	 * 进出港航班的类型
	 *
	 */
	public enum OutIn{
		Dep,Arr,Srd
	}
	
	public enum RouteType{
		Dom,Dom_Tra,Int,Int_Tra,Reg,Reg_Tra
	}
	
	public enum DiscountStyle{
		direct,Range
	}

	/**
	 * 进出港航班对应的List
	 */
	public static List<String> RouteTypeCn = Arrays.asList("国内","国内中转","国际","国际中转","地区","地区中转");
	
	
	/**
	 * 进出港航班对应的List
	 */
	public static List<String> OutInCn = Arrays.asList("出港","进港","本场");
	
	/**
	 * 进出港航班对应的List Code
	 */
	public static List<String> OutInCode = Arrays.asList("Dep","Arr","Srd");
	
	public enum ConverterFlag{
		PO2VO,VO2PO
	}
	
	
	/**
	 *  代表关联航班是昨日的进港航班---昨日进港
	 */
	public static String LinkFlightDep = "昨日相关停场";
	
	/**
	 *  代表关联是不属于本场的航班-----进港开始
	 */
	public static String LinkFlightArr = "航线开始";
	
	
	
	public static String SysTemStartDate="01JAN14";
	
	
	
	public static Shell  emptyShell = null;
	
	/**
	 * 获取field名的Servce
	 * @param ctx  Srping容器的Ctx
	 * @param field 字段名
	 * @return 对应的Service
	 */


	@SuppressWarnings("unchecked")
	public static IService<Integer,? extends Object> getFieldService(AbstractApplicationContext ctx,Class<?> clazz,String field){
		
		String fieldClassName = ObjectMethodUtil.getFieldType(clazz,field).getSimpleName();
		fieldClassName = fieldClassName.substring(0, 1).toLowerCase()+fieldClassName.substring(1);
		String ServiceName = fieldClassName+"Service";
		return (IService<Integer, ? extends Object>) ctx.getBean(ServiceName);
		
	}

	
}
