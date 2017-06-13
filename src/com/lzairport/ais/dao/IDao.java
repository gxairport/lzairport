package com.lzairport.ais.dao;

import java.util.List;
import java.util.Map;

import com.lzairport.ais.dao.impl.QueryConditions;

/**
 * 通用DAO层接口
 * @author ZhangYu
 * @version 0.9a 24/06/14
 * @since	JDK1.6
 *
 */



public interface IDao <K,E>{
	
	/**
	 * 在数据库保存一个对象
	 * @param E 实体对象
	 * @return 返回保存的ID值
	 */
	public E save(Object obj);
	
	
	/**
	 * 在数据库中删除一个给定的对象
	 * @param E 实体对象
	 */
	public void delete(Object entity);
	
	/**
	 * 更新一个对象的方法
	 * @param E 实体类
	 */
	public E update(Object entity);
	
	
	/**
	 * 从数据库中返回给定一个ID的实体对象
	 * @param id	给定的ID
	 * @return	查询到的实体对象
	 */
	public E findById(Object id);
	
	/**
	 * 从数据库返回给一个特定实体对象的所有数据
	 * @return	所有的给定实体对象类型的所有数据
	 */
	public List<E> getAll();
	
	/**
	 * 获取Dao层所处理的实体对象的实际类的类型
	 * @return	实体对象的实际类的类型
	 */
	public  Class<E> getModelClass();
	
	/**
	 * 从数据库返回一个给定Name的实体对象
	 * @param name 给定的name
	 * @return 查询到的实体对象
	 */
	public E findByName(Object[] expresstion);
	
	
	/**
	 * 根据给定的字段名及数据来获得一个实体对象
	 * @param field 给定的字段名
	 * @param value	指定的数据
	 * @return 符合条件的一个实体对象
	 */
	public E findByFieldSingle(String field,Object value);
	
	
	
	/**
	 * 根据给定的字段名及数据来获得实体对象集合
	 * @param field 给定的字段名
	 * @param value	指定的数据
	 * @return 符合条件的实体对象集合
	 */
	public List<E> findByFieldAll(String field,Object value);

	
	
	/**
	 * 根据给定的条件及数据来获得实体对象集合
	 * @param  conditions 传入的条件
	 * @return 符合条件的一个实体对象
	 */
	public E findByConditionSingle(QueryConditions conditions);
	
	/**
	 根据给定的条件及数据来获得实体对象集合
	 * @param  conditions 传入的条件
	 * @return 符合条件的实体对象集合
	 */
	public List<E> findByConditionAll(QueryConditions conditions);
	
	/**
	 * 
	 * @Description: TODO 根据给定的条件获取符合条件的数量
	 * @param conditions 传入的条件
	 * @return 获取符合条件对象的数量
	 */
	public int findCountByCondition(QueryConditions conditions);



	/**
	 * 
	 * @Description: TODO 根据给定的条件进行聚合处理
	 * @param conditions 传入的条件
	 * @return 聚合的结果
	 */
	public List<Map<String,Object>> findAggregationByCondition(QueryConditions conditions) throws Exception;
}
