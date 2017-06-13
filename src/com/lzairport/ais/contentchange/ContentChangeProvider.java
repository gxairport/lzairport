package com.lzairport.ais.contentchange;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


/**
 * IContentChangeProvider接口的实现类
 * 用于通知注册在自身的Listener内容改变
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since JDK 1.6
 *
 */

public class ContentChangeProvider implements IContentChangeProvider {

	private ArrayList<IContentChangeListener > listeners = new ArrayList<IContentChangeListener >();

	@Override
	public void addContentChangeListener(IContentChangeListener listener) {
		
		if (!listeners.contains(listener)){
			listeners.add(listener);
		}
		
	}

	@Override
	public void removeContentChangeListener(IContentChangeListener listener) {

		if (listeners.contains(listener)){
			listeners.remove(listener);
		}
		
	}

	@Override
	public void contentChangeInvoke(String property, Object newValue ) {

		for (IContentChangeListener  listener:listeners){
			try {
				listener.contentChange(new PropertyChangeEvent(this, property, null, newValue));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	
	

}
