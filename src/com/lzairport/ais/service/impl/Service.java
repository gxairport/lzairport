package com.lzairport.ais.service.impl;

import java.util.List;
import java.util.Map;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.dao.impl.AisOrder;
import com.lzairport.ais.dao.impl.QueryConditions;
import com.lzairport.ais.models.DefaultEntity;
import com.lzairport.ais.service.IService;
import com.lzairport.ais.utils.SYS_VARS;


/**
 * 通用的Service层接口的实现类
 * 处理数据对象的CRUD操作
 * 监听Jms的消息，如果数据对象有变更，则通知注册在自己身上的监听者数据有改变
 * 自己是消息产生者同时也是监听者
 * @author ZhangYu
 * @since JDK1.6
 * @version 0.9a 24/06/2014	
 */


public class Service<K,E extends Object>  implements  IService<K,E>{
	
	protected IDao<K,E> dao;
	
	
	@Override
	public Class<E> getModelClass() {
		// TODO Auto-generated method stub
		return dao.getModelClass();
	}

	/**
	 * @param dao the dao to set
	 */
	@Override

	public void setDao(IDao<K,E> dao) {
		this.dao = dao;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public E add(Object obj) {
		obj = dao.save(obj);
		
	
		return (E) obj;
	}


	@SuppressWarnings("unchecked")
	@Override
	public E update(Object obj) {
		obj = dao.update(obj);
		
		
		return (E) obj;
	}

	@Override
	public void remove(Object obj) {
		dao.delete(obj);
		
		
	}


	@Override
	public  List <E> getAll() {
		return  dao.getAll();
	}

	@Override
	public E findByID(Object id) {
		// TODO Auto-generated method stub
		return  dao.findById(id);
	}

	@Override
	public  List <E> findByFieldAll(String field,Object value){
		return  dao.findByFieldAll(field, value);
		
	}

	@Override
	public E findByFieldSingle(String field, Object value) {
		// TODO Auto-generated method stub
		return  dao.findByFieldSingle(field, value);
		
	}
	@Override
	public E findByConditionSingle(QueryConditions conditions) {
		// TODO Auto-generated method stub
		E result = dao.findByConditionSingle(conditions);
		if (result != null){
			return  result; 
		}else {
			return null;
		}
	}

	@Override
	public  List<E> findByConditionAll(QueryConditions conditions) {
		// TODO Auto-generated method stub
		return dao.findByConditionAll(conditions);
	}


	@Override
	public int findCountByCondition(QueryConditions conditions) {
		// TODO Auto-generated method stub
		return dao.findCountByCondition(conditions);
	}


	@Override
	public List<Map<String,Object>> findAggregationByCondition(QueryConditions conditions) throws Exception {
		// TODO Auto-generated method stub
		return dao.findAggregationByCondition(conditions);
	}


	protected void setLastId(DefaultEntity entity) {

		if (entity.getId().equals(0)){
			QueryConditions conditions = new QueryConditions();
			conditions.setExpresstion(new Object[]{DefaultEntity.ID,"<>",0});
			AisOrder order = new AisOrder();
			order.setName(DefaultEntity.ID);
			order.setSortMode(SYS_VARS.DescSORT);
			conditions.setOrders(new AisOrder[]{order});
			DefaultEntity idEntity = (DefaultEntity) dao.findByConditionSingle(conditions);
			entity.setId(Integer.parseInt(idEntity.getId().toString())+1);
			System.out.println(entity.getId());
		}
	}


	










}
