package com.lzairport.ais.comm;


import java.io.OutputStream;

import com.lzairport.ais.exception.SerialConnectionException;

/**
 * 用于处理串口数据的抽像接口,在SerialConnection的构造函数中传入.<p>
 * 用做实际上处理串口输入数据和输出数据的接口.
 * @author ZhangYu
 * @version 0.9b 11/30/12
 * @since JDK 1.6
 * @see SerialConnection
 */

public interface ISerialHandle {
	
	

	/**
	 * 用于输入串口数据处理的方法
	 * @param InData
	 */

	public abstract void InDataHandle(String InData) throws  Exception;


	/**
	 * 用于输出串口数据处理得方法
	 * @param OutData
	 * @throws SerialConnectionException
	 * @throws Exception 
	 */
	public abstract void OutDataHandle(String OutData)
			throws Exception;

	/**
	 * 取得串口输出缓冲流的IO
	 * @return the os
	 */
	public abstract OutputStream getOs();

	/**
	 * 
	 * 设置串口输出缓冲流的IO,在SerialConnection进行赋值
	 * @param os the os to set
	 */
	public abstract void setOs(OutputStream os);

}