package com.lzairport.ais.tableviewer.provider;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import com.lzairport.ais.contentchange.*;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.jms.ChangeEntityInfo;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.models.aodb.BaseFlight;
import com.lzairport.ais.tableviewer.base.TableViewerService;
import com.lzairport.ais.utils.EventCodeUtil;


/**
 * 表格的ContentProvider抽象类，联系实体，如果实体发生变化，则自动改变内容
 * @author ZhangYu
 * version 0.9a 28/06/14
 * @since JDK 1.6
 * 
 */


public  abstract class  ViewerContentProvider implements IStructuredContentProvider,IContentChangeListener{


	/**
	 * 出现取回数据为null值的情况尝试的次数
	 */
	private final int MaxTryTimes = 15;
	
	/**
	 *  等待数据进入数据库的时间
	 */
	private final int WaitTime =100;
	
	
	private TableViewerService viewerService;
	
	private static QueryConditions  conditions= new  QueryConditions();


	/**
	 * 新增操作
	 */
	protected abstract  void add(Object entity); 
	
	/**
	 * 删除操作
	 */
	protected abstract void remove(Object entity); 
	
	/**
	 * 更新操作
	 */
	protected abstract void update(Object entity);
	
	/**
	 * 刷新操作
	 */
	protected abstract void refresh();
	
	
	/**
	 * 出错的提示
	 */
	protected abstract void showErrMsg(String msg);

	


	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * 等待一段时间的方法
	 */
	private void waitForRefresh(){
		
		synchronized (this) {
			try {
				wait(WaitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	/**
	 *   如果输入数据发生变化所对应的操作
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if (newInput instanceof TableViewerService) {
			viewerService = (TableViewerService) newInput;
		}
	}

	/**
	 * 获取数据对象数组
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		
		if (inputElement instanceof TableViewerService){
			//如果条件为空则取全部数据，如果条件不为空则取符合条件的数据集合
			TableViewerService viewerService = (TableViewerService) inputElement;
			if (viewerService.getConditions() != null){
				return viewerService.getService().findByConditionAll(viewerService.getConditions()).toArray();
			}else{
				return viewerService.getService().getAll().toArray();
			}
			 
		} else if (inputElement instanceof List){
			//如果是List，直接转换为数组
			return ((List<?>) inputElement).toArray();
			
		}
		return null;
		
	}


	/**
	 *  数据内容改变时将触发，
	 *  根据传入的property参数 选择相应的处理
	 */
	@Override
	public void contentChange(PropertyChangeEvent evt) {
		
		if (viewerService != null){
			
			String property = evt.getPropertyName();
			ChangeEntityInfo entityInfo = (ChangeEntityInfo) evt.getNewValue();
			//如果处理的实体类与记录改变实体相同
			if (viewerService.getService().getModelClass().equals(entityInfo.getClazz())){
				
				Object  obj = null;
				
				if (property.equals(EventCodeUtil.ModelsRemove)){
					//如果是实体删除事件，新建一个相关实体，并设置ID
					try {
						
						obj = entityInfo.getClazz().newInstance();
						((DefaultEntity)obj).setId(entityInfo.getId());
						
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}else{
					//否则查找id一样的数据，ManyToOne和OneToMany与Viewer保持一致
					conditions.setExpresstion(new Object[]{DefaultEntity.ID,"=",entityInfo.getId()});
					if (viewerService.getConditions() != null){
						conditions.setFetchManyToOne(viewerService.getConditions().getFetchManyToOne());
						conditions.setFetchOneToMany(viewerService.getConditions().getFetchOneToMany());
					}

					
				}

					
				if (property.equals(EventCodeUtil.ModelsAdd)){
						
					obj = viewerService.getService().findByConditionSingle(conditions);
					
					int tryTimes = 0;
					//如果obj为null说明数据未更新，尝试等待后刷新数据
					while ((obj == null) && (tryTimes < MaxTryTimes)){
						waitForRefresh();
						obj = viewerService.getService().findByConditionSingle(conditions);
					}
					
					if (obj == null){
						
						showErrMsg("数据更新时出现错误，请手工刷新该视图");
						
					}else{
						add(obj);
					}
				}else if (property.equals(EventCodeUtil.ModelsRemove)){

					remove(obj);
				}else if (property.equals(EventCodeUtil.ModelsUpdate)){
					
					//如果是航班实体，等待一段时间再获取数据，原因在于航班实体关联多，保存较慢
					if (BaseFlight.class.isAssignableFrom(entityInfo.getClazz())){
						waitForRefresh();
					}
					
					obj = viewerService.getService().findByConditionSingle(conditions);

					update(obj);
				}else if (property.equals(EventCodeUtil.ModelsRefresh)){
					refresh();
				}
			
			}

		}
		
		
		
		
	}

	
	
	
	
}
