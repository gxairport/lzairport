package com.lzairport.ais.dao.impl;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;


/**
 * 通用的运营数据库Dao层接口实现类
 * 所有的运营数据库Dao层的实现类都继承它
 * @author ZhangYu
 * @version 0.9a 29/06/14
 * @since JDK 1.6
 *
 */

@Stateless
public class AodbDaoImpl<K, E> extends DaoImpl<K, E> {
	
	@PersistenceContext(unitName="AodbDS",type=PersistenceContextType.TRANSACTION)
	private EntityManager aodbEm;
	



	@PostConstruct 
	@Override
	public void setEntityManager() {
		// TODO Auto-generated method stub
		em = aodbEm;
	}





}
