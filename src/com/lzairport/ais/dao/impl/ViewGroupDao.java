package com.lzairport.ais.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import com.lzairport.ais.dao.IViewGroupDao;
import com.lzairport.ais.models.ViewGroup;

/**
 * 显示分组ViewGroup的Dao层接口的实现类
 * @author ZhangYu
 * @version 0.9a  24/06/14
 * @since JDK 1.6
 *
 */

@Stateless
public class ViewGroupDao extends AodbDaoImpl<Integer, ViewGroup> implements IViewGroupDao {


	@Override
	public List<ViewGroup> findByFieldAll(String field, Object value) {
		// TODO Auto-generated method stub
		return super.findByFieldAll(field, value);
	}


	@Override
	public ViewGroup findByFieldSingle(String field, Object value) {
		// TODO Auto-generated method stub
		return super.findByFieldSingle(field, value);
	}

	
}
