package com.lzairport.ais.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import com.lzairport.ais.utils.SYS_VARS.GrpDate;

/**
 * 
 * FileName      ExpresstionUtil.java
 * @Description  JPA 安全表达式工具类
 * @author       ZhangYu    Company:    LZAirport
 * @version      V0.9a CreateDate: 2016年2月13日 
 * @ModificationHistory
 * Date         Author     Version   Discription
 * <p>---------------------------------------------
 * <p>2016年2月13日      Yu    1.0        1.0
 * <p>Why & What is modified: <修改原因描述>
 */

@SuppressWarnings("rawtypes")
public class ExpresstionUtil {
	
private static int StartYearPos = 1;
	
	/**
	 *  关于日期字符位置的常量 
	 */
	private static int StartMonthPos = 6;
	
	private static int StartDayPos = 6;
	
	private static int Yearlen = 4;
	
	private static int MonthLen = 2;
	
	private static int DayLen = 5;
	
	private CriteriaBuilder cb;
	
	/**
	 *  操作符对比动作
	 */
	private static String CALC = "calc";
	
	private static String POP = "pop";
	
	private static String PUSH = "push";
	
	private static String ERROR ="err";

	
	/**
	 *   优先级定义表
	 */
	private Map<String, Map<String, String>> priorityMap = new HashMap<String, Map<String, String>>();
	
	private List<String> opts = Arrays.asList(">","<",">=","<=","<>","=","LIKE","IS","+","-","OR","AND","(",")","#");
	
	/**
	 * 存放条件表达式的栈
	 */
	private Stack<Object> dataStack = new Stack<Object>();
	
	/**
	 * 存放操作符的栈
	 */
	private Stack<String> optStack = new Stack<String>();
	
	
	

	
	/**
	 * 
	 * @Description: 初始化优先级定义表
	 * (OR)<(AND)<(>,<,>=,<=,<>,LIKE)<(())
	 */
	private void initPriorityMap(){
      	  //>
		Map<String, String> subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("=", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put(">", subMap);
		  //<
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("<", subMap);
		  
		  //>=
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put(">=", subMap);
		  
		  //<=
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("<=", subMap);
		  //<>
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("<>", subMap);
		  
		  //=
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("=", subMap);
		  		  
		  //LIKE
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("LIKE", subMap);

		  //IS
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("IS", subMap);
		  
		  //+
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", CALC);
		  subMap.put("-", CALC);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("+", subMap);
		  
		  //-
		  subMap = new HashMap<String, String>();
		  subMap.put(">", CALC);
		  subMap.put("<", CALC);
		  subMap.put(">=", CALC);
		  subMap.put("<=", CALC);
		  subMap.put("=", CALC);
		  subMap.put("<>", CALC);
		  subMap.put("LIKE", CALC);
		  subMap.put("IS", CALC);
		  subMap.put("+", CALC);
		  subMap.put("-", CALC);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("-", subMap);		 
		  
		  //OR
		  subMap = new HashMap<String, String>();
		  subMap.put(">", PUSH);
		  subMap.put("<", PUSH);
		  subMap.put(">=", PUSH);
		  subMap.put("<=", PUSH);
		  subMap.put("=", PUSH);
		  subMap.put("<>", PUSH);
		  subMap.put("LIKE", PUSH);
		  subMap.put("IS", PUSH);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", PUSH);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("OR", subMap);
		  
		  //AND
		  subMap = new HashMap<String, String>();
		  subMap.put(">", PUSH);
		  subMap.put("<", PUSH);
		  subMap.put(">=", PUSH);
		  subMap.put("<=", PUSH);
		  subMap.put("=", PUSH);
		  subMap.put("<>", PUSH);
		  subMap.put("LIKE", PUSH);
		  subMap.put("IS", PUSH);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", CALC);
		  subMap.put("AND", CALC);
		  subMap.put("(", PUSH);
		  subMap.put(")", CALC);
		  subMap.put("#", CALC);
		  priorityMap.put("AND", subMap);
		  //(
		  subMap = new HashMap<String, String>();
		  subMap.put(">", PUSH);
		  subMap.put("<", PUSH);
		  subMap.put(">=", PUSH);
		  subMap.put("<=", PUSH);
		  subMap.put("=", PUSH);
		  subMap.put("<>", PUSH);
		  subMap.put("LIKE", PUSH);
		  subMap.put("IS", PUSH);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", PUSH);
		  subMap.put("AND", PUSH);
		  subMap.put("(", PUSH);
		  subMap.put(")", POP);
		  subMap.put("#", ERROR);
		  priorityMap.put("(", subMap);
		  
		  //) 不可能被放入栈中，一定会被丢弃，因为前面的数据一定会被计算
		  subMap = new HashMap<String, String>();
		  subMap.put(">", ERROR);
		  subMap.put("<", ERROR);
		  subMap.put(">=", ERROR);
		  subMap.put("<=", ERROR);
		  subMap.put("<>", ERROR);
		  subMap.put("=", ERROR);
		  subMap.put("LIKE",ERROR);
		  subMap.put("IS", ERROR);
		  subMap.put("+", ERROR);
		  subMap.put("-", ERROR);
		  subMap.put("OR", ERROR);
		  subMap.put("AND", ERROR);
		  subMap.put("(", ERROR);
		  subMap.put(")", ERROR);
		  subMap.put("#", ERROR);
		  priorityMap.put(")", subMap);
		  //# 结束和开始字符
		  subMap = new HashMap<String, String>();
		  subMap.put(">", PUSH);
		  subMap.put("<", PUSH);
		  subMap.put(">=", PUSH);
		  subMap.put("<=", PUSH);
		  subMap.put("=", PUSH);
		  subMap.put("<>", PUSH);
		  subMap.put("LIKE", PUSH);
		  subMap.put("IS", PUSH);
		  subMap.put("+", PUSH);
		  subMap.put("-", PUSH);
		  subMap.put("OR", PUSH);
		  subMap.put("AND", PUSH);
		  subMap.put("(", PUSH);
		  subMap.put(")", ERROR);
		  subMap.put("#", POP);
		  priorityMap.put("#", subMap);
		 
	}
	
	public ExpresstionUtil(CriteriaBuilder cb) {
		super();
		this.cb = cb;
		initPriorityMap();
	}
	
	private boolean isOpt(Object Token){
		if (opts.indexOf(Token) == -1){
			return false;
		}else{
			return true;
		}
	}
	
	


	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param data1
	 * @param data2
	 * @param opt
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked",  })
	private Object compose(Object data1,Object data2,String opt) throws Exception{
		
		
		Object result = null;
		Object value = null;
		Predicate predicate1=null;
		Predicate predicate2=null;
		Expression expression1 = null;
		Expression expression2 = null;
			
		if (data1 instanceof Predicate && data2 instanceof Predicate){
			predicate1 = (Predicate) data1;
			predicate2 = (Predicate) data2;
		}else if (data1 instanceof Expression && data2 instanceof Expression){
			expression1 = (Expression) data1;
			expression2 = (Expression) data2;
		}else if (data2 instanceof Expression){
			expression1 = (Expression) data2;
			value = data1;
		}else {
			
			throw new Exception("栈内参数出错，无法进行合成");
		}
		
		if (opt.equals(">") && value instanceof Comparable && expression1!= null){
			//大于处理方式
			result = cb.greaterThan(expression1, (Comparable)value);
		}else if (opt.equals(">=")&&value instanceof Comparable && expression1!= null){
			//大等于处理方式
			result = cb.greaterThanOrEqualTo(expression1, (Comparable)value);
		}else if (opt.equals("<") && value instanceof Comparable && expression1!= null){
			//小于处理方式
			result = cb.lessThan(expression1, (Comparable)value);
		}else if (opt.equals("<=")&&value instanceof Comparable && expression1!= null){
			//小于等于处理方式
			result = cb.lessThanOrEqualTo(expression1, (Comparable)value);
			//等于处理方式
		}else if (opt.equals("=") && expression1!= null){
			result = cb.equal(expression1, value);
		}else if (opt.equals("<>") && expression1!= null){
			//不等于处理方式
			result = cb.notEqual(expression1, value);
		}else if (opt.equals(SYS_VARS.Oper_Like) && expression1!= null){
			//LIKE处理方式
			result = cb.like(expression1, value.toString());
		}else if (opt.equals(SYS_VARS.Oper_Is) && expression1!= null){
			//IS处理方式
			result = cb.isNull(expression1);
			
		}else if (opt.equals("AND")&&predicate1 != null&&predicate2 != null){
			//And处理
			result = cb.and(predicate1, predicate2);
		}else if (opt.equals("OR")&&predicate1 != null&&predicate2 != null){
			//OR处理
			result = cb.or(predicate1, predicate2);
		}else if (opt.equals("+")&&expression1 != null&&expression2 != null){
			//加处理方式
			result = cb.sum(expression1, expression2);
		}else if (opt.equals("-")&&expression1 != null&&expression2 != null){
			//减处理方式
			result = cb.diff(expression1,expression2);
		}else{
			throw new Exception("字段合成表达式出现错误");			
		}
		
		return result;
		
		
		
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param expresstion
	 * @throws Exception 
	 */
	private void process(Object token) throws Exception{
		while (!optStack.peek().equals("#")||!"#".equals(token)){
			//如果操作符栈顶为#，表达式传入对象为#，说明已经处理完表达式，跳出循环
			if (!isOpt(token)){
				//表达式不是操作符，入栈，跳出循环，进行下一个表达式的处理
				dataStack.push(token);
				break;
			}else if (optStack.size() > 0){
				String opt = (String) token; 
				String priority = priorityMap.get(optStack.peek()).get(opt);
				if (PUSH.equals(priority)){
					//如果操作符的等级比栈内的操作符的等级高，将此操作符入栈，并跳出循环
					optStack.push(opt);
					break;
				} else if (POP.equals(priority)){
					//说明是一对括号，中间的数据已经处理完毕，跳出循环，丢弃括号
					optStack.pop();
					break;
				}else if (CALC.equals(priority)){
					//说明操作符等级比栈内的低，处理栈内的数据运算，直到跳出循环
					if (dataStack.size() >=2){
						Object obj = compose(dataStack.pop(), dataStack.pop(), optStack.pop());
						dataStack.push(obj);
					}else{
						throw new Exception("数据栈内数据不足，请检查表达式");	
					}
				}else{
					
					throw new Exception("表达式出错，请检查表达式");	
				}
			}else {
				throw new Exception("操作符栈内数据不足，请检查表达式");	
			}
			
		}
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param expresstions
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T>T composeExpression(Object[] expresstions) throws Exception{
		optStack.push("#");
		for (Object token:expresstions){
			process(token);
		}
		process("#");
		return (T) dataStack.pop();
		
	}
	
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ymd
	 * @param attrib
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public Expression getYMDExpression(GrpDate ymd,Expression expression){
		switch (ymd) {
		case Year:
			return 	cb.substring(expression,StartYearPos, Yearlen);
		case Month:
			return  cb.substring(expression,StartMonthPos,MonthLen);
		case Day:
			return  cb.substring(expression,StartDayPos,DayLen);
		default:
			return null;
		}
	}

}
