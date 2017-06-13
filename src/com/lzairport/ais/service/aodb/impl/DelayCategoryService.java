package com.lzairport.ais.service.aodb.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lzairport.ais.dao.aodb.IDelayCategoryDao;
import com.lzairport.ais.models.aodb.DelayCategory;
import com.lzairport.ais.service.aodb.IDelayCategoryService;
import com.lzairport.ais.service.impl.Service;

/**
 * 延误类别的Service接口的实现类
 * @author ZhangYu
 * @version 0.9a 03/05/15
 * @since JDK 1.6
 *
 */
@Stateless
public class DelayCategoryService extends Service<Integer, DelayCategory>
		implements IDelayCategoryService {

	@EJB
	public void setDelayCategoryDao(IDelayCategoryDao categoryDao){
		setDao(categoryDao);
	}
}
