package com.lzairport.ais.comm;

import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.lzairport.ais.exception.SerialConnectionException;


/**
 * 串口连接的实现类。<p>
 * 实现打开串口，并传入实际处理串口操作的SerialHandle类
 * @author ZhangYu
 * @version 0.9b 11/30/12
 * @since JDK 1.6
 * @see ISerialHandle
 *
 */


@Component
public class SerialConnection implements SerialPortEventListener	{ 

	@Resource
	private ISerialHandle serialHandle;
	@Resource
	private SerialParameters parameters;
	private InputStream is;
	private CommPortIdentifier portId;
	private SerialPort sPort;
	private boolean open;
	
	/**
	 * 类的构造函数，对串口连接操作所需要的类进行赋值。
	 * @param serialHandle 对串口数据操作实际的类
	 * @param parameters 串口参数
	 */

	public SerialConnection() {
		super();
		open = false;
	}
	
	
	

	public void init() throws SerialConnectionException{
		this.openConnection();
		
	}
	
 

	/**
	 * @return the serialHandle
	 */
	public ISerialHandle getSerialHandle() {
		return serialHandle;
	}





	/**
     * 根据parameters对象设置串口连接参数，如果失败则恢复原先的串口连接参数设置并抛出异常
     */
    public void setConnectionParameters() throws SerialConnectionException {

    	// 保存原先的设置
    	int oldBaudRate = sPort.getBaudRate();
    	int oldDatabits = sPort.getDataBits();
    	int oldStopbits = sPort.getStopBits();
    	int oldParity   = sPort.getParity();

    	// 	设置串口连接参数，如果失败则恢复原先的串口连接参数设置
    	try {
    		sPort.setSerialPortParams(parameters.getBaudRate(),
				      parameters.getDatabits(),
				      parameters.getStopbits(),
				      parameters.getParity());
    	} catch (UnsupportedCommOperationException e) {
    		parameters.setBaudRate(oldBaudRate);
    		parameters.setDatabits(oldDatabits);
    		parameters.setStopbits(oldStopbits);
    		parameters.setParity(oldParity);
    		throw new SerialConnectionException("Unsupported parameter");
    	}

    	// 设置 flow control.
    	try {
    		sPort.setFlowControlMode(parameters.getFlowControlIn() 
			           | parameters.getFlowControlOut());
    	} catch (UnsupportedCommOperationException e) {
    		throw new SerialConnectionException("Unsupported flow control");
    	}
    }


    /**
     * 
     * 尝试用parameters中的参数打开一个串口连接，设置serialHandle的os(串口输出IO)
     *在打开串口的每一步中如果不成功，则关闭串口并抛出<code>SerialConnectionException</code>异常
     *如果操作的串口被其他应用程序占用等候30秒。
     * @throws SerialConnectionException 
     *  
     */
	public void openConnection() throws SerialConnectionException	{
				
		// 获取一个parameters中的指定端口的CommPortIdentifier的对象
		try {
		    portId = 
			 CommPortIdentifier.getPortIdentifier(parameters.getPortName());
		} catch (NoSuchPortException e) {
		    throw new SerialConnectionException("NoSuchPort");
		}	

		//由CommPortIdentifier对象打开串口端口
		try {
		    sPort = (SerialPort)portId.open("柳州机场电报处理系统", 30000);
		} catch (PortInUseException e) {
		    throw new SerialConnectionException("PortInUse");
		}
		
		//设置串口连接参数
		try {
		    setConnectionParameters();
		} catch (SerialConnectionException e) {	
		    sPort.close();
		    throw e;
		}

		// 打开串口的输入输出流，如果错误，关闭串口连接并抛出异常
		try {
		    serialHandle.setOs(sPort.getOutputStream());
		    is = sPort.getInputStream();
		} catch (IOException e) {
		    sPort.close();
		    throw new SerialConnectionException("Error opening i/o streams");
		}
		
		//加入串口事件监听器
		try {
		    sPort.addEventListener(this);
		} catch (TooManyListenersException e) {
		    sPort.close();
		    throw new SerialConnectionException("too many listeners added");
		}
		

		// 设置设置串口有数据的事件为真
		sPort.notifyOnDataAvailable(true);
	    try {
			sPort.enableReceiveTimeout(30);
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		open = true;
	}
	
	/**
	 * 关闭串口
	 */
	
    public void closeConnection() {
    	if (!open) {
    		return;
    	}


    	if (sPort != null) {
    		try {
    			// 	关闭输入输出流
    			serialHandle.getOs().close();
    			is.close();
    		} catch (IOException e) {
    			System.err.println(e);
    		}
    		// 关闭端口
    		sPort.close();
    	    
    	}
    	open = false;
    }
    

    /**
     * 向串口发送数据
     * @param serialData
     */
    public void sendSerialData(String serialData){
    	try {
			serialHandle.OutDataHandle(serialData);
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	
	

    /**
     * 处理串口的通信事件
     * @throws SerialConnectionException
     * @see SerialPortEvent
     */
	@Override
	public void serialEvent(SerialPortEvent ev) {
	 	// 创建一个接受字符串缓冲
		StringBuffer inputBuffer = new StringBuffer();
		int newData = 0;

		switch (ev.getEventType()) {
		    //串口数据到达事件
			case SerialPortEvent.DATA_AVAILABLE:
			    while (newData != -1) {
			    	try {
			    	    newData = is.read();
				    if (newData == -1) {
				    	break;
				    }
			    	inputBuffer.append((char)newData);
			    	} catch (IOException ex) {
			    	//	throw new SerialConnectionException("IO错误");
			      	}
	   		    }
			//serialHandle类来处理串口缓冲区的数据
			    try {
			    	serialHandle.InDataHandle(new String(inputBuffer));
			    } catch (Exception e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    }
			    break;

			// 通讯中断事件
		    case SerialPortEvent.BI:
		    //	throw new SerialConnectionException("通讯中断");
		   
		    //载波检测事件	
		    case SerialPortEvent.CD:
		    	break;
		    	
		    //清除发送事件
		    case SerialPortEvent.CTS:
		    	break;
		    	
		    //数据设备准备好事件
		    case SerialPortEvent.DSR:
		    	break;
		    	
		    //帧错误事件	
		    case SerialPortEvent.FE:
		    	break;
		    	
		    //溢位错误	
		    case SerialPortEvent.OE:
		    	break;
		    	
		    //输出缓冲区已清空	
		    case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
		    	break;
		    	
		    //奇偶校验错
		    case SerialPortEvent.PE:
		    	break;
		    	
		    //振铃指示
		    case SerialPortEvent.RI:
		    	break;
 	
		    	
		}

	}

}
