package com.lzairport.ais.service;

import java.util.List;
import java.util.Map;

import com.lzairport.ais.dao.IDao;
import com.lzairport.ais.dao.impl.QueryConditions;


/**
 * 通用的Service层接口，提供基本的实体类操作方法
 * @author ZhangYu
 * @since JDK1.6
 * @version 0.9a 24/06/2014	
 */

public interface IService<K,E extends Object>  {
	
	
	/**
	 * 获取Service层所处理的实体对象的实际类的类型
	 * @return	实体对象的实际类的类型
	 */
	public  Class<E> getModelClass();
	
	/**
	 * 设置操作数据的Dao对象
	 * @param dao
	 */
	public void setDao(IDao<K, E> dao);	
	
	/**
	 * 删除一个数据对象
	 * @param obj
	 */
	public void remove(Object obj);	
	
	/**
	 * 增加一个数据对象
	 * @param obj
	 */
	public E add(Object obj);
	
	/**
	 * 更新一个数据对象
	 * @param object
	 */
	public E update(Object object);
	
	/**
	 * 返回所有的数据对象
	 * @return
	 */
	public  List <E> getAll();

	/**
	 * 返回指定Id所代表的数据对象
	 * @param id 关键字
	 * @return 指定Id所代表的数据对象
	 */
	public E findByID(Object id);
	
	/**
	 * 返回指定字段值得单个数据对象
	 * @param field
	 * @param value
	 * @return 指定字段值得数据对象
	 */
	public  List <E>  findByFieldAll(String field,Object value);

	/**
	 * 返回指定字段值得数据对象的集合
	 * @param field
	 * @param value
	 * @return 指定字段值得数据对象的集合
	 */
	public E  findByFieldSingle(String field,Object value);
	
	/**
	 * 根据给定的条件及数据来获得实体对象集合
	 * @param fields 字段数组
	 * @param opers 关系运算符
	 * @param links 连接符 and or
	 * @param values 数据数组
	 * @return 符合条件的一个实体对象
	 */
	public E  findByConditionSingle(QueryConditions conditions);
	
	/**
	 根据给定的条件及数据来获得实体对象集合
	 * @param fields 字段数组
	 * @param opers 关系运算符
	 * @param links 连接符 and or
	 * @param values 数据数组
	 * @return 符合条件的实体对象集合
	 */
	public  List <E>  findByConditionAll(QueryConditions conditions);
	
	/**
	 * 根据给定的条件获取符合条件的数量
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param conditions 传入的条件
	 * @return 获取符合条件对象的数量
	 */
	public int findCountByCondition(QueryConditions conditions);
	
	/**
	 * 
	 * @Description: TODO 根据给定的条件进行聚合处理
	 * @param conditions 传入的条件
	 * @return 聚合的结果
	 * @throws Exception 
	 */
	public List<Map<String,Object>> findAggregationByCondition(QueryConditions conditions) throws Exception;
	

	
	
}
