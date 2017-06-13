package com.lzairport.ais.exception;



/**
 * 航班事件出来抛出的异常类
 * @author ZhangYu
 * @version 0.9b 12/11/14
 * @since JDK1.6
 *
 */


public class FlightServiceException extends Exception {

	/**
	 * 不带错误信息的构造方法
	 */
	private static final long serialVersionUID = 1L;

	public FlightServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 带错误信息的构造方法
	 * @param message
	 */
	public FlightServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
